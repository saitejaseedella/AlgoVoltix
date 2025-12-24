package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.TransactionRequest;
import com.algovoltix.evbooking.dto.response.TransactionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "Transaction", description = "APIs for managing transactions")
public interface TransactionController {
    @Operation(summary = "Create Transaction", description = "Create a new transaction.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest request);

    @Operation(summary = "Get Transaction by ID", description = "Retrieve a transaction by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction found"),
        @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<TransactionResponse> getTransactionById(@Parameter(description = "ID of the transaction", required = true) @PathVariable Long id);

    @Operation(summary = "Get All Transactions", description = "Retrieve all transactions.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of transactions")
    })
    @GetMapping
    ResponseEntity<List<TransactionResponse>> getAllTransactions();

    @Operation(summary = "Update Transaction", description = "Update an existing transaction.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction updated successfully"),
        @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<TransactionResponse> updateTransaction(@Parameter(description = "ID of the transaction", required = true) @PathVariable Long id, @RequestBody TransactionRequest request);

    @Operation(summary = "Delete Transaction", description = "Delete a transaction by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Transaction deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTransaction(@Parameter(description = "ID of the transaction", required = true) @PathVariable Long id);
}
