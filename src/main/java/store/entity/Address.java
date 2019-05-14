package store.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "id_address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postcode")
    private Integer postcode;

    @Column(name = "street")
    private String street;

    @Column(name = "flat_number")
    private Integer flatNumber;

    @Column(name = "house_number")
    private Integer houseNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType;


    public Address(){
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getId(), address.getId()) &&
                Objects.equals(getCustomer(), address.getCustomer()) &&
                Objects.equals(getCountry(), address.getCountry()) &&
                Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getPostcode(), address.getPostcode()) &&
                Objects.equals(getStreet(), address.getStreet()) &&
                Objects.equals(getFlatNumber(), address.getFlatNumber()) &&
                Objects.equals(getHouseNumber(), address.getHouseNumber()) &&
                getAddressType() == address.getAddressType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getCountry(), getCity(), getPostcode(),
                getStreet(), getFlatNumber(), getHouseNumber(), getAddressType());
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
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
