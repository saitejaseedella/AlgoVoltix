package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.WalletRequest;
import com.algovoltix.evbooking.dto.response.WalletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "Wallet", description = "APIs for managing user wallets")
public interface WalletController {
    @Operation(summary = "Create Wallet", description = "Create a new wallet for a user.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Wallet created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    ResponseEntity<WalletResponse> createWallet(@RequestBody WalletRequest request);

    @Operation(summary = "Get Wallet by ID", description = "Retrieve a wallet by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Wallet found"),
        @ApiResponse(responseCode = "404", description = "Wallet not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<WalletResponse> getWalletById(@Parameter(description = "ID of the wallet", required = true) @PathVariable Long id);

    @Operation(summary = "Get All Wallets", description = "Retrieve all wallets.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of wallets")
    })
    @GetMapping
    ResponseEntity<List<WalletResponse>> getAllWallets();

    @Operation(summary = "Update Wallet", description = "Update an existing wallet.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Wallet updated successfully"),
        @ApiResponse(responseCode = "404", description = "Wallet not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<WalletResponse> updateWallet(@Parameter(description = "ID of the wallet", required = true) @PathVariable Long id, @RequestBody WalletRequest request);

    @Operation(summary = "Delete Wallet", description = "Delete a wallet by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Wallet deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Wallet not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteWallet(@Parameter(description = "ID of the wallet", required = true) @PathVariable Long id);
}
