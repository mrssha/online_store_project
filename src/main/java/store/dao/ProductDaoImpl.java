package store.dao;

import store.entity.Category;
import store.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
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
    public Product getByIdForUpdate(long id){
        return entityManager.find(Product.class, id, LockModeType.PESSIMISTIC_WRITE);
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
    public List<Product> getByParams(Long id_category, String brand,
                                     Integer minPrice, Integer maxPrice){



        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        if (id_category != null) {
            predicates.add(
                    builder.equal(root.get("category"), id_category));
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
        return entityManager.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public List<Product> getBySearch(String search) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root).where(builder.or(
                builder.like(
                        builder.lower(root.get("name")), '%' + search.toLowerCase() + '%'),
                builder.like(
                        builder.lower(root.get("brand")), '%' + search.toLowerCase() + '%')));
        return entityManager.createQuery(query).getResultList();
    }


    @Override
    public Product getByName(String name) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = cBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("name"), name));
        return (Product) entityManager.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);
    }


    @Override
    public List<Product> getByCategory(Category category) {
        TypedQuery<Product> query = entityManager.
                createQuery("Select p from Product p where p.category=:category", Product.class)
                .setParameter("category", category);
        return query.getResultList();
    }


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
    public List<Product> getTopProducts(){
        TypedQuery<Product> query = entityManager.
                createQuery("Select p from Product p ORDER BY p.sales desc", Product.class);
        return query.setMaxResults(10).getResultList();
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
