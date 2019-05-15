package store.service;

import org.apache.log4j.Logger;
import store.converter.AddressConverter;
import store.dao.AddressDao;
import store.dto.AddressDto;
import store.entity.Address;
import store.entity.AddressType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.utils.LogMessage;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private AddressConverter addressConverter;

    private static final Logger logger = Logger.getLogger(AddressServiceImpl.class);


    @Override
    @Transactional
    public AddressDto getAddressById(long id) {
        return addressConverter.convertToDto(addressDao.getById(id));
    }

    @Override
    @Transactional
    public List<AddressDto> getAddressByCustomerId(long id) {
        return addressConverter.convertToDtoList(addressDao.getByCustomerId(id));
    }

    @Override
    @Transactional
    public List<AddressDto> getByAddressType(AddressType type){
        return addressConverter.convertToDtoList(addressDao.getByAddressType(type));
    }

    @Override
    @Transactional
    public void addAddress(AddressDto addressDto) {
        addressDto.setAddressType(AddressType.CUSTOMER);
        addressDto.setActive(true);
        addressDao.add(addressConverter.convertToEntity(addressDto));
        logger.info(LogMessage.ADDRESS_ADD_SUCCESS);
    }

    @Override
    @Transactional
    public void updateAddress(AddressDto addressDto) {
        addressDao.update(addressConverter.convertToEntity(addressDto));
    }

    @Override
    @Transactional
    public void setActiveFalse(long id){
        Address address = addressDao.getById(id);
        address.setActive(false);
        addressDao.update(address);
    }

    @Override
    @Transactional
    public void deleteAddressById(long id) {
        addressDao.deleteById(id);
    }
}
