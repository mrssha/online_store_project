package dbservice.dto;

import javax.validation.constraints.*;
import java.util.Date;

public class ChangeInfoDto {

    @NotEmpty(message = "First name shouldn't be empty")
    @Size(min = 2, max = 45, message = "Field should contain from 2 to 45 characters")
    private String firstName;

    @NotEmpty(message = "Second name shouldn't be empty")
    @Size(min = 2, max = 45, message = "Field should contain from 2 to 45 characters")
    private String secondName;

    //@DateTimeFormat(pattern="dd/MM/yyyy")
    @NotNull(message = "Date of birth shouldn't be empty")
    @Past(message = "Date of birth should be in past")
    private Date birthDate;

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
