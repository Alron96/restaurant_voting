package com.restaurant_voting.to;

import com.restaurant_voting.HasIdAndEmail;
import com.restaurant_voting.util.validation.NoHtml;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTo implements HasIdAndEmail {

    private Integer id;

    @NotBlank
    @Size(max = 128)
    @NoHtml
    private String name;

    @Email
    @NotBlank
    @Size(max = 128)
    private String email;

    @NotBlank
    @Size(min = 6, max = 128)
    private String password;

    public UserTo(UserTo uTo) {
        this(uTo.id, uTo.name, uTo.email, uTo.password);
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + this.getId() +
                ", name='" + this.getName() +
                ", email='" + this.getEmail() +
                '}';
    }
}