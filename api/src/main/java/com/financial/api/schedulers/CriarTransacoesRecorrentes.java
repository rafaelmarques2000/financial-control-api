package com.financial.api.schedulers;

import com.financial.api.domain.services.enums.RecurrenceType;
import com.financial.api.domain.services.model.Service;
import com.financial.api.domain.services.repository.IServiceRepository;
import com.financial.api.domain.transaction.model.Transaction;
import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;
import com.financial.api.domain.transaction.repository.ITransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    Logger logger = LoggerFactory.getLogger(CriarTransacoesRecorrentes.class);

    @Scheduled(cron = "1 * * * * *", zone = TIME_ZONE)
    public void efetuarLancamento() {

        LocalDate initial = LocalDate.now();
        LocalDate beginMonth = initial.with(firstDayOfMonth());
        LocalDate end = initial.with(lastDayOfMonth());

         serviceRepository.findAll().subscribe(service -> {
             if(service.recurrenceType().equals(RecurrenceType.MONTHLY)) {
                 processMonthServices(beginMonth, end, service);
             }
         });

    }
    //@TODO melhorar codigo de processamento de transações mensais
    private void processMonthServices(LocalDate beginMonth, LocalDate end, Service service) {
        transactionRepository.findByDateAndServiceReference(beginMonth, end, service.id())
                .collectList().subscribe(transactions -> {
                      if(transactions.isEmpty()) {
                          transactionRepository.save(createRecurrentTransaction(beginMonth, service)).subscribe(transaction -> {
                              logger.info("Serviço "+service.description() + "da conta de id" + service.accountId()+ "Foi registrado com sucesso");
                          });
                      } else {
                          logger.warn("Lançamento já realizado service id:"+ service.id());
                      }
                });
    }

    private Transaction createRecurrentTransaction(LocalDate beginMonth, Service service) {
        return new Transaction(
                UUID.randomUUID().toString(),
                service.description(),
                beginMonth,
                service.value(),
                "Lançado automaticamente pelo sistema",
                service.accountId(),
                new TransactionType("b607761c-7c57-4b0e-804b-dfccea1a2a95", ""),
                new TransactionCategory("dccf7735-b74e-46ef-ac01-a30a5ed4f3d5", ""),
                LocalDateTime.now(),
                LocalDateTime.now(),
                UUID.fromString(service.id())
        );
    }
}
