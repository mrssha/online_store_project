package store.dao;

import store.entity.Customer;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
        return entityManager.createQuery(criteriaQuery).getResultList().
                stream().findFirst().orElse(null);
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
    public List<Customer> getTopCustomers(){
        TypedQuery<Customer> query = entityManager.
                createQuery("Select c from Customer c ORDER BY c.sumPurchases desc", Customer.class);
        return query.setMaxResults(10).getResultList();
    }


    @Override
    public void add(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public void deleteById(long id) {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);

    }

    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);
    }
}
