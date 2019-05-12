package unit;

import dbservice.config.WebConfig;
import dbservice.converter.CustomerConverter;
import dbservice.converter.CustomerConverterImpl;
import dbservice.dao.CustomerDao;
import dbservice.dao.CustomerDaoImpl;
import dbservice.dto.CustomerDto;
import dbservice.entity.Customer;
import dbservice.service.CustomerService;
import dbservice.service.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;



//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {CustomerServiceImpl.class})
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest{




    //@Qualifier("mockCustomerDao")
    //@Autowired
    @Mock
    private CustomerDao customerDao;

    @Mock
    private CustomerConverter customerConverter;


    //@Autowired
    @InjectMocks
    private CustomerServiceImpl customerService = new CustomerServiceImpl();

    private Customer customer = new Customer();

    @Before
    public void beforeTest() {
        customer.setId(2L);
        customer.setFirstName("John");
        customer.setSecondName("Smith");
        customer.setEmail("john@gmail.com");



//        when(customerDao.getById(2L)).thenReturn(customer);
//        when(customerDao.getByEmail("john@gmail.com")).thenReturn(customer);

    }



    public void getCustomerById() {
    }

    @Test
    public void getCustomerByEmail() {
        String email = "john@gmail.com";
        System.out.println(customer);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(2L);
        //when(customerDao.getByEmail(email)).thenReturn(customer);
        when(customerConverter.convertToDto(any())).thenReturn(customerDto);

        //Customer n = customerDao.getByEmail(email);
        CustomerDto resultCustomer = customerService.getCustomerByEmail(email);

        assertNotNull(resultCustomer);
        assertEquals(resultCustomer.getId(),customer.getId());
    }


}