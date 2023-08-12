package preonboard.preonboard.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberLoginRequest {

    @JsonProperty
    private String email;
    @JsonProperty
    private String password;
}
