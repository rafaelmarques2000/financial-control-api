package com.financial.api.app.mappers;

import com.financial.api.app.responses.TransactionTypeResponse;
import com.financial.api.domain.transaction.model.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TransactionTypeMapperTest {

    @Test
    public void testIfCreateFromTransactionTypeToTransactionTypeResponse() {
        //GIVEN
        String id = "6721e129-d38f-450e-b613-3666a6b94cda";
        String description = "teste";

        TransactionType transactionType = new TransactionType(id,description);
        //WHEN
            TransactionTypeResponse transactionTypeResponse = TransactionTypeMapper.fromTransactionTypeToTransactionTypeResponse(transactionType);
        //THEN
        Assertions.assertEquals(id, transactionTypeResponse.id());
        Assertions.assertEquals(description, transactionTypeResponse.description());
    }
}
