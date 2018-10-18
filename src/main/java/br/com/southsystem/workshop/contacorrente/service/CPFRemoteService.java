package br.com.southsystem.workshop.contacorrente.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Map;
import java.util.Optional;

@Service
public class CPFRemoteService {
    private final RestTemplate restTemplate;

    @Value("${service.spring-boot-demo.url}")
    private String remoteUrl;

    public CPFRemoteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Map<String, String>> findNomeByCpf(String name) {
        final ResponseEntity<Map> response = restTemplate.getForEntity(remoteUrl, Map.class, name);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return Optional.empty();
        }

        return Optional.of(response.getBody());
    }
}

