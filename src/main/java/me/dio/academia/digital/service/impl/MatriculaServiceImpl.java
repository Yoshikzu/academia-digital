package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Matricula create(MatriculaForm form) {
        Matricula matricula = new Matricula();
        var aluno = alunoRepository.findById(form.getAlunoId()).orElse(null);
        if(aluno == null){
            throw new RuntimeException("Aluno não cadastrado no sistema!");
        }
        if (aluno != null) {
            var matriculaExistente = matriculaRepository.findByIdAluno(aluno.getId());
            if (matriculaExistente != null) {
                throw new RuntimeException("Aluno já matriculado!");
            }
        }
        matricula.setAluno(aluno);
        return matriculaRepository.save(matricula);
    }

    @Override
    public Matricula get(Long id) {
        return getMatricula(id);
    }

    @Override
    public List<Matricula> getAll(String bairro) {
        if(bairro == null){
            return matriculaRepository.findAll();
        }else{
            return matriculaRepository.findAlunosMatriculadosBairro(bairro);
        }
    }

    @Override
    public void delete(Long id) {
        var matricula = getMatricula(id);
        matriculaRepository.delete(matricula);
    }

    private Matricula getMatricula(Long id){
        var matricula = matriculaRepository.findById(id).orElse(null);
        if(matricula == null){
            throw new RuntimeException("Matricula (id: " +  id + ") não cadastrado no sistema!");
        }
        return matricula;
    }
}