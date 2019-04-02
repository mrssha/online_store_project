package dbservice.dao;

import dbservice.entity.Cart;
import dbservice.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cart getById(long id){
        return entityManager.find(Cart.class, id);
    }


    @Override
    public Cart getByProductAndCustomer(long id_customer, long id_product){
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cart> criteriaQuery = cBuilder.createQuery(Cart.class);
        Root<Cart> root = criteriaQuery.from(Cart.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("customer"), id_customer),
                cBuilder.equal(root.get("product"), id_product));
        return entityManager.createQuery(criteriaQuery).getResultList().
                stream().findFirst().orElse(null);
    }

    @Override
    public List<Product> getProductsInCart(long id_customer){
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = cBuilder.createQuery(Product.class);
        Root<Cart> root = criteriaQuery.from(Cart.class);
        criteriaQuery.select(root.get("product"));
        criteriaQuery.where(cBuilder.equal(root.get("customer"), id_customer));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Cart> getCartItemsForCustomer(long id_customer){
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cart> criteriaQuery = cBuilder.createQuery(Cart.class);
        Root<Cart> root = criteriaQuery.from(Cart.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("customer"), id_customer));
        return entityManager.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public void add(Cart cartItem){
        entityManager.persist(cartItem);
    }

    @Override
    public void deleteById(long id){
        Cart cartItem = entityManager.find(Cart.class, id);
        entityManager.remove(cartItem);
    }

    @Override
    public void update(Cart cartItem){
        entityManager.merge(cartItem);
    }
}
