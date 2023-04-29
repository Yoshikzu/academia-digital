package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

    @Autowired
    private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
        var aluno = alunoRepository.findById(form.getAlunoId()).orElse(null);
        if(aluno == null){
            throw new RuntimeException("Aluno não cadastrado no sistema!");
        }
        avaliacaoFisica.setAluno(aluno);
        avaliacaoFisica.setPeso(form.getPeso());
        avaliacaoFisica.setAltura(form.getAltura());

        return avaliacaoFisicaRepository.save(avaliacaoFisica);
    }

    @Override
    public AvaliacaoFisica get(Long id) {
        return getAvaliacao(id);
    }

    @Override
    public List<AvaliacaoFisica> getAll() {
        return avaliacaoFisicaRepository.findAll();
    }

    @Override
    public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm dadosAvalicaoAtualizado) {
        var avaliacao = getAvaliacao(id);
        if(dadosAvalicaoAtualizado.getPeso() != 0d){
            avaliacao.setPeso(dadosAvalicaoAtualizado.getPeso());
        }
        if(dadosAvalicaoAtualizado.getAltura() != 0d){
            avaliacao.setAltura(dadosAvalicaoAtualizado.getAltura());
        }

        return avaliacaoFisicaRepository.save(avaliacao);
    }

    @Override
    public void delete(Long id) {
        var avaliacao = getAvaliacao(id);
        avaliacaoFisicaRepository.delete(avaliacao);
    }

    private AvaliacaoFisica getAvaliacao(Long id){
        var avaliacao = avaliacaoFisicaRepository.findById(id).orElse(null);
        if(avaliacao == null){
            throw new RuntimeException("Avaliação Física (id: " +  id + ") não cadastrado no sistema!");
        }
        return avaliacao;
    }
}