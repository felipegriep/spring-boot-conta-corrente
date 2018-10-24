package br.com.southsystem.workshop.contacorrente.broker;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface BrokerOutput {
    String CONTA_CORRENTE_CRIADA = "contaCorrenteCriada";

    @Output(BrokerOutput.CONTA_CORRENTE_CRIADA)
    MessageChannel contaCorrenteCriada();
}
