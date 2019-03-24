package dbservice.dto;

import java.util.*;

public class CustomerDto {

    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private Date birthDate;
    private String role;

    public CustomerDto(){
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
