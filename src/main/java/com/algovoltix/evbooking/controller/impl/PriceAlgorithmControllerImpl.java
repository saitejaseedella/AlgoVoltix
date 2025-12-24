package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.PriceAlgorithmController;
import com.algovoltix.evbooking.dto.request.PriceAlgorithmRequest;
import com.algovoltix.evbooking.dto.response.PriceAlgorithmResponse;
import com.algovoltix.evbooking.service.PriceAlgorithmService;
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
@RequestMapping("/api/pricealgorithms")
@RequiredArgsConstructor
@Tag(name = "Price Algorithm", description = "APIs for managing price algorithms")
public class PriceAlgorithmControllerImpl implements PriceAlgorithmController {
    private final PriceAlgorithmService priceAlgorithmService;

    @Override
    @Operation(summary = "Create Price Algorithm", description = "Create a new price algorithm.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Price algorithm created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<PriceAlgorithmResponse> createPriceAlgorithm(@RequestBody PriceAlgorithmRequest request) {
        return ResponseEntity.ok(priceAlgorithmService.createPriceAlgorithm(request));
    }

    @Override
    @Operation(summary = "Get Price Algorithm by ID", description = "Retrieve a price algorithm by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Price algorithm found"),
        @ApiResponse(responseCode = "404", description = "Price algorithm not found")
    })
    public ResponseEntity<PriceAlgorithmResponse> getPriceAlgorithmById(@Parameter(description = "ID of the price algorithm", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(priceAlgorithmService.getPriceAlgorithmById(id));
    }

    @Override
    @Operation(summary = "Get All Price Algorithms", description = "Retrieve all price algorithms.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of price algorithms")
    })
    public ResponseEntity<List<PriceAlgorithmResponse>> getAllPriceAlgorithms() {
        return ResponseEntity.ok(priceAlgorithmService.getAllPriceAlgorithms());
    }

    @Override
    @Operation(summary = "Update Price Algorithm", description = "Update an existing price algorithm.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Price algorithm updated successfully"),
        @ApiResponse(responseCode = "404", description = "Price algorithm not found")
    })
    public ResponseEntity<PriceAlgorithmResponse> updatePriceAlgorithm(@PathVariable Long id, @RequestBody PriceAlgorithmRequest request) {
        return ResponseEntity.ok(priceAlgorithmService.updatePriceAlgorithm(id, request));
    }

    @Override
    @Operation(summary = "Delete Price Algorithm", description = "Delete a price algorithm by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Price algorithm deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Price algorithm not found")
    })
    public ResponseEntity<Void> deletePriceAlgorithm(@Parameter(description = "ID of the price algorithm", required = true) @PathVariable Long id) {
        priceAlgorithmService.deletePriceAlgorithm(id);
        return ResponseEntity.noContent().build();
    }
}
