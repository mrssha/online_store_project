package store.converter;

import store.dto.AddressDto;
import store.entity.Address;

import java.util.List;

public interface AddressConverter {

    AddressDto convertToDto(Address product);

    Address convertToEntity(AddressDto addressDto);

    List<AddressDto> convertToDtoList(List<Address> addresses);

    List<Address> convertToEntityList(List<AddressDto> addressDto);

}
