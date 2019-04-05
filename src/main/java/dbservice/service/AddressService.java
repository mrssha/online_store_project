package dbservice.service;

import dbservice.dto.AddressDto;
import dbservice.entity.AddressType;

import java.util.List;


public interface AddressService {

    AddressDto getAddressById(long id);

    List<AddressDto> getAddressByCustomerId(long id);

    List<AddressDto> getByAddressType(AddressType type);

    void addAddress(AddressDto addressDto);

    void updateAddress(AddressDto addressDto);

    void deleteAddressById(long id);
}
