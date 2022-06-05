package com.financial.api.app.mappers;

import com.financial.api.app.requests.TransactionRequest;
import com.financial.api.app.responses.TransactionResponse;
import com.financial.api.domain.transaction.model.Transaction;
import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TransactionMapperTest {

    @Test
    public void testIfCreateFromTransactionRequestToTransaction() {
        //GIVEN
        String description = "teste";
        LocalDate date = LocalDate.now();
        Integer value = 50000;
        String extraDescription = "teste";
        String transactionTypeId = "bb5535dc-f1b8-4ca2-8e9e-41df9d3498d0";
        String transactionCategoryId = "10df2093-aeba-49fe-926f-ba7e793e0d11";
        String transactionId = "e74c17b2-d446-48db-b3ce-0fa34c19bc17";
        String accountId = "e88eaa75-665a-4285-b0ea-cf0c04bb04b9";

        TransactionRequest transactionRequest = new TransactionRequest(
                  description,
                  date,
                  value,
                  extraDescription,
                  transactionTypeId,
                  transactionCategoryId
        );
        //WHEN
            Transaction transaction = TransactionMapper.fromTransactionRequestToTransaction(transactionRequest, transactionId, accountId);
        //THEN
        Assertions.assertEquals(transactionId, transaction.id());
        Assertions.assertEquals(date, transaction.date());
        Assertions.assertEquals(description, transaction.description());
        Assertions.assertEquals(extraDescription, transaction.extraDescription());
        Assertions.assertEquals(accountId, transaction.accountId());
        Assertions.assertInstanceOf(TransactionType.class, transaction.type());
        Assertions.assertInstanceOf(TransactionCategory.class, transaction.category());
        Assertions.assertEquals(transactionTypeId, transaction.type().id());
        Assertions.assertEquals(transactionCategoryId, transaction.category().id());
    }

    @Test
    public void testIfCreateFromTransactionToTransactionResponse() {
        //GIVEN
        String description = "teste";
        LocalDate date = LocalDate.now();
        LocalDateTime createdOrUpdatedAt = LocalDateTime.now();
        Integer value = 50000;
        String extraDescription = "teste";
        String transactionTypeId = "bb5535dc-f1b8-4ca2-8e9e-41df9d3498d0";
        String transactionCategoryId = "10df2093-aeba-49fe-926f-ba7e793e0d11";
        String transactionId = "e74c17b2-d446-48db-b3ce-0fa34c19bc17";
        String accountId = "e88eaa75-665a-4285-b0ea-cf0c04bb04b9";
        TransactionType transactionType = new TransactionType(transactionTypeId,"TESTE");
        TransactionCategory transactionCategory = new TransactionCategory(transactionCategoryId,"TESTE");


        Transaction transaction = new Transaction(
              transactionId,
              description,
              date,
              value,
              extraDescription,
              accountId,
              transactionType,
              transactionCategory,
              createdOrUpdatedAt,
              createdOrUpdatedAt
        );
        //WHEN
        TransactionResponse transactionResponse = TransactionMapper.fromTransactionToTransactionResponse(transaction);
        //THEN
        Assertions.assertEquals(transactionId, transactionResponse.id());
        Assertions.assertEquals(date, transactionResponse.date());
        Assertions.assertEquals(description, transactionResponse.description());
        Assertions.assertEquals(extraDescription, transactionResponse.extraDescription());
        Assertions.assertEquals(accountId, transactionResponse.accountId());
        Assertions.assertInstanceOf(TransactionType.class, transactionResponse.type());
        Assertions.assertInstanceOf(TransactionCategory.class, transactionResponse.category());
        Assertions.assertEquals(transactionTypeId, transactionResponse.type().id());
        Assertions.assertEquals(transactionCategoryId, transactionResponse.category().id());
    }


}
