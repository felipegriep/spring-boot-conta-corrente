package br.com.southsystem.workshop.contacorrente.resource;

import br.com.southsystem.workshop.contacorrente.model.Conta;
import br.com.southsystem.workshop.contacorrente.model.enumeration.TipoContaEnum;
import br.com.southsystem.workshop.contacorrente.service.ContaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaResource {
    private final ContaService contaService;

    public ContaResource(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<Conta> insert(@Valid @RequestBody Conta conta) {
        return ResponseEntity.ok(contaService.insert(conta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> find(@PathVariable("id") String id) {
        return contaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Conta>> findAll(Pageable pageable) {
        return ResponseEntity.ok(contaService.findAll(pageable));
    }

    @GetMapping("/tipo-conta/{tipoConta}")
    public ResponseEntity<Page<Conta>> findByTipoConta(@PathVariable("tipoConta")TipoContaEnum tipoConta, Pageable pageable) {
        return ResponseEntity.ok(contaService.findByTipoConta(tipoConta, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        contaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<Conta> update(@Valid @RequestBody Conta conta) {
        return ResponseEntity.ok(contaService.update(conta));
    }
}
