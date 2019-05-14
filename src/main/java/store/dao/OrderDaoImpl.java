package store.dao;

import store.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order getById(long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> getByCustomerId(long id) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = cBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("customer"), id));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


    @Override
    public List<Order> getAllOrders() {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = cBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Order> getByDate(Date date){
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = cBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cBuilder.equal(root.get("dateOrder"), date));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Double getRevenueForPeriod(Integer year, String month){
        Month m = Month.valueOf(month);
        YearMonth ym = YearMonth.of(year, m);
        LocalDate firstDay = ym.atDay(1);
        Date date1 =  java.sql.Date.valueOf(firstDay);
        LocalDate lastDay = ym.atEndOfMonth();
        Date date2 = java.sql.Date.valueOf(lastDay);

        Query query2 = entityManager.
                createQuery("Select sum(o.payment_amount) from Order o where o.orderStatus='DELIVERED'" +
                        " and o.dateOrder between :startDate and :endDate")
                .setParameter("startDate", date1, TemporalType.DATE)
                .setParameter("endDate", date2, TemporalType.DATE);
        return (Double) query2.getSingleResult();
    }

    @Override
    public Double getRevenueForLastWeek(){
        Date now = new Date();
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        Date weekAgo = cal.getTime();
        Query query2 = entityManager.
                createQuery("Select sum(o.payment_amount) from Order o where o.orderStatus='DELIVERED'" +
                        " and o.dateOrder between :startDate and :endDate")
                .setParameter("startDate", weekAgo, TemporalType.DATE)
                .setParameter("endDate", now, TemporalType.DATE);
        return (Double) query2.getSingleResult();
    }

    @Override
    public void add(Order order) {
        entityManager.persist(order);
    }

    @Override
    public void deleteById(long id) {
        Order order = entityManager.find(Order.class, id);
        entityManager.remove(order);
    }

    @Override
    public void update(Order order) {
        entityManager.merge(order);
    }
}
