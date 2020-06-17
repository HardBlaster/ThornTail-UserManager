package hu.innovitech.database.repository;

import hu.innovitech.database.entity.Address;
import hu.innovitech.database.entity.User;
import hu.innovitech.web.vo.AddressVO;

import java.util.List;

public interface AddressDAO extends DAO<Address, Long> {

    List<Address> findByUser(User user);

    Address getEntity(AddressVO addressVO);
}
