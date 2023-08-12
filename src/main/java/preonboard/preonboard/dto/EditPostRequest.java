package preonboard.preonboard.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditPostRequest {
    @JsonProperty
    private Long id;

    @JsonProperty
    private String title;
    @JsonProperty
    private String content;


}
