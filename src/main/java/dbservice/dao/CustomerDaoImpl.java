package dbservice.dao;

import dbservice.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer getById(long id)  {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Customer getByEmail(String email){
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = cBuilder.createQuery(Customer.class);
        Root <Customer> customerRoot = criteriaQuery.from(Customer.class);
        criteriaQuery.select(customerRoot);
        criteriaQuery.where(cBuilder.equal(customerRoot.get("email"), email));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<Customer> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = cb.createQuery(Customer.class);
        Root <Customer> root = criteriaQuery.from(Customer.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional
    public void add(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);

    }

    @Override
    @Transactional
    public void update(Customer customer) {
        entityManager.merge(customer);
    }
}
