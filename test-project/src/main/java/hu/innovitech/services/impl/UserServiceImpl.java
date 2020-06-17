package hu.innovitech.services.impl;

import hu.innovitech.database.entity.Address;
import hu.innovitech.database.entity.User;
import hu.innovitech.database.repository.AddressDAO;
import hu.innovitech.database.repository.UserDAO;
import hu.innovitech.services.UserService;
import hu.innovitech.services.exceptions.IncorrectPasswordException;
import hu.innovitech.services.exceptions.NoSuchUserException;
import hu.innovitech.services.exceptions.UsernameTakenException;
import hu.innovitech.utils.Converter;
import hu.innovitech.web.vo.AddressVO;
import hu.innovitech.web.vo.UserVO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private AddressDAO addressDAO;

    @Override
    public boolean login(UserVO userVO) {
        User user = userDAO.findByUsername(userVO.getUsername());

        if(user != null) {
            if(user.getHashedPass().equals(userVO.getHashedPass())) {
                return true;
            } else {
                throw new IncorrectPasswordException();
            }
        } else {
            throw new NoSuchUserException();
        }
    }

    @Override
    public boolean register(UserVO userVO) {
        User user = userDAO.findByUsername(userVO.getUsername());

        if(user == null) {

            user = Converter.userVOToEntity(userVO);
            user.setHashedPass(userVO.getHashedPass());

            userDAO.persist(user);

            return true;

        } else {
            throw new UsernameTakenException();
        }
    }

    @Override
    public List<UserVO> getAllUsers() {
        return userDAO.findAll().stream()
                .map(Converter::userEntityToVO)
                .collect(Collectors.toList());
    }

    @Override
    public UserVO getUser(String username) {
        return Converter.userEntityToVO(userDAO.findByUsername(username));
    }

    @Override
    public Boolean updateUser(UserVO userVO) {
        User user = userDAO.findByUsername(userVO.getUsername());
        user.setRole(userVO.getRole());

        userDAO.update(user);

        return true;
    }

    @Override
    public String getRoles(String username) {
        return userDAO.getRoles(username);
    }

    @Override
    public List<AddressVO> getUserAddresses(String username) {
        System.out.println("UserService: " + username);
        return userDAO.getUserAddresses(username).stream()
                .map(Converter::addressEntityToVO)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteUser(String username) {
        userDAO.remove(userDAO.findByUsername(username));
        return true;
    }

    @Override
    public Boolean addAddress(String username, AddressVO addressVO) {
        User user = userDAO.findByUsername(username);
        Address address = addressDAO.getEntity(addressVO);

        user.getAddresses().add(address);
        userDAO.update(user);

        return true;
    }

    @Override
    public Boolean deleteAddress(String username, AddressVO addressVO) {
        User user = userDAO.findByUsername(username);
        Address address = addressDAO.getEntity(addressVO);

        user.getAddresses().remove(address);
        userDAO.update(user);

        return true;
    }
}
