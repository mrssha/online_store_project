package dbservice.dao;

import dbservice.entity.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category getById(long id){
        return entityManager.find(Category.class, id);
    }

    @Override
    public Category getByName(String name){
        TypedQuery<Category> query = entityManager.
                createQuery("Select c from Category c where c.categoryName=:name", Category.class)
                .setParameter("name", name);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public List<Category> getAllCategories(){
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = cBuilder.createQuery(Category.class);
        Root<Category> root = criteriaQuery.from(Category.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void add(Category category){
        entityManager.persist(category);
    }

    @Override
    public void deleteById(long id){
        Category category = entityManager.find(Category.class, id);
        entityManager.remove(category);
    }

    @Override
    public void update(Category category){
        entityManager.merge(category);
    }
}
