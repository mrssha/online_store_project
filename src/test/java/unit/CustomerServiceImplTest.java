package unit;

import dbservice.converter.CustomerConverter;
import dbservice.dao.CustomerDao;
import dbservice.dto.CustomerDto;
import dbservice.entity.Customer;
import dbservice.result.StatusResult;
import dbservice.service.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest{


    @Mock
    private CustomerDao customerDao;

    @Mock
    private CustomerConverter customerConverter;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomerServiceImpl customerService = new CustomerServiceImpl();

    private Customer customer = new Customer();

    @Before
    public void beforeTest() {
        customer.setId(2L);
        customer.setFirstName("John");
        customer.setSecondName("Smith");
        customer.setEmail("john@gmail.com");
    }

    @Test
    public void getCustomerByEmail() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(2L);

        when(customerConverter.convertToDto(any())).thenReturn(customerDto);
        CustomerDto resultCustomer = customerService.getCustomerByEmail(customer.getEmail());
        assertNotNull(resultCustomer);
        assertEquals(resultCustomer.getId(),customer.getId());
    }

    @Test
    public void addCustomer1(){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(2L);
        customerDto.setEmail("john@gmail.com");
        when(customerConverter.convertToDto(any())).thenReturn(customerDto);
        StatusResult result = customerService.addCustomer(customerDto);
        assertEquals(result, StatusResult.EMAIL_ALREADY_EXIST);
    }

    public void addCustomer2(){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(2L);
        customerDto.setEmail("john@gmail.com");
        customerDto.setPassword("123456");
        when(customerConverter.convertToDto(any())).thenReturn(null);
        when(passwordEncoder.encode(any())).thenReturn("123456");
        StatusResult result = customerService.addCustomer(customerDto);
        assertEquals(customerDto.getSumPurchases(), Double.valueOf(0));
        assertEquals(customerDto.getRole(), "ROLE_USER");
        assertEquals(result, StatusResult.CUSTOMER_CREATE_SUCCESS);
    }
}