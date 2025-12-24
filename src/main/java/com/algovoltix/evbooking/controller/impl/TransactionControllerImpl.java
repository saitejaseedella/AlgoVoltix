package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.TransactionController;
import com.algovoltix.evbooking.dto.request.TransactionRequest;
import com.algovoltix.evbooking.dto.response.TransactionResponse;
import com.algovoltix.evbooking.service.TransactionService;
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
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Tag(name = "Transaction", description = "APIs for managing transactions")
public class TransactionControllerImpl implements TransactionController {
    private final TransactionService transactionService;

    @Override
    @Operation(summary = "Create Transaction", description = "Create a new transaction.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionService.createTransaction(request));
    }

    @Override
    @Operation(summary = "Get Transaction by ID", description = "Retrieve a transaction by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction found"),
        @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    public ResponseEntity<TransactionResponse> getTransactionById(@Parameter(description = "ID of the transaction", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @Override
    @Operation(summary = "Get All Transactions", description = "Retrieve all transactions.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of transactions")
    })
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @Override
    @Operation(summary = "Update Transaction", description = "Update an existing transaction.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction updated successfully"),
        @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    public ResponseEntity<TransactionResponse> updateTransaction(@PathVariable Long id, @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionService.updateTransaction(id, request));
    }

    @Override
    @Operation(summary = "Delete Transaction", description = "Delete a transaction by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Transaction deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    public ResponseEntity<Void> deleteTransaction(@Parameter(description = "ID of the transaction", required = true) @PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
