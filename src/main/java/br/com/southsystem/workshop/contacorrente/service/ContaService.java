package br.com.southsystem.workshop.contacorrente.service;

import br.com.southsystem.workshop.contacorrente.model.Conta;
import br.com.southsystem.workshop.contacorrente.model.enumeration.TipoContaEnum;
import br.com.southsystem.workshop.contacorrente.repository.ContaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final CPFRemoteService cpfRemoteService;

    public ContaService(ContaRepository contaRepository, CPFRemoteService cpfRemoteService) {
        this.contaRepository = contaRepository;
        this.cpfRemoteService = cpfRemoteService;
    }

    public Conta insert(Conta conta) {
        cpfRemoteService.findNomeByCpf(conta.getCpf())
                .ifPresent(result -> conta.setNome(result.get("name")));
        return contaRepository.save(conta);
    }

    public Optional<Conta> findById(String id) {
        return Optional.ofNullable(contaRepository.findOne(id));
    }

    public Page<Conta> findAll(Pageable pageable) {
        return contaRepository.findAll(pageable);
    }

    public Page<Conta> findByTipoConta(TipoContaEnum tipoContaEnum, Pageable pageable) {
        return contaRepository.findByTipo(tipoContaEnum, pageable);
    }

    public void delete(String id) {
        Conta conta = contaRepository.findOne(id);
        contaRepository.delete(conta);
    }

    public Conta update(Conta conta) {
        Conta c = contaRepository.findOne(conta.getId());
        c.setAgencia(conta.getAgencia());
        c.setNumeroConta(conta.getNumeroConta());
        c.setCpf(conta.getCpf());
        c.setTipo(conta.getTipo());
        return contaRepository.save(c);
    }
}
