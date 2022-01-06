package clientUI.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

/**
 * The model for patients gathering their personal information
 */
@Generated
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    Integer id;
    @NotEmpty
    @NonNull
    String firstname;
    @NotEmpty
    @NonNull
    String lastname;
    @NonNull
    Gender gender;
    @NotEmpty
    @NonNull
    String address;
    @NotEmpty
    @NonNull
    String phone;
    @NonNull
    String birthdate;
    UUID uuid;

}
