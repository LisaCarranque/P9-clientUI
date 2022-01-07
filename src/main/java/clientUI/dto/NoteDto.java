package clientUI.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.util.UUID;

/**
 * A Data Transfer Object to transfer data for notes
 */
@Generated
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NoteDto {

    String id;
    String content;
    UUID uuid;

}
