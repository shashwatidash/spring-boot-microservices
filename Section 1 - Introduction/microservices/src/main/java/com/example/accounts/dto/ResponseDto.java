package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Schema to hold standard API response")
public class ResponseDto {

    @Schema(description = "Status code of the API response", example = "200")
    private String statusCode;

    @Schema(description = "Status message of the API response", example = "Success")
    private String statusMessage;
}
