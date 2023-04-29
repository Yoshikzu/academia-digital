package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.service.impl.AvaliacaoFisicaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    @Autowired
    private AvaliacaoFisicaServiceImpl service;

    @PostMapping
    public ResponseEntity create(@RequestBody AvaliacaoFisicaForm form) {
        return ResponseEntity.ok(service.create(form));
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoFisica>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoFisica> getAvaliacaoFisica(@PathVariable Long id) {
        return (ResponseEntity<AvaliacaoFisica>) ResponseEntity.ok(service.get(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarAvaliacao(@PathVariable Long id, @RequestBody AvaliacaoFisicaUpdateForm avaliacao){
        return ResponseEntity.ok(service.update(id,avaliacao));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}