package clientUI.dto;

import clientUI.model.Gender;
import lombok.*;

import java.util.UUID;

/**
 * A Data Transfer Object to transfer data for patient
 */
@Generated
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    Integer id;
    String firstname;
    String lastname;
    Gender gender;
    String address;
    String phone;
    String birthdate;
    UUID uuid;

}
