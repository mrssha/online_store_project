package dbservice.service;

import dbservice.dto.AddressDto;

import java.util.List;


public interface AddressService {

    public AddressDto getAddressById(long id);

    public List<AddressDto> getAddressByCustomerId(long id);

    public void addAddress(AddressDto addressDto);

    public void updateAddress(AddressDto addressDto);

    public void deleteAddressById(long id);
}
