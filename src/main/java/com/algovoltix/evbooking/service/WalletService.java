package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.WalletRequest;
import com.algovoltix.evbooking.dto.response.WalletResponse;
import java.util.List;
import java.util.UUID;

public interface WalletService {
    WalletResponse createWallet(WalletRequest request);
    WalletResponse getWalletById(UUID id);
    List<WalletResponse> getAllWallets();
    WalletResponse updateWallet(UUID id, WalletRequest request);
    void deleteWallet(UUID id);
}
