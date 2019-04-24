package dbservice.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.*;

public class CustomerDto {

    private Long id;

    @NotEmpty(message = "First name shouldn't be empty")
    @Size(min = 2, max = 45, message = "Field should contain from 2 to 45 characters")
    private String firstName;

    @NotEmpty(message = "Second name shouldn't be empty")
    @Size(min = 2, max = 45, message = "Field should contain from 2 to 45 characters")
    private String secondName;

    @NotEmpty(message = "Email shouldn't be empty")
    @Email(message = "Please, enter a valid email")
    private String email;

    @Size(min = 6, max = 45, message = "Password should contain from 6 to 45 characters")
    @NotEmpty(message = "Password shouldn't be empty")
    private String password;

    @NotEmpty(message = "Please, repeat password")
    private String confirmedPassword;

    @AssertTrue(message = "Passwords are not equal")
    private boolean isValid() {
        return this.password.equals(this.confirmedPassword);
    }

    private boolean valid;

    //@DateTimeFormat(pattern="dd/MM/yyyy")
    @NotNull(message = "Date of birth shouldn't be empty")
    @Past(message = "Date of birth should be in past")
    private Date birthDate;

    private Double sumPurchases;

    private String role;

    public CustomerDto(){
    }

    public boolean getValid(){
        return valid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Double getSumPurchases() {
        return sumPurchases;
    }

    public void setSumPurchases(Double sumPurchases) {
        this.sumPurchases = sumPurchases;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        if (obj == null || obj.getClass() != CustomerDto.class){
            return false;
        }
        CustomerDto customer =(CustomerDto)obj;
        return customer.getId().equals(this.getId());
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", role='" + role + '\'' +
                '}';
    }
}
