package hello.login.web.member.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class MemberSaveForm {

    @NotBlank
    @Length(min = 1, max = 10)
    private String username;

    @NotBlank
    @Length(max = 10)
    private String loginId;

    @NotBlank
    @Length(max = 10)
    private String password;

    @NotBlank
    @Length(max = 10)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
