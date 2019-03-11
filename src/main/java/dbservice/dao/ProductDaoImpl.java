package dbservice.DAO;

import dbservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product getById(long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> getByName(String name) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = cBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("name"), name));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Product> getByCategory(String name) {
        return null;
    }


    //Объеденить позже с getByName()
    @Override
    public List<Product> getByBrand(String name) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = cBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("brand"), name));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional
    public void add(Product product) {
        entityManager.persist(product);

    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);

    }

    @Override
    @Transactional
    public void update(Product product) {
        entityManager.merge(product);

    }
}
