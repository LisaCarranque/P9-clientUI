package clientUI.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

/**
 * The model for the report e.g. the outcome of the analysis of patient information and related notes
 */
@Generated
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    Probability probability;
    @NotEmpty
    @NonNull
    String firstname;
    @NotEmpty
    @NonNull
    String lastname;
    @NonNull
    String birthdate;
    @NonNull
    String gender;
    @NotEmpty
    @NonNull
    String address;
    @NotEmpty
    @NonNull
    String phone;
    UUID uuid;

}
