package lv.javaguru.java2.servify.core.database;

import org.springframework.transaction.annotation.Transactional;
import lv.javaguru.java2.servify.core.domain.Color;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ColorRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Color> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Color c", Color.class)
                .getResultList();
    }
}
