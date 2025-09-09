package com.example.microservices.dto;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Schema to hold standard API error response")
public class ErrorResponseDto {

    @Schema(description = "API path where the error occurred", example = "/api/account")
    private String apiPath;

    @Schema(description = "HTTP status code of the API error response", example = "400")
    private HttpStatus status;

    @Schema(description = "HTTP status message of the API error response", example = "Bad Request")
    private String errorMessage;

    @Schema(description = "Timestamp when the error occurred", example = "2024-06-15T14:30:00")
    private LocalDateTime timestamp;
}
