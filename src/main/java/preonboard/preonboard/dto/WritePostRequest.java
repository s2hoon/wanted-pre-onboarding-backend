package preonboard.preonboard.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WritePostRequest {

    private String title;

    private String content;

}
