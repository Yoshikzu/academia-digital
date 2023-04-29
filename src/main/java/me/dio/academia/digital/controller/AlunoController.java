package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServiceImpl service;

    @PostMapping
    @Transactional
    public ResponseEntity create(@Valid @RequestBody AlunoForm form) {
        return ResponseEntity.ok(service.create(form));
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll(@RequestParam(value = "dataDeNascimento", required = false)
                              String dataDeNacimento) {
        return (ResponseEntity<List<Aluno>>) ResponseEntity.ok(service.getAll(dataDeNacimento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAluno(@PathVariable Long id) {
        return (ResponseEntity<Aluno>) ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/avaliacoes/{id}")
    public ResponseEntity<List<AvaliacaoFisica>> getAllAvaliacaoFisicaId(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllAvaliacaoFisicaId(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarAluno(@PathVariable Long id, @RequestBody AlunoUpdateForm aluno){
        return ResponseEntity.ok(service.update(id,aluno));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}