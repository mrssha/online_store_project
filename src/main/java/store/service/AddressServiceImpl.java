package store.service;

import store.converter.AddressConverter;
import store.dao.AddressDao;
import store.dto.AddressDto;
import store.entity.AddressType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private AddressConverter addressConverter;


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
        addressDao.add(addressConverter.convertToEntity(addressDto));
    }

    @Override
    @Transactional
    public void updateAddress(AddressDto addressDto) {
        addressDao.update(addressConverter.convertToEntity(addressDto));
    }

    @Override
    @Transactional
    public void deleteAddressById(long id) {
        addressDao.deleteById(id);
    }
}