package lv.javaguru.java2.servify.core.database;
import lv.javaguru.java2.servify.core.domain.Detail;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

//@Repository
//@Transactional
public class OrmDetailRepositoryImpl implements DetailRepository {
    //@Autowired
    //private SessionFactory sessionFactory;
    @Override
    public void save(Detail detail) {
        //sessionFactory.getCurrentSession().save(detail);
    }

    @Override
    public boolean deleteById(Long id) {
//        Query query = sessionFactory.getCurrentSession().createQuery(
//                "DELETE Detail WHERE id = :id");
//        query.setParameter("id", id);
//        int result = query.executeUpdate();
//        return result == 1;
        return false;
    }

    @Override
    public List<Detail> getAllDetails() {
//        return sessionFactory.getCurrentSession()
//                .createQuery("SELECT d FROM Detail d", Detail.class)
//                .getResultList();
        return null;
    }

    @Override
    public BigDecimal getTotalPrice(List<Detail> listWithPrices) {
        return null;
    }
}

