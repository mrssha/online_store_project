package store.dao;

import store.entity.Address;
import store.entity.AddressType;
import store.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Repository
public class AddressDaoImpl implements AddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Address getById(long id){
        return entityManager.find(Address.class, id);
    }

    @Override
    public List<Address> getByCustomerId(long id) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> criteriaQuery = cBuilder.createQuery(Address.class);
        Root<Address> root = criteriaQuery.from(Address.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cBuilder.equal(root.get("customer"), id));
        predicates.add(cBuilder.equal(root.get("active"), true));
        criteriaQuery.select(root)
                .where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


    @Override
    public List<Address> getByAddressType(AddressType type){
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> criteriaQuery = cBuilder.createQuery(Address.class);
        Root<Address> root = criteriaQuery.from(Address.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("addressType"), type));
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
