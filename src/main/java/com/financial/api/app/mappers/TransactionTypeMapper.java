package com.financial.api.app.mappers;

import com.financial.api.app.requests.TransactionRequest;
import com.financial.api.app.responses.TransactionResponse;
import com.financial.api.app.responses.TransactionTypeResponse;
import com.financial.api.domain.transaction.model.Transaction;
import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionTypeMapper {

    public static TransactionTypeResponse fromTransactionTypeToTransactionTypeResponse(TransactionType transactionType) {
        return new TransactionTypeResponse(transactionType.id(), transactionType.description());
    }
}
