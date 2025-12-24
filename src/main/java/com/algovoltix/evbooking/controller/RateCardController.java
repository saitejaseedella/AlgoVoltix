package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.RateCardRequest;
import com.algovoltix.evbooking.dto.response.RateCardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "Rate Card", description = "APIs for managing rate cards")
public interface RateCardController {
    @Operation(summary = "Create Rate Card", description = "Create a new rate card.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rate card created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    ResponseEntity<RateCardResponse> createRateCard(@RequestBody(description = "Rate card create request", required = true) RateCardRequest request);

    @Operation(summary = "Get Rate Card by ID", description = "Retrieve a rate card by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rate card found"),
        @ApiResponse(responseCode = "404", description = "Rate card not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<RateCardResponse> getRateCardById(@Parameter(description = "ID of the rate card", required = true) @PathVariable Long id);

    @Operation(summary = "Get All Rate Cards", description = "Retrieve all rate cards.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of rate cards")
    })
    @GetMapping
    ResponseEntity<List<RateCardResponse>> getAllRateCards();

    @Operation(summary = "Update Rate Card", description = "Update an existing rate card.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rate card updated successfully"),
        @ApiResponse(responseCode = "404", description = "Rate card not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<RateCardResponse> updateRateCard(@Parameter(description = "ID of the rate card", required = true) @PathVariable Long id, @RequestBody(description = "Rate card update request", required = true) RateCardRequest request);

    @Operation(summary = "Delete Rate Card", description = "Delete a rate card by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Rate card deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Rate card not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRateCard(@Parameter(description = "ID of the rate card", required = true) @PathVariable Long id);
}
