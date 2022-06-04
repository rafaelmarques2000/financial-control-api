package com.financial.api.schedulers;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component @EnableScheduling
public class CriarTransacoesRecorrentes {
    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Scheduled(cron = "1 * * * * *", zone = TIME_ZONE)
    public void efetuarLancamento() {
         System.out.println("CRON JOB");
    }
}
