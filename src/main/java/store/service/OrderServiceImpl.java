package store.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import store.converter.OrderConverter;
import store.converter.ProductConverter;
import store.dao.BasketDao;
import store.dao.CartDao;
import store.dao.OrderDao;
import store.dao.ProductDao;
import store.dto.*;
import store.entity.*;
import store.result.LogMessage;
import store.result.StatusResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderConverter orderConverter;

    @Autowired
    OrderDao orderDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductConverter productConverter;

    @Autowired
    BasketDao basketDao;

    @Autowired
    CartDao cartDao;

    @Autowired
    StandService standService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CartService cartService;


    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    public Map<ProductDto, Integer> getOrdersProducts(OrderDto orderDto){
        return null;
    }

    @Override
    @Transactional
    public OrderDto getById(long id) {
        return orderConverter.convertToDto(orderDao.getById(id));
    }


    @Override
    @Transactional
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderDao.getAllOrders();
        List<OrderDto> ordersDto = new ArrayList<>();
        for (Order order: orders){
            ordersDto.add(orderConverter.convertToDto(order));
        }
        return ordersDto;
    }

    @Override
    @Transactional
    public List<OrderDto> getByCustomerId(long id) {
        List<Order> orders = orderDao.getByCustomerId(id);
        List<OrderDto> ordersDto = new ArrayList<>();
        for (Order order: orders){
            ordersDto.add(orderConverter.convertToDto(order));
        }
        return ordersDto;
    }

    @Override
    @Transactional
    public Set<OrderDto> getByDate(Date date) {
        Set<Order> orders = new HashSet<>(orderDao.getByDate(date));
        return orderConverter.convertToDtoSet(orders);
    }

    @Override
    @Transactional
    public void add(OrderDto order) {
        orderDao.add(orderConverter.convertToEntity(order));

    }

    @Override
    @Transactional
    public void update(OrderDto order) {
        orderDao.update(orderConverter.convertToEntity(order));
    }


    @Override
    @Transactional
    public void updateStatus(String orderJson){
        JsonElement jElement = new JsonParser().parse(orderJson);
        JsonObject jObject = jElement.getAsJsonObject();
        Long id = jObject.get("id").getAsLong();
        String orderStatus = jObject.get("orderStatus").getAsString();
        OrderDto orderDto = getById(id);
        orderDto.setOrderStatus(OrderStatus.valueOf(orderStatus));
        update(orderDto);
        logger.info(LogMessage.ORDER_SUCCESS_UPDATE);
    }

    @Override
    @Transactional
    public String getRevenueForPeriod(String periodJson){
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jo = (JsonObject) parser.parse(periodJson);
        Integer year = jo.get("year").getAsInt();
        String month = jo.get("month").getAsString();
        Double sum = orderDao.getRevenueForPeriod(year,month);
        return gson.toJson(sum != null ? sum.toString(): String.valueOf(0));
    }

    @Override
    @Transactional
    public String getRevenueForWeek(){
        Double sum = orderDao.getRevenueForLastWeek();
        return (sum != null ? sum.toString(): String.valueOf(0));
    }


    @Override
    @Transactional
    public void deleteById(long id) {
        orderDao.deleteById(id);
    }


    @Override
    @Transactional
    public StatusResult confirmOrder(CustomerDto customerDto, OrderDto orderDto, BaseCartDto baseCartDto){
        Order newOrder = createNewOrder(orderDto, baseCartDto);
        orderDao.add(newOrder);
        List<CartDto> cartItems = baseCartDto.getCartItems();

        for (CartDto cartItem: cartItems){
            Product product = productDao.getByIdForUpdate(cartItem.getProduct().getId());
            if (cartItem.getQuantity() > product.getQuantity()){
                logger.info(StatusResult.ORDER_FIND_MISSING_PRODUCTS.getMessage());
                return StatusResult.ORDER_FIND_MISSING_PRODUCTS;
            }
        }

        for (CartDto cartItem: cartItems){
            //Product changedProduct = productDao.getByIdForUpdate(cartItem.getProduct().getId());
            Product changedProduct = productDao.getById(cartItem.getProduct().getId());
            Basket orderProduct = new Basket();
            orderProduct.setOrder(newOrder);
            orderProduct.setProduct(changedProduct);
            orderProduct.setQuantity(cartItem.getQuantity());
            basketDao.add(orderProduct);

            int oldQuantity = changedProduct.getQuantity();
            int amountBought = changedProduct.getSales();
            changedProduct.setQuantity(oldQuantity - cartItem.getQuantity());
            changedProduct.setSales(amountBought + cartItem.getQuantity());
            productDao.update(changedProduct);
            cartDao.deleteById(cartItem.getId());
        }
        logger.info(StatusResult.ORDER_CONFIRM_SUCCESS.getMessage());
        customerDto.setSumPurchases(customerDto.getSumPurchases() + newOrder.getPayment_amount());
        customerService.updateCustomer(customerDto);
        standService.updateStandIfTopChanged();
        return StatusResult.ORDER_CONFIRM_SUCCESS;
    }

    @Override
    public Order createNewOrder(OrderDto orderDto, BaseCartDto baseCartDto){
        Order order = orderConverter.convertToEntity(orderDto);
        Date date = new Date();
        order.setDateOrder(date);
        order.setPaymentStatus(PaymentStatus.WAITING);
        order.setOrderStatus(OrderStatus.WAIT_PAYMENT);
        order.setQuantityProducts(baseCartDto.getAmountProducts());
        order.setPayment_amount(baseCartDto.getTotalSum());
        return order;
    }

}



