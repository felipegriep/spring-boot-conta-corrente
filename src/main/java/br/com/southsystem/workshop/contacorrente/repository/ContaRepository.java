package br.com.southsystem.workshop.contacorrente.repository;

import br.com.southsystem.workshop.contacorrente.model.Conta;
import br.com.southsystem.workshop.contacorrente.model.enumeration.TipoContaEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends MongoRepository<Conta, String> {

    Page<Conta> findByTipo(TipoContaEnum tipoContaEnum, Pageable pageable);
}
