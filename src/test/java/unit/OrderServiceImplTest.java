package unit;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import store.converter.OrderConverter;
import store.dao.OrderProductDao;
import store.dao.CartDao;
import store.dao.OrderDao;
import store.dao.ProductDao;
import store.dto.*;
import store.entity.*;
import store.utils.StatusResult;
import store.service.CustomerService;
import store.service.OrderService;
import store.service.OrderServiceImpl;
import store.service.StandService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderConverter orderConverter;

    @Mock
    private CustomerService customerService;

    @Mock
    private OrderDao orderDao;

    @Mock
    private OrderProductDao orderProductDao;

    @Mock
    private ProductDao productDao;

    @Mock
    private CartDao cartDao;

    @Mock
    private StandService standService;

    @InjectMocks
    private OrderService orderService = new OrderServiceImpl();

    private Order order = new Order();
    private BaseCartDto baseCartDto =  new BaseCartDto();

    @Before
    public void beforeTest(){
        order.setCustomer(new Customer());
        order.setCustomerAddress(new Address());
        baseCartDto.setAmountProducts(5);
        baseCartDto.setTotalSum(85000.0);
        List<CartDto> cartItems = new ArrayList<>();
        ProductDto product = new ProductDto();
        product.setId(2L);
        CartDto cartDto = new CartDto();
        cartDto.setProduct(product);
        cartDto.setId(1L);
        cartDto.setQuantity(5);
        cartItems.add(cartDto);
        baseCartDto.setCartItems(cartItems);

    }

    @Test
    public void createNewOrder(){
        when(orderConverter.convertToEntity(any())).thenReturn(order);
        Order newOrder = orderService.createNewOrder(new OrderDto(), baseCartDto);
        Assert.assertNotNull(newOrder);
        Assert.assertEquals(newOrder.getPayment_amount(), Double.valueOf(baseCartDto.getTotalSum()));
        Assert.assertEquals(newOrder.getQuantityProducts(), Integer.valueOf(baseCartDto.getAmountProducts()));
        Assert.assertEquals(newOrder.getPaymentStatus(), PaymentStatus.WAITING);
        Assert.assertEquals(newOrder.getOrderStatus(), OrderStatus.WAIT_PAYMENT);
    }

    @Test
    public void confirmOrder1(){
        when(orderConverter.convertToEntity(any())).thenReturn(order);
        Product productBase = new Product();
        productBase.setSales(1);
        productBase.setQuantity(3);
        when(productDao.getByIdForUpdate(anyLong())).thenReturn(productBase);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setSumPurchases(1000.0);
        OrderDto orderDto = new OrderDto();

        StatusResult result = orderService.confirmOrder(customerDto, orderDto, baseCartDto);
        Assert.assertEquals(result, StatusResult.ORDER_FIND_MISSING_PRODUCTS);
    }

    @Test
    public void confirmOrder2(){
        when(orderConverter.convertToEntity(any())).thenReturn(order);

        Product productBase = new Product();
        productBase.setSales(1);
        productBase.setQuantity(10);
        when(productDao.getById(anyLong())).thenReturn(productBase);
        when(productDao.getByIdForUpdate(anyLong())).thenReturn(productBase);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setSumPurchases(1000.0);
        OrderDto orderDto = new OrderDto();

        StatusResult result = orderService.confirmOrder(customerDto, orderDto, baseCartDto);
        Assert.assertEquals(result, StatusResult.ORDER_CONFIRM_SUCCESS);
        Assert.assertEquals(customerDto.getSumPurchases(), Double.valueOf(1000.0 + baseCartDto.getTotalSum()));
    }

    @Test
    public void getRevenueForPeriod(){
        when(orderDao.getRevenueForPeriod(anyInt(), anyString())).thenReturn(30000.0);
        JsonObject object = new JsonObject();
        object.addProperty("year", 2019);
        object.addProperty("month", "MAY");
        Gson gson = new Gson();
        String s = gson.toJson(object);
        String sum = orderService.getRevenueForPeriod(s);
        Assert.assertEquals(sum, gson.toJson(String.valueOf(30000.0)));
    }
}
