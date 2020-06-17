package hu.innovitech.utils;

import hu.innovitech.database.entity.Address;
import hu.innovitech.database.entity.User;
import hu.innovitech.web.vo.AddressVO;
import hu.innovitech.web.vo.UserVO;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static AddressVO addressEntityToVO(Address address) {
        AddressVO addressVO = new AddressVO();

        addressVO.setPostalCode(address.getPostalCode());
        addressVO.setCity(address.getCity());
        addressVO.setStreet(address.getStreet());
        addressVO.setHouse(address.getHouse());

        return addressVO;
    }

    public static Address addressVOToEntity(AddressVO addressVO) {
        Address address = new Address();

        address.setPostalCode(addressVO.getPostalCode());
        address.setCity(addressVO.getCity());
        address.setStreet(addressVO.getStreet());
        address.setHouse(addressVO.getHouse());

        return address;
    }

    public static UserVO userEntityToVO(User user) {
        UserVO userVO = new UserVO();

        userVO.setName(user.getName());
        userVO.setUsername(user.getUsername());
        userVO.setHashedPass(user.getHashedPass());
        userVO.setRole(user.getRole());

        List<AddressVO> addresses = user.getAddresses().stream().map(Converter::addressEntityToVO).collect(Collectors.toList());
        userVO.setAddresses(addresses);

        return userVO;
    }

    public static User userVOToEntity(UserVO userVO) {
        User user = new User();

        user.setUsername(userVO.getUsername());
        user.setHashedPass(userVO.getHashedPass());
        user.setName(userVO.getName());
        user.setRole(userVO.getRole());

        List<Address> addresses = userVO.getAddresses().stream().map(Converter::addressVOToEntity).collect(Collectors.toList());
        user.setAddresses(addresses);

        return user;
    }
}
