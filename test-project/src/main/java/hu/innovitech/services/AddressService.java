package hu.innovitech.services;

import hu.innovitech.web.vo.AddressVO;

public interface AddressService {

    Boolean editAddress(AddressVO oldA, AddressVO newA);

    Boolean addAddress(AddressVO addressVO);
}
