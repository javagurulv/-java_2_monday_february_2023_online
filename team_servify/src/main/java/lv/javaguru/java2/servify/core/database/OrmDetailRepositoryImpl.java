package lv.javaguru.java2.servify.core.database;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lv.javaguru.java2.servify.core.domain.Detail;
import java.math.BigDecimal;


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
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Detail WHERE id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
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
                "SELECT d FROM Detail d WHERE detail_type = :detail_type");
        query.setParameter("detail_type", detailType);
        return query.getResultList();
    }

    @Override
    public List<Detail> findByDetailSide(String detailSide) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Detail d WHERE detail_side =:detail_side");
        query.setParameter("detail_side", detailSide);
        return query.getResultList();
    }

    @Override
    public List<Detail> findByDetailTypeSide(String detailType, String detailSide) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Detail d WHERE detail_side =:detailSide AND  detail_type = :detailType");
        query.setParameter("detail_side", detailSide);
        query.setParameter("detail_type", detailType);
        return query.getResultList();
    }

    @Override
    public List<Detail> findByDetailPrice(BigDecimal detailPrice) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Detail d WHERE price =:price");
        query.setParameter("price", detailPrice);
        return query.getResultList();
    }
}

