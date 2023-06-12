package java2.eln.core.database;

import java2.eln.core.domain.ReactionData;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Component
@Transactional
public class OrmReactionRepositoryOldImpl implements ReactionRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addReaction(ReactionData reaction) {
        sessionFactory.getCurrentSession().save(reaction);
    }

    @Override
    public boolean delReactionByCode(String code) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete ReactionData where code = :code");
        query.setParameter("code", code);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean delReactionByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete ReactionData where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<ReactionData> getAllReactions() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT r FROM ReactionData r", ReactionData.class)
                .getResultList();
    }

    @Override
    public boolean hasReactionWithCode(String reactionCode) {
        return false;
    }

    @Override
    public boolean hasReactionWithId(int reactionId) {
        return false;
    }
}
