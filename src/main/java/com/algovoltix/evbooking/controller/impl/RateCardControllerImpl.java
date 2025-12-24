package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.RateCardController;
import com.algovoltix.evbooking.dto.request.RateCardRequest;
import com.algovoltix.evbooking.dto.response.RateCardResponse;
import com.algovoltix.evbooking.service.RateCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratecards")
@RequiredArgsConstructor
@Tag(name = "Rate Card", description = "APIs for managing rate cards")
public class RateCardControllerImpl implements RateCardController {
    private final RateCardService rateCardService;

    @Override
    @Operation(summary = "Create Rate Card", description = "Create a new rate card.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rate card created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<RateCardResponse> createRateCard(@RequestBody RateCardRequest request) {
        return ResponseEntity.ok(rateCardService.createRateCard(request));
    }

    @Override
    @Operation(summary = "Get Rate Card by ID", description = "Retrieve a rate card by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rate card found"),
        @ApiResponse(responseCode = "404", description = "Rate card not found")
    })
    public ResponseEntity<RateCardResponse> getRateCardById(@Parameter(description = "ID of the rate card", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(rateCardService.getRateCardById(id));
    }

    @Override
    @Operation(summary = "Get All Rate Cards", description = "Retrieve all rate cards.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of rate cards")
    })
    public ResponseEntity<List<RateCardResponse>> getAllRateCards() {
        return ResponseEntity.ok(rateCardService.getAllRateCards());
    }

    @Override
    @Operation(summary = "Update Rate Card", description = "Update an existing rate card.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rate card updated successfully"),
        @ApiResponse(responseCode = "404", description = "Rate card not found")
    })
    public ResponseEntity<RateCardResponse> updateRateCard(@PathVariable Long id, @RequestBody RateCardRequest request) {
        return ResponseEntity.ok(rateCardService.updateRateCard(id, request));
    }

    @Override
    @Operation(summary = "Delete Rate Card", description = "Delete a rate card by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Rate card deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Rate card not found")
    })
    public ResponseEntity<Void> deleteRateCard(@Parameter(description = "ID of the rate card", required = true) @PathVariable Long id) {
        rateCardService.deleteRateCard(id);
        return ResponseEntity.noContent().build();
    }
}
