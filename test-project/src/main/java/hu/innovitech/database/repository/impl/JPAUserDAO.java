package hu.innovitech.database.repository.impl;

import hu.innovitech.database.entity.Address;
import hu.innovitech.database.entity.User;
import hu.innovitech.database.repository.UserDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class JPAUserDAO extends JpaDao<User, Long> implements UserDAO {

    public JPAUserDAO() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        try {

            return entityManager.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public String getRoles(String username) {
        try {

            return entityManager.createQuery("SELECT u.role FROM User u WHERE u.username=:username", String.class)
                    .setParameter("username", username)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Address> getUserAddresses(String username) {
        try {

            return entityManager.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class)
                    .setParameter("username", username)
                    .getSingleResult()
                    .getAddresses();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
