package rs.ac.uns.ftn.clinic.payload;

import java.util.Set;

import javax.validation.constraints.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.clinic.model.Role;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequest {
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String firstName;

    @NotBlank
    @Size(max = 40)
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @Email
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String insuranceNumber;

    @NotBlank
    private Boolean isVerified;

    @NotNull
    private Set<Role> roles;
}
