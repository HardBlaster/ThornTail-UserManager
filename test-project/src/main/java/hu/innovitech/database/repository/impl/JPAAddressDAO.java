package hu.innovitech.database.repository.impl;

import hu.innovitech.database.entity.Address;
import hu.innovitech.database.entity.User;
import hu.innovitech.database.repository.AddressDAO;
import hu.innovitech.web.vo.AddressVO;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import java.util.List;

@ApplicationScoped
public class JPAAddressDAO extends JpaDao<Address, Long> implements AddressDAO {

    public JPAAddressDAO() {
        super(Address.class);
    }

    @Override
    public List<Address> findByUser(User user) {
        try {
            return entityManager.createQuery("SELECT u.addresses FROM User u WHERE u=:user", Address.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Address getEntity(AddressVO addressVO) {
        try {
            return entityManager.createQuery("SELECT a FROM Address a WHERE a.postalCode=:pc AND a.city=:city AND a.street=:street AND a.house=:house", Address.class)
                    .setParameter("pc", addressVO.getPostalCode())
                    .setParameter("city", addressVO.getCity())
                    .setParameter("street", addressVO.getStreet())
                    .setParameter("house", addressVO.getHouse())
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
