package dbservice.dao;

import dbservice.entity.Customer;
import dbservice.entity.Order;
import dbservice.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order getById(long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> getByCustomer(Customer customer) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = cBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("customer"), customer.getId()));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Order> getByDate(Date date){
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = cBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("dateOrder"), date));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


    @Override
    public int getProductQuantity(Order order, Product product) {
        return 0;
    }

    @Override
    @Transactional
    public void add(Order order) {
        entityManager.persist(order);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Order order = entityManager.find(Order.class, id);
        entityManager.remove(order);
    }

    @Override
    @Transactional
    public void update(Order order) {
        entityManager.merge(order);
    }
}
