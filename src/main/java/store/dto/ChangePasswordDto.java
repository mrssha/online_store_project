package store.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ChangePasswordDto {

    //@Size(min = 6, max = 45, result = "Field should contain from 6 to 45 characters")
    @NotEmpty(message = "Old password shouldn't be empty")
    private String oldPassword;

    @Size(min = 6, max = 45, message = "Field should contain from 6 to 45 characters")
    @NotEmpty(message = "New password shouldn't be empty")
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
