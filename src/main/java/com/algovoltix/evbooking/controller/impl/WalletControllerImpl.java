package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.WalletController;
import com.algovoltix.evbooking.dto.request.WalletRequest;
import com.algovoltix.evbooking.dto.response.WalletResponse;
import com.algovoltix.evbooking.service.WalletService;
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
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
@Tag(name = "Wallet", description = "APIs for managing user wallets")
public class WalletControllerImpl implements WalletController {
    private final WalletService walletService;

    @Override
    @Operation(summary = "Create Wallet", description = "Create a new wallet for a user.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Wallet created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<WalletResponse> createWallet(@RequestBody WalletRequest request) {
        return ResponseEntity.ok(walletService.createWallet(request));
    }

    @Override
    @Operation(summary = "Get Wallet by ID", description = "Retrieve a wallet by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Wallet found"),
        @ApiResponse(responseCode = "404", description = "Wallet not found")
    })
    public ResponseEntity<WalletResponse> getWalletById(@Parameter(description = "ID of the wallet", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(walletService.getWalletById(id));
    }

    @Override
    @Operation(summary = "Get All Wallets", description = "Retrieve all wallets.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of wallets")
    })
    public ResponseEntity<List<WalletResponse>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @Override
    @Operation(summary = "Update Wallet", description = "Update an existing wallet.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Wallet updated successfully"),
        @ApiResponse(responseCode = "404", description = "Wallet not found")
    })
    public ResponseEntity<WalletResponse> updateWallet(@PathVariable Long id, @RequestBody WalletRequest request) {
        return ResponseEntity.ok(walletService.updateWallet(id, request));
    }

    @Override
    @Operation(summary = "Delete Wallet", description = "Delete a wallet by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Wallet deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Wallet not found")
    })
    public ResponseEntity<Void> deleteWallet(@Parameter(description = "ID of the wallet", required = true) @PathVariable Long id) {
        walletService.deleteWallet(id);
        return ResponseEntity.noContent().build();
    }
}
