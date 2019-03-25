package dbservice.dao;

import dbservice.entity.Basket;
import dbservice.entity.Order;
import dbservice.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class BasketDaoImpl implements BasketDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Basket getById(long id) {
        return entityManager.find(Basket.class, id);
    }

    @Override
    public List<Product> getProductsInOrder(long id_order) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = cBuilder.createQuery(Product.class);
        Root<Basket> root = criteriaQuery.from(Basket.class);
        criteriaQuery.select(root.get("product"));
        criteriaQuery.where(cBuilder.equal(root.get("order"), id_order));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Order> getOrdersForProduct(long id_product) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = cBuilder.createQuery(Order.class);
        Root<Basket> root = criteriaQuery.from(Basket.class);
        criteriaQuery.select(root.get("order"));
        criteriaQuery.where(cBuilder.equal(root.get("product"), id_product));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


    /*
    @Override
    public List<Basket> getBasketsByOrder(Order order){
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Basket> criteriaQuery = cBuilder.createQuery(Basket.class);
        Root<Basket> root = criteriaQuery.from(Basket.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("order"), order));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


    // убрать если не нужен
    @Override
    public List<Basket> getBasketsByProduct(Product product){
        return null;
    }
    */

    @Override
    public void add(Basket basket){
        entityManager.persist(basket);
    }


    @Override
    public void update(Basket basket){
        entityManager.merge(basket);
    }

    @Override
    public void deleteById(long id){
        Basket basket = entityManager.find(Basket.class, id);
        entityManager.remove(basket);
    }

}

