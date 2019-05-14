package unit;

import store.converter.CustomerConverter;
import store.converter.CustomerConverterImpl;
import store.dto.CustomerDto;
import store.entity.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(MockitoJUnitRunner.class)
public class CustomerConverterImplTest {

    private CustomerConverter customerConverter = new CustomerConverterImpl();
    private Customer customer;

    @Before
    public void beforeTest() {
        customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("John");
        customer.setSecondName("Smith");
        customer.setEmail("john@gmail.com");
        customer.setPassword("234567");
        customer.setBirthDate(new Date());
        customer.setSumPurchases(40000.0);
        customer.setRole("ROLE_USER");
    }

    @Test
    public void convertToDto() {
        CustomerDto customerDto =  customerConverter.convertToDto(customer);
        assertNotNull(customerDto);
        assertEquals(customer.getId(),customerDto.getId());
        assertEquals(customer.getFirstName(),customerDto.getFirstName());
        assertEquals(customer.getSecondName(),customerDto.getSecondName());
        assertEquals(customer.getEmail(), customerDto.getEmail());
        assertEquals(customer.getPassword(), customerDto.getPassword());
        assertEquals(customer.getBirthDate(), customerDto.getBirthDate());
        assertEquals(customer.getSumPurchases(), customerDto.getSumPurchases());
        assertEquals(customer.getRole(), customerDto.getRole());
    }

    @Test
    public void convertToDto2(){
        Customer customer = null;
        CustomerDto customerDto =  customerConverter.convertToDto(customer);
        Assert.assertNull(customerDto);
    }
}
