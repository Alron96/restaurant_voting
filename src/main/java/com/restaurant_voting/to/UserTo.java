package com.restaurant_voting.to;

import com.restaurant_voting.HasIdAndEmail;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTo implements HasIdAndEmail, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank
    @Size(max = 128)
    private String name;

    @Email
    @NotBlank
    @Size(max = 128)
    private String email;

    @NotBlank
    @Size(min = 6, max = 128)
    private String password;

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + this.getId() +
                ", name='" + this.getName() +
                ", email='" + this.getEmail() +
                '}';
    }
}