package com.financial.api.app.mappers;

import com.financial.api.app.responses.TransactionCategoryResponse;
import com.financial.api.app.responses.TransactionTypeResponse;
import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;

public class TransactionCategoryMapper {

    public static TransactionCategoryResponse fromTransactionCategoryToTransactionCategoryResponse(TransactionCategory transactionCategory) {
        return new TransactionCategoryResponse(transactionCategory.id(), transactionCategory.description());
    }
}
