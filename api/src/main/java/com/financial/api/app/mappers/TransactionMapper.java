package com.financial.api.app.mappers;

import com.financial.api.app.requests.TransactionRequest;
import com.financial.api.app.responses.TransactionResponse;
import com.financial.api.domain.transaction.model.Transaction;
import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionMapper {

    public static Transaction fromTransactionRequestToTransaction(TransactionRequest transactionRequest, String transactionId, String accountId) {
        return new Transaction(
                    transactionId == null ? UUID.randomUUID().toString() : transactionId,
                    transactionRequest.description(),
                    transactionRequest.date(),
                    transactionRequest.value(),
                    transactionRequest.extraDescription(),
                    accountId,
                    new TransactionType(transactionRequest.transactionTypeId(),""),
                    new TransactionCategory(transactionRequest.transactionCategoryId(), ""),
                    LocalDateTime.now(),
                    LocalDateTime.now()
                );
    }

    public static TransactionResponse fromTransactionToTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.id(),
                transaction.description(),
                transaction.date(),
                transaction.value(),
                transaction.extraDescription(),
                transaction.accountId(),
                transaction.type(),
                transaction.category(),
                transaction.created_at(),
                transaction.updated_at()
        );
    }
}
