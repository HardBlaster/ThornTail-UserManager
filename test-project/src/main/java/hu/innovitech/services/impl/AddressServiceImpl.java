package hu.innovitech.services.impl;

import hu.innovitech.database.entity.Address;
import hu.innovitech.database.repository.AddressDAO;
import hu.innovitech.services.AddressService;
import hu.innovitech.utils.Converter;
import hu.innovitech.web.vo.AddressVO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddressServiceImpl implements AddressService {

    @Inject
    AddressDAO addressDAO;

    @Override
    public Boolean editAddress(AddressVO oldA, AddressVO newA){
        Address address = addressDAO.getEntity(oldA);
        address.setPostalCode(newA.getPostalCode());
        address.setCity(newA.getCity());
        address.setStreet(newA.getStreet());
        address.setHouse(newA.getHouse());

        addressDAO.update(address);

        return true;
    }

    @Override
    public Boolean addAddress(AddressVO addressVO) {
        addressDAO.persist(Converter.addressVOToEntity(addressVO));

        return true;
    }


}
