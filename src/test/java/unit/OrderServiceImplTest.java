package unit;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dbservice.converter.OrderConverter;
import dbservice.converter.ProductConverter;
import dbservice.dao.BasketDao;
import dbservice.dao.CartDao;
import dbservice.dao.OrderDao;
import dbservice.dao.ProductDao;
import dbservice.dto.*;
import dbservice.entity.*;
import dbservice.service.CustomerService;
import dbservice.service.OrderService;
import dbservice.service.OrderServiceImpl;
import dbservice.service.StandService;
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
    private ProductConverter productConverter;

    @Mock
    private CustomerService customerService;

    @Mock
    private OrderDao orderDao;

    @Mock
    private BasketDao basketDao;

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
    public void confirmOrder(){
        when(orderConverter.convertToEntity(any())).thenReturn(order);
        when(productConverter.convertToEntity(any())).thenReturn(new Product());
        Product product = new Product();
        product.setSales(1);
        product.setQuantity(10);
        when(productDao.getById(anyLong())).thenReturn(product);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setSumPurchases(1000.0);
        OrderDto orderDto = new OrderDto();
        orderService.confirmOrder(customerDto, orderDto, baseCartDto);
        Assert.assertEquals(customerDto.getSumPurchases(), Double.valueOf(1000.0 + baseCartDto.getTotalSum()));
    }

    @Test
    public void getRevenueForPeriod(){
        when(orderDao.getRevenueForPeriod(anyInt(), anyString())).thenReturn(30000.0);
        JsonObject object = new JsonObject();
        object.addProperty("year", 2019);
        object.addProperty("month", "MAY");
        Gson gson = new Gson();
        String s = gson.toJson(object).toString();
        String sum = orderService.getRevenueForPeriod(s);
        Assert.assertEquals(sum, gson.toJson(String.valueOf(30000.0)));
    }
}
