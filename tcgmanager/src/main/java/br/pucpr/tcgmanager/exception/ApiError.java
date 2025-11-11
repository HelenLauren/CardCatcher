package br.pucpr.tcgmanager.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {

    private HttpStatus status;
    private String message;
    private String path;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public static ApiError of(HttpStatus status, String message, String path) {
        return ApiError.builder()
                .status(status)
                .message(message)
                .path(path)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
