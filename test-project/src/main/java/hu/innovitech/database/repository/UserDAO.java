package hu.innovitech.database.repository;

import hu.innovitech.database.entity.Address;
import hu.innovitech.database.entity.User;

import java.util.List;

public interface UserDAO extends DAO<User, Long>{

    User findByUsername(String username);

    String getRoles(String username);

    List<Address> getUserAddresses(String username);
}
