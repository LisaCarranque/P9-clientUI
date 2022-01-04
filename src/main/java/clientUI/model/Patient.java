package clientUI.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

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
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Patient {

    Integer id;
    String firstname;
    String lastname;
    String genre;
    String address;
    String phone;
    String birthdate;
    UUID uuid;

}
