package clientUI.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
    @Size(min=1, max=40)
    String firstname;
    @NotEmpty
    @NonNull
    @Size(min=1, max=40)
    String lastname;
    @NonNull
    String birthdate;
    @NonNull
    String gender;
    @NotEmpty
    @NonNull
    @Size(min=1, max=40)
    String address;
    @NotEmpty
    @NonNull
    @Size(min=1, max=40)
    String phone;
    UUID uuid;

}
