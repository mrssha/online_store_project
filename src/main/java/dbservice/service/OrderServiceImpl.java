package dbservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dbservice.controller.ProductController;
import dbservice.converter.OrderConverter;
import dbservice.converter.ProductConverter;
import dbservice.dao.BasketDao;
import dbservice.dao.CartDao;
import dbservice.dao.OrderDao;
import dbservice.dao.ProductDao;
import dbservice.dto.*;
import dbservice.entity.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.UncategorizedJmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Month;
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
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CartService cartService;

    @Autowired
    private JmsTemplate jmsTemplate;

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
    public void updateFromJson (String orderJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(orderJson);
        System.out.println(rootNode);
        Long id = rootNode.get("id").asLong();
        String orderStatus = rootNode.get("orderStatus").asText();
        OrderDto orderDto = getById(id);
        orderDto.setOrderStatus(OrderStatus.valueOf(orderStatus));
        update(orderDto);
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
    public void deleteById(long id) {
        orderDao.deleteById(id);
    }


    @Override
    @Transactional
    public void confirmOrder(CustomerDto customerDto, OrderDto orderDto, List<CartDto> cartItems){
        Order newOrder = createNewOrder(orderDto, cartItems);
        orderDao.add(newOrder);

        for (CartDto cartItem: cartItems){
            Basket orderProduct = new Basket();
            orderProduct.setOrder(newOrder);
            orderProduct.setProduct(productConverter.convertToEntity(cartItem.getProduct()));
            orderProduct.setQuantity(cartItem.getQuantity());
            basketDao.add(orderProduct);

            Product changedProduct = productDao.getById(cartItem.getProduct().getId());
            int oldQuantity = changedProduct.getQuantity();
            int amountBought = changedProduct.getSales();
            changedProduct.setQuantity(oldQuantity - cartItem.getQuantity());
            changedProduct.setSales(amountBought + cartItem.getQuantity());
            productDao.update(changedProduct);
            cartDao.deleteById(cartItem.getId());
        }
        logger.info(String.format("Order successfully confirmed for customer with id: %d",
                customerDto.getId()));
        customerDto.setSumPurchases(customerDto.getSumPurchases() + newOrder.getPayment_amount());
        customerService.updateCustomer(customerDto);
        updateStandIfTopChanged();

    }


    private Order createNewOrder(OrderDto orderDto, List<CartDto> cartItems){
        Order order = orderConverter.convertToEntity(orderDto);
        Date date = new Date();
        order.setDateOrder(date);
        order.setPaymentStatus(PaymentStatus.WAITING);
        order.setOrderStatus(OrderStatus.WAIT_PAYMENT);
        int amount = 0;
        double totalPrice = 0;
        for(CartDto cart: cartItems){
            amount += cart.getQuantity();
            totalPrice += cart.getProduct().getPrice() * cart.getQuantity();
        }
        order.setQuantityProducts(amount);
        order.setPayment_amount(totalPrice);
        return order;
    }

    private void updateStandIfTopChanged(){
        List<ProductDto> lastTopList = productService.getLastTopProductsList();
        List<ProductDto> newTopList= productService.getTopProducts();
        try {
            if (!lastTopList.containsAll(newTopList)) {
                jmsTemplate.send(s -> s.createTextMessage("update list"));
            }
        }
        catch (UncategorizedJmsException e){
            logger.error("Couldn't send message to JMS");
        }
    }

}



