package com.financial.api.app.mappers;

import com.financial.api.app.requests.AccountRequest;
import com.financial.api.app.responses.AccountResponse;
import com.financial.api.app.responses.AccountShareUserResponse;
import com.financial.api.app.responses.TransactionCategoryResponse;
import com.financial.api.domain.account.enums.AccountType;
import com.financial.api.domain.account.model.Account;
import com.financial.api.domain.accountUser.model.AccountShareUser;
import com.financial.api.domain.transaction.model.TransactionCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TransactionCategoryMapperTest {

    @Test
    public void testIfCreateFromTransactionCategoryToTransactionCategoryResponse() {
        //GIVEN
        String id = "6721e129-d38f-450e-b613-3666a6b94cda";
        String description = "teste";

        TransactionCategory transactionCategory = new TransactionCategory(id,description);
        //WHEN
            TransactionCategoryResponse transactionCategoryResponse = TransactionCategoryMapper.fromTransactionCategoryToTransactionCategoryResponse(transactionCategory);
        //THEN
        Assertions.assertEquals(id, transactionCategoryResponse.id());
        Assertions.assertEquals(description, transactionCategoryResponse.description());
    }
}
