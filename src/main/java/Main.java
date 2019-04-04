import com.sun.org.apache.xalan.internal.xsltc.dom.AdaptiveResultTreeImpl;
import dbservice.entity.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

            Calendar calendar = Calendar.getInstance();
            Date date =  calendar.getTime();
            System.out.println(date);

            /*
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);

            Integer min = 5000;
            Integer max = 15000;


            List<Predicate> predicates = new ArrayList<Predicate>();

            String name = null;
            String category = null;
            String brand = "K2";
            //Double minPrice = Double.valueOf(min);
            //Double maxPrice = Double.valueOf(max);



            if (name != null) {
                predicates.add(
                        builder.equal(root.get("name"), name));
            }
            if (category != null) {
                predicates.add(
                        builder.equal(root.get("category"), category));
            }
            if (brand != null) {
                predicates.add(
                        builder.equal(root.get("brand"), brand));
            }
            if (min != null){
                if (max != null){
                    Double minPrice = Double.valueOf(min);
                    Double maxPrice = Double.valueOf(max);
                    predicates.add(
                            builder.between(root.get("price"), minPrice, maxPrice));
                }
                else {
                    Double minPrice = Double.valueOf(min);
                    predicates.add(
                            builder.greaterThanOrEqualTo(root.get("price"), minPrice));
                }
            }
            else{
                if (max != null){
                    Double maxPrice = Double.valueOf(max);
                    predicates.add(
                            builder.lessThanOrEqualTo(root.get("price"), maxPrice));
                }

            }

            criteriaQuery.select(root)
                    .where(predicates.toArray(new Predicate[]{}));
            //execute query and do something with result
            List<Product> products = session.createQuery(criteriaQuery).getResultList();
            for (Product product: products){
                System.out.println(product);
            }
            System.out.println(products.size());
            */


            /*
            Transaction trx = session.beginTransaction();
            Customer cust3 = (Customer)session.load(Customer.class, 1L);
            Product prod = (Product)session.load(Product.class, 12L);


            Cart cart = new Cart();
            cart.setCustomer(cust3);
            cart.setProduct(prod);
            cart.setQuantity(1);
            session.persist(cart);
            trx.commit();
            */

            long id_customer =  5L;
            long id_product =  4L;
            /*
            CriteriaBuilder cBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Cart> criteriaQuery = cBuilder.createQuery(Cart.class);
            Root<Cart> root = criteriaQuery.from(Cart.class);
            criteriaQuery.select(root);
            criteriaQuery.where(cBuilder.equal(root.get("customer"), id_customer));
            List<Cart> carts =  session.createQuery(criteriaQuery).getResultList();
            System.out.println(carts.size());
            */






            //Создание Order

            /*
            Transaction trx = session.beginTransaction();
            Customer cust3 = (Customer)session.load(Customer.class, 3L);

            Order order = new Order();
            order.setCustomer(cust3);
            order.setCustomerAddress(cust3.getAddresses().get(0));
            Date date = new Date();
            order.setDateOrder(date);

            session.persist(order);
            trx.commit();
            */

            /*
            Order order = (Order)session.load(Order.class, 2L);
            //EntityManager entityManager = session.getEntityManagerFactory().createEntityManager();

            CriteriaBuilder cBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Basket> criteriaQuery = cBuilder.createQuery(Basket.class);
            Root<Basket> root = criteriaQuery.from(Basket.class);
            criteriaQuery.select(root);
            criteriaQuery.where(cBuilder.equal(root.get("order"), order));
            List<Basket> baskets = session.createQuery(criteriaQuery).getResultList();
            for (Basket basket: baskets) {
                System.out.println(basket.getOrder().getId().toString()+" "+  basket.getProduct().getName() +
                        " " + basket.getQuantity());
            }
            int y=9;
            int r=8;
            */


            /*
            long id_product = 11L;
            CriteriaBuilder cBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = cBuilder.createQuery(Order.class);
            Root<Basket> root = criteriaQuery.from(Basket.class);
            criteriaQuery.select(root.get("order"));
            criteriaQuery.where(cBuilder.equal(root.get("product"), id_product));
            List<Order>  products = session.createQuery(criteriaQuery).getResultList();
            for (Order product: products){
                System.out.println(product);
            }
            System.out.println(products.size());
            */




            /*
            Transaction trx = session.beginTransaction();
            Order ord = (Order)session.load(Order.class, 1L);
            Product prod = (Product)session.load(Product.class, 12L);

            Basket bas = new Basket();
            bas.setOrder(ord);
            bas.setProduct(prod);
            bas.setQuantity(2);

            session.persist(bas);
            trx.commit();
            */






            /*
            Transaction trx = session.beginTransaction();
            Order ord = (Order)session.load(Order.class, 2L);
            //Product prod = (Product)session.load(Product.class, 2L);
            //Basket bas = (Basket) session.load(Basket.class, 6L);
            session.delete(ord);
            trx.commit();
            */

            /*
            Transaction trx = session.beginTransaction();
            Product prod = (Product)session.load(Product.class, 2L);
            prod.setPrice(9500.0);
            //Basket bas = (Basket) session.load(Basket.class, 6L);
            session.update(prod);
            trx.commit();
            */




            /*
            //Order order = (Order)session.load(Order.class, 1L);
            long id = 1L;
            CriteriaBuilder cBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = cBuilder.createQuery(Product.class);
            Root<Basket> root = criteriaQuery.from(Basket.class);
            criteriaQuery.select(root.get("product"));
            criteriaQuery.where(cBuilder.equal(root.get("order"), id));
            List<Product> products =  session.createQuery(criteriaQuery).getResultList();

            for (Product product: products){
                System.out.println(product);
            }
            System.out.println(products.size());
            */




            /*
            long id = 1L;
            CriteriaBuilder cBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Basket> criteriaQuery = cBuilder.createQuery(Basket.class);
            Root<Basket> root = criteriaQuery.from(Basket.class);
            criteriaQuery.select(root);
            criteriaQuery.where(cBuilder.equal(root.get("order"), id));
            List<Basket> baskets =  session.createQuery(criteriaQuery).getResultList();
            for (Basket basket: baskets){
                System.out.println(basket);
            }
            System.out.println(baskets.size());
            */







            //Product prod = (Product) session.load(Product.class, 5L);
            //System.out.println(prod.getName());
            //System.out.println(prod.toString());


            /*
            Product prod = new Product();
            prod.setName("Шлем");
            prod.setPrice(1000.0);
            prod.setBrand("666");
            prod.setCategory("Защита");
            prod.setQuantity(12);
            System.out.println(prod.toString());
            */

            /*
            Transaction trx = session.beginTransaction();
            //Customer cus = session.load(Customer.class, 5L);
            Category category = new Category();
            category.setCategoryName("snowboards");
            session.persist(category);
            trx.commit();
            */


            /*
            Transaction trx = session.beginTransaction();
            Category cat = session.load(Category.class, 1L);

            Product prod = new Product();
            prod.setName("Сноборд 100500");
            prod.setPrice(6000.0);
            prod.setBrand("666");
            prod.setCategory(cat);
            prod.setQuantity(12);

            session.persist(prod);
            trx.commit();
            */



            /*
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String pas = passwordEncoder.encode("admin");
            System.out.println(pas);
            */












            //Transaction trx = session.beginTransaction();
            //Customer cust2 = (Customer)session.load(Customer.class, 8L);

            //Address address3 = new Address();
            //address3.setCity("Paris");
            //address3.setCustomer(cust2);
            //cust2.addAddress(address3);
            //Address ad = (Address) session.load(Address.class, 4L);
            //System.out.println(ad.toString());

            //a.setCountry("UK");
            //session.delete(cust2);
            //trx.commit();








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