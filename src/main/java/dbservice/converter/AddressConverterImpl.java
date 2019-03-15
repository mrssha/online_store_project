package dbservice.converter;

import dbservice.dto.AddressDto;
import dbservice.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("addressConverter")
public class AddressConverterImpl implements AddressConverter{

    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public AddressDto convertToDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setCountry(address.getCountry());
        addressDto.setCity(address.getCity());
        addressDto.setPostcode(address.getPostcode());
        addressDto.setStreet(address.getStreet());
        addressDto.setFlatNumber(address.getFlatNumber());
        addressDto.setHouseNumber(address.getHouseNumber());

        addressDto.setCustomer(customerConverter.convertToDto(address.getCustomer()));
        return addressDto;
    }

    @Override
    public Address convertToEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setCountry(addressDto.getCountry());
        address.setCity(addressDto.getCity());
        address.setPostcode(addressDto.getPostcode());
        address.setStreet(addressDto.getStreet());
        address.setFlatNumber(addressDto.getFlatNumber());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setCustomer(customerConverter.convertToEntity(addressDto.getCustomer()));
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


    /*
    @Override
    public Set<AddressDto> convertToDtoSet(Set<Address> addresses) {
        Set<AddressDto> addressesDto = new HashSet<>();
        for (Address address: addresses){
            addressesDto.add(convertToDto(address));
        }
        return addressesDto;
    }

    @Override
    public Set<Address> convertToEntitySet(Set<AddressDto> addressDto) {
        return null;
    }
    */
}
