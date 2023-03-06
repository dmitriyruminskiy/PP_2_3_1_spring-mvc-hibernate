package web.dao;

import org.springframework.stereotype.Component;
import web.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
@PersistenceContext
private EntityManager entityManager;
    @Override
    public List<User> getAllUsers(){
        return entityManager.createQuery("select u from User u",
                User.class).getResultList();
    }

    @Override
    public User show (int id){
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user){
        entityManager.persist(user);
    }

    @Override
    public void update(User updateUser){entityManager.merge(updateUser);}

    @Override
    public void delete(int id){
        entityManager.remove(entityManager.find(User.class, id));
    }
}


