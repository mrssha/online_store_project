package store.dto;

import org.springframework.format.annotation.NumberFormat;
import store.entity.AddressType;

import javax.validation.constraints.*;
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

    private Boolean active;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressDto)) return false;
        AddressDto that = (AddressDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCountry(), that.getCountry()) &&
                Objects.equals(getCity(), that.getCity()) &&
                Objects.equals(getPostcode(), that.getPostcode()) &&
                Objects.equals(getStreet(), that.getStreet()) &&
                Objects.equals(getFlatNumber(), that.getFlatNumber()) &&
                Objects.equals(getHouseNumber(), that.getHouseNumber()) &&
                Objects.equals(getCustomer(), that.getCustomer()) &&
                getAddressType() == that.getAddressType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCountry(), getCity(), getPostcode(), getStreet(), getFlatNumber(), getHouseNumber(), getCustomer(), getAddressType());
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
