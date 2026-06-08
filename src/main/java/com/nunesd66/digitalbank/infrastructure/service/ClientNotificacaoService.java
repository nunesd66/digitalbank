package com.nunesd66.digitalbank.infrastructure.service;

import com.nunesd66.digitalbank.domain.event.TransferenciaConcluidaEvent;
import com.nunesd66.digitalbank.infrastructure.exception.NotificationMessagingException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class ClientNotificacaoService {

    @Retryable(
            retryFor = { NotificationMessagingException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 1.5) // Aumenta o tempo a cada tentativa (2s, 3s...)
    )
    public void enviar(TransferenciaConcluidaEvent event) {
        System.out.println("[GATEWAY NOTIFICAÇÃO] Iniciando tentativa de envio via HTTP externo...");

        simularLatenciaRede();
        simulaFalha();

        System.out.printf("[GATEWAY NOTIFICAÇÃO] SUCESSO: Notificação enviada para os clientes %d e %d (Valor: R$ %s)%n",
                event.idRemetente(), event.idDestinatario(), event.valor());
    }

    @Recover
    public void recuperar(NotificationMessagingException e, TransferenciaConcluidaEvent event) {
        System.err.printf("[MENSAGERIA ALERTA] FALHA CRÍTICA DEFINITIVA: Notificação da transferência dos clientes %d -> %d não pôde ser entregue. Erro original: %s%n",
                event.idRemetente(), event.idDestinatario(), e.getMessage());
    }

    private void simularLatenciaRede() {
        try {
            Thread.sleep((long) 400);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void simulaFalha() {
        int chance = ThreadLocalRandom.current().nextInt(1, 11);
        if (chance <= 7) {
            System.err.println("[GATEWAY NOTIFICAÇÃO] Erro de conexão 503: Serviço de mensageria instável.");
            throw new NotificationMessagingException("Falha de timeout ao conectar no provedor de SMS/Email.");
        }
    }
}
