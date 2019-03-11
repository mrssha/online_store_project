import dbservice.entity.Address;
import dbservice.entity.Customer;
import dbservice.entity.Order;
import dbservice.entity.Product;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    //Отладочный файл

    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {


            //Transaction trx = session.beginTransaction();
            //Customer cust2 = (Customer)session.load(Customer.class, 8L);
            Order ord = (Order) session.load(Order.class, 1L);
            //Product prod = (Product) session.load(Product.class, 7L);
            //session.delete(cust2);
            //trx.commit();

            /*
            Long id = Long.valueOf(5);
            Address address_1 = (Address) session.load(Address.class, id);
            System.out.println(address_1.getId());
            System.out.println(address_1.getCountry());
            System.out.println(address_1.getCity());
            System.out.println(address_1.getPostcode());
            System.out.println(address_1.getStreet());
            System.out.println(address_1.getFlatNumber());
            System.out.println(address_1.getHouseNumber());
            System.out.println(address_1.getCustomer().getFirstName());
            */




            /*
            System.out.println("querying all the managed entities...");
            Long id = Long.valueOf(2);
            Customer customer_1 = (Customer)session.load(Customer.class, id);
            System.out.println(customer_1.getId());
            System.out.println(customer_1.getFirstName());
            System.out.println(customer_1.getSecondName());
            System.out.println(customer_1.getBirthDate());
            System.out.println(customer_1.getEmail());
            System.out.println(customer_1.getPassword());
            */

            /*
            Transaction trx = session.beginTransaction();
            Customer cust2 = new Customer();
            //cust2.setId(7L);
            cust2.setFirstName("Harry");
            cust2.setSecondName("Potter");
            cust2.setEmail("potter@mail.com");
            cust2.setPassword("alohomora");

            Address address2 = new Address();
            //address2.setId(7L);
            address2.setStreet("Parnas");
            cust2.addAddress(address2);
            session.save(cust2);
            trx.commit();

            Set<Address> addresses =  cust2.getAddresses();
            for (Address address: addresses){
                System.out.println(address.getStreet());
            }
            */


            //Transaction trx = session.beginTransaction();
            //Customer cust2 = (Customer)session.load(Customer.class, 8L);

            //Address address3 = new Address();
            //address3.setCity("Paris");
            //address3.setCustomer(cust2);
            //cust2.addAddress(address3);
            //Address a = (Address) session.load(Address.class, 6L);
            //a.setCountry("UK");
            //session.delete(cust2);
            //trx.commit();


            /*
            Transaction trx = session.beginTransaction();
            Customer cust2 = (Customer)session.load(Customer.class, 8L);

            Order order = new Order();
            order.setCustomer(cust2);
            order.setCustomerAddress(cust2.getAddresses().get(0));
            Date date = new Date();
            order.setDateOrder(date);

            session.persist(order);
            trx.commit();
            */

            //Order ord = (Order) session.load(Order.class, 1L);
            //System.out.println(ord.getCustomer().getFirstName());

            /*
            Transaction trx = session.beginTransaction();
            Customer cust2 = (Customer)session.load(Customer.class, 8L);

            Product prod = new Product();
            prod.setName("Шлем");
            prod.setPrice(1000.0);
            prod.setBrand("666");
            prod.setCategory("Защита");
            prod.setQuantity(12);

            session.persist(prod);
            trx.commit();
            */











            /*
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
            */

        } finally {
            session.close();
        }
    }
}