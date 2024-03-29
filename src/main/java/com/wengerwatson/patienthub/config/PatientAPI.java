package com.wengerwatson.patienthub.config;

import com.wengerwatson.patienthub.model.Patient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Patient Hub API", description = "Patient Hub management APIs")
public interface PatientAPI {

    @Operation(
            summary = "Fetch all Patients",
            description = "fetches all Patient entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Patient.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<List<Patient>> getAll();
}
