package com.nunesd66.digitalbank.infrastructure.listener;

import com.nunesd66.digitalbank.domain.event.TransferenciaConcluidaEvent;
import com.nunesd66.digitalbank.infrastructure.service.ClientNotificacaoService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransferenciaNotificacaoListener {

    private final ClientNotificacaoService notificacaoService;

    @Async
    @EventListener
    public void onTransferenciaConcluida(TransferenciaConcluidaEvent event) {
        System.out.println("[Listener] Evento capturado em background. Chamando serviço de envio...");
        notificacaoService.enviar(event);
    }
}
