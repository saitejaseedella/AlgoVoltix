package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.PriceAlgorithmRequest;
import com.algovoltix.evbooking.dto.response.PriceAlgorithmResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "Price Algorithm", description = "APIs for managing price algorithms")
@RequestMapping("/api/price-algorithms")
public interface PriceAlgorithmController {
    @Operation(summary = "Create Price Algorithm", description = "Create a new price algorithm.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Price algorithm created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    ResponseEntity<PriceAlgorithmResponse> createPriceAlgorithm(@RequestBody PriceAlgorithmRequest request);

    @Operation(summary = "Get Price Algorithm by ID", description = "Retrieve a price algorithm by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Price algorithm found"),
        @ApiResponse(responseCode = "404", description = "Price algorithm not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<PriceAlgorithmResponse> getPriceAlgorithmById(@Parameter(description = "ID of the price algorithm", required = true) @PathVariable UUID id);

    @Operation(summary = "Get All Price Algorithms", description = "Retrieve all price algorithms.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of price algorithms")
    })
    @GetMapping
    ResponseEntity<List<PriceAlgorithmResponse>> getAllPriceAlgorithms();

    @Operation(summary = "Update Price Algorithm", description = "Update an existing price algorithm.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Price algorithm updated successfully"),
        @ApiResponse(responseCode = "404", description = "Price algorithm not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<PriceAlgorithmResponse> updatePriceAlgorithm(@Parameter(description = "ID of the price algorithm", required = true) @PathVariable UUID id, @RequestBody PriceAlgorithmRequest request);

    @Operation(summary = "Delete Price Algorithm", description = "Delete a price algorithm by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Price algorithm deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Price algorithm not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePriceAlgorithm(@Parameter(description = "ID of the price algorithm", required = true) @PathVariable UUID id);
}
