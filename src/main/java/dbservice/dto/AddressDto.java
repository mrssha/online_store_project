package dbservice.dto;

import dbservice.entity.AddressType;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class AddressDto {

    private Long id;

    @NotEmpty(message = "Country shouldn't be empty")
    private String country;

    @NotEmpty(message = "City shouldn't be empty")
    private String city;

    @NotNull(message = "Postcode shouldn't be empty")
    @Positive
    private Integer postcode;

    @NotEmpty(message = "Street shouldn't be empty")
    private String street;

    @NotNull(message = "Flat number shouldn't be empty")
    @Positive(message = "Flat number should be greater than 0")
    private Integer flatNumber;

    @NotNull(message = "House number shouldn't be empty")
    @Positive(message = "House number should be greater than 0")
    private Integer houseNumber;

    private CustomerDto customer;

    private AddressType addressType;

    public AddressDto(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || obj.getClass()!= AddressDto.class){
            return false;
        }
        AddressDto address = (AddressDto) obj;
        return address.getId().equals(this.getId());
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postcode=" + postcode +
                ", street='" + street + '\'' +
                ", flatNumber=" + flatNumber +
                ", houseNumber=" + houseNumber +
                '}';
    }
}
