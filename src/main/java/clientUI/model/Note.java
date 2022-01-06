package clientUI.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * The model of note added to patient history for further analysis
 */
@Generated
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Note {

    String id;
    @NotEmpty
    @NonNull
    @Size(min=1)
    String content;
    UUID uuid;

}
