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
    public List<Product> getProductsInOrder(Order order) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = cBuilder.createQuery(Product.class);
        Root<Basket> root = criteriaQuery.from(Basket.class);
        criteriaQuery.select(root.get("product"));
        criteriaQuery.where(cBuilder.equal(root.get("oder"), order));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Basket> getOrdersBasket(Order order){
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Basket> criteriaQuery = cBuilder.createQuery(Basket.class);
        Root<Basket> root = criteriaQuery.from(Basket.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("order"), order));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public void add(Basket basket){
        entityManager.persist(basket);
    }


    //void update(Basket basket);

    //void deleteByOrder(Order order);
}

