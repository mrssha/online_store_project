package store.converter;

import store.dto.AddressDto;
import store.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("addressConverter")
public class AddressConverterImpl implements AddressConverter{

    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public AddressDto convertToDto(Address address) {
        if (address == null){
            return null;
        }
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setCountry(address.getCountry());
        addressDto.setCity(address.getCity());
        addressDto.setPostcode(address.getPostcode());
        addressDto.setStreet(address.getStreet());
        addressDto.setFlatNumber(address.getFlatNumber());
        addressDto.setHouseNumber(address.getHouseNumber());
        addressDto.setCustomer(customerConverter.convertToDto(address.getCustomer()));
        addressDto.setAddressType(address.getAddressType());
        return addressDto;
    }

    @Override
    public Address convertToEntity(AddressDto addressDto) {
        if (addressDto == null){
            return null;
        }
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setCountry(addressDto.getCountry());
        address.setCity(addressDto.getCity());
        address.setPostcode(addressDto.getPostcode());
        address.setStreet(addressDto.getStreet());
        address.setFlatNumber(addressDto.getFlatNumber());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setCustomer(customerConverter.convertToEntity(addressDto.getCustomer()));
        address.setAddressType(addressDto.getAddressType());
        return address;
    }

    @Override
    public List<AddressDto> convertToDtoList(List<Address> addresses) {
        List<AddressDto> addressesDto = new ArrayList<>();
        for (Address address: addresses){
            addressesDto.add(convertToDto(address));
        }
        return addressesDto;
    }

    @Override
    public List<Address> convertToEntityList(List<AddressDto> addressesDto) {
        List<Address> addresses = new ArrayList<>();
        for (AddressDto addressDto: addressesDto){
            addresses.add(convertToEntity(addressDto));
        }
        return addresses;
    }

}
