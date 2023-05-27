package org.boardpj.commons.rests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class JSONData<T> {
    private boolean success;
    private HttpStatus status=HttpStatus.OK; //200
    private String message;
    private T data;

}
