package com.financial.api.schedulers;

import com.financial.api.domain.services.enums.RecurrenceType;
import com.financial.api.domain.services.model.Service;
import com.financial.api.domain.services.repository.IServiceRepository;
import com.financial.api.domain.transaction.model.Transaction;
import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;
import com.financial.api.domain.transaction.repository.ITransactionCategoryRepository;
import com.financial.api.domain.transaction.repository.ITransactionRepository;
import com.financial.api.domain.transaction.repository.ITransactionTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Component @EnableScheduling
public class CriarTransacoesRecorrentes {
    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Autowired
    IServiceRepository serviceRepository;
    @Autowired
    ITransactionRepository transactionRepository;

    @Autowired
    ITransactionTypeRepository transactionTypeRepository;

    @Autowired
    ITransactionCategoryRepository transactionCategoryRepository;

    Logger logger = LoggerFactory.getLogger(CriarTransacoesRecorrentes.class);

    @Scheduled(cron = "1 * * * * *", zone = TIME_ZONE)
    public void efetuarLancamento() {
        LocalDate initial = LocalDate.now();
        LocalDate beginMonth = initial.with(firstDayOfMonth());
        LocalDate endMonth = initial.with(lastDayOfMonth());

         serviceRepository.findAll().subscribe(service -> {
             processMonthServices(beginMonth, endMonth, service);
         });
    }

    private void processMonthServices(LocalDate beginMonth, LocalDate end, Service service) {
        transactionRepository.findByDateAndServiceReference(beginMonth, end, service.id())
                .collectList().subscribe(transactions -> {
                      if(transactions.isEmpty()) {
                          transactionRepository.save(createRecurrentTransaction(beginMonth, service)).subscribe(transaction -> {
                              logger.info("Servi??o "+service.description() + "da conta de id" + service.accountId()+ "Foi registrado com sucesso");
                          });
                      } else {
                          logger.warn("Lan??amento j?? realizado service id:"+ service.id());
                      }
                });
    }

    private Transaction createRecurrentTransaction(LocalDate beginMonth, Service service) {
        return new Transaction(
                UUID.randomUUID().toString(),
                service.description(),
                beginMonth,
                service.value(),
                "Lan??ado automaticamente pelo sistema",
                service.accountId(),
                getTransactionType().get(),
                getTransactionCategory().get(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                UUID.fromString(service.id())
        );
    }

    private Optional<TransactionType> getTransactionType() {
        return transactionTypeRepository.noReactiveFindBySlugname("despesa");
    }

    private Optional<TransactionCategory> getTransactionCategory() {
        return transactionCategoryRepository.noReactiveFindBySlugname("outras_despesas");
    }
}
