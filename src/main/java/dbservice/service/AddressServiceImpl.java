package dbservice.service;

import dbservice.converter.AddressConverter;
import dbservice.dao.AddressDao;
import dbservice.dto.AddressDto;
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
    public AddressDto getAddressById(long id) {
        return addressConverter.convertToDto(addressDao.getById(id));
    }

    @Override
    public List<AddressDto> getAddressByCustomerId(long id) {
        return addressConverter.convertToDtoList(addressDao.getByCustomerId(id));
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
