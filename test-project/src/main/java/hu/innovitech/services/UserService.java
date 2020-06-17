package hu.innovitech.services;


import hu.innovitech.web.vo.AddressVO;
import hu.innovitech.web.vo.UserVO;

import javax.swing.text.StyledEditorKit;
import java.util.List;

public interface UserService {

    boolean login(UserVO userVO);

    boolean register(UserVO userVO);

    List<UserVO> getAllUsers();

    UserVO getUser(String username);

    Boolean updateUser(UserVO userVO);

    String getRoles(String username);

    List<AddressVO> getUserAddresses(String username);

    Boolean deleteUser(String username);

    Boolean addAddress(String username, AddressVO addressVO);

    Boolean deleteAddress(String username, AddressVO addressVO);
}
