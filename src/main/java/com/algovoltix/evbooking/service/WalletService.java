package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.WalletRequest;
import com.algovoltix.evbooking.dto.response.WalletResponse;
import java.util.List;

public interface WalletService {
    WalletResponse createWallet(WalletRequest request);
    WalletResponse getWalletById(Long id);
    List<WalletResponse> getAllWallets();
    WalletResponse updateWallet(Long id, WalletRequest request);
    void deleteWallet(Long id);
}
