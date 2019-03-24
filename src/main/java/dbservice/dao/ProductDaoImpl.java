package dbservice.dao;

import dbservice.dto.ProductDto;
import dbservice.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    public List<Product> getAll() {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = cBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


    @Override
    public List<Product> getByParams(String name, String category, String brand,
                                     Integer minPrice, Integer maxPrice){

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

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

        if (minPrice != null){
            if (maxPrice != null){
                Double minPriceDouble = Double.valueOf(minPrice);
                Double maxPriceDouble = Double.valueOf(maxPrice);
                predicates.add(
                        builder.between(root.get("price"), minPriceDouble, maxPriceDouble));
            }
            else {
                Double minPriceDouble = Double.valueOf(minPrice);
                predicates.add(
                        builder.greaterThanOrEqualTo(root.get("price"), minPriceDouble));
            }
        }
        else{
            if (maxPrice != null){
                Double maxPriceDouble = Double.valueOf(maxPrice);
                predicates.add(
                        builder.lessThanOrEqualTo(root.get("price"), maxPriceDouble));
            }

        }
        criteriaQuery.select(root)
                .where(predicates.toArray(new Predicate[]{}));
        //execute query and do something with result
        return entityManager.createQuery(criteriaQuery).getResultList();

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
    public void add(Product product) {
        entityManager.persist(product);

    }

    @Override
    public void deleteById(long id) {
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);

    }

    @Override
    public void update(Product product) {
        entityManager.merge(product);

    }
}
