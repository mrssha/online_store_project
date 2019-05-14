package store.dao;

import store.entity.OrderProduct;
import store.entity.Order;
import store.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class OrderProductDaoImpl implements OrderProductDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public OrderProduct getById(long id) {
        return entityManager.find(OrderProduct.class, id);
    }

    @Override
    public List<Product> getProductsInOrder(long id_order) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = cBuilder.createQuery(Product.class);
        Root<OrderProduct> root = criteriaQuery.from(OrderProduct.class);
        criteriaQuery.select(root.get("product"));
        criteriaQuery.where(cBuilder.equal(root.get("order"), id_order));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Order> getOrdersForProduct(long id_product) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = cBuilder.createQuery(Order.class);
        Root<OrderProduct> root = criteriaQuery.from(OrderProduct.class);
        criteriaQuery.select(root.get("order"));
        criteriaQuery.where(cBuilder.equal(root.get("product"), id_product));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


    /*
    @Override
    public List<OrderProduct> getBasketsByOrder(Order order){
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderProduct> criteriaQuery = cBuilder.createQuery(OrderProduct.class);
        Root<OrderProduct> root = criteriaQuery.from(OrderProduct.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("order"), order));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    */

    @Override
    public void add(OrderProduct orderProduct){
        entityManager.persist(orderProduct);
    }


    @Override
    public void update(OrderProduct orderProduct){
        entityManager.merge(orderProduct);
    }

    @Override
    public void deleteById(long id){
        OrderProduct orderProduct = entityManager.find(OrderProduct.class, id);
        entityManager.remove(orderProduct);
    }

}

