package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
@PersistenceContext
private EntityManager entityManager;
    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers(){
        return entityManager.createQuery("select u from User u",
                User.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User show (int id){
        TypedQuery<User> q =
                entityManager.createQuery("select u from User u where u.id = :id", User.class);
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void save(User user){
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(User updateUser){entityManager.merge(updateUser);}

    @Override
    @Transactional
    public void delete(int id){
        entityManager.remove(entityManager.find(User.class, id));
    }
}


