package dbservice.dao;

import dbservice.entity.Address;
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
public class AddressDaoImpl implements AddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Address getById(long id){
        return entityManager.find(Address.class, id);
    }


    /*
    @Override
    public List<Address> getByCustomerEmail(String email) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> criteriaQuery = cBuilder.createQuery(Address.class);
        Root<Address> root = criteriaQuery.from(Address.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("email"), email));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    */

    @Override
    public List<Address> getByCustomerId(long id) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> criteriaQuery = cBuilder.createQuery(Address.class);
        Root<Address> root = criteriaQuery.from(Address.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("customer"), id));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Address> getByCustomer(Customer customer) {
        return getByCustomerId(customer.getId());
    }


    @Override
    public List<Address> getAll() {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> criteriaQuery = cBuilder.createQuery(Address.class);
        Root<Address> root = criteriaQuery.from(Address.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void add(Address address){
        entityManager.persist(address);

    }

    @Override
    public void deleteById(long id){
       Address address = entityManager.find(Address.class, id);
       entityManager.remove(address);

    }

    @Override
    public void update(Address address) {
        entityManager.merge(address);
    }
}
