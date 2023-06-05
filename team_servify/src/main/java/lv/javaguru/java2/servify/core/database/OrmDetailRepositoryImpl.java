package lv.javaguru.java2.servify.core.database;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lv.javaguru.java2.servify.core.domain.Detail;

import java.math.BigDecimal;
import java.util.Optional;


@Repository
@Transactional
public class OrmDetailRepositoryImpl implements DetailRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Detail detail) {
        sessionFactory.getCurrentSession().save(detail);
    }

    @Override
    public void update(Detail detail) {
        sessionFactory.getCurrentSession().update(detail);
    }


    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Detail WHERE id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public Optional<Detail> findById(Long id) {
        var detail = sessionFactory.getCurrentSession().get(Detail.class, id);
        return Optional.ofNullable(detail);


    }

    @Override
    public List<Detail> getAllDetails() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT d FROM Detail d", Detail.class)
                .getResultList();
    }

    @Override
    public List<Detail> findByDetailType(String detailType) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Detail d WHERE type = :type", Detail.class);
        query.setParameter("type", detailType);
        return query.getResultList();
    }

    @Override
    public List<Detail> findByDetailSide(String detailSide) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Detail d WHERE side =:side", Detail.class);
        query.setParameter("side", detailSide);
        return query.getResultList();
    }

    @Override
    public List<Detail> findByDetailTypeSide(String detailType, String detailSide) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Detail d WHERE side =:side AND  type = :type", Detail.class);
        query.setParameter("side", detailSide);
        query.setParameter("type", detailType);
        return query.getResultList();
    }

    @Override
    public List<Detail> findByDetailPrice(BigDecimal detailPrice) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Detail d WHERE price =:price", Detail.class);
        query.setParameter("price", detailPrice);
        return query.getResultList();
    }

    @Override
    public List<Detail> findByDetailTypePrice(String detailType, BigDecimal detailPrice) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Detail d WHERE type = :type AND price =:price", Detail.class);
        query.setParameter("type", detailType);
        query.setParameter("price", detailPrice);
        return query.getResultList();
    }

    @Override
    public List<Detail> findByDetailSidePrice(String detailSide, BigDecimal detailPrice) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Detail d WHERE side = :side AND price =:price", Detail.class);
        query.setParameter("side", detailSide);
        query.setParameter("price", detailPrice);
        return query.getResultList();
    }

}

