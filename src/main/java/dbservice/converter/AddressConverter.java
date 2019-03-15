package dbservice.converter;

import dbservice.dto.AddressDto;
import dbservice.entity.Address;

import java.util.List;
import java.util.Set;

public interface AddressConverter {

    AddressDto convertToDto(Address product);

    Address convertToEntity(AddressDto addressDto);

    List<AddressDto> convertToDtoList(List<Address> addresses);

    List<Address> convertToEntityList(List<AddressDto> addressDto);


    /*
    Set<AddressDto> convertToDtoSet(Set<Address> addresses);

    Set<Address> convertToEntitySet(Set<AddressDto> addressDto);
    */
}
