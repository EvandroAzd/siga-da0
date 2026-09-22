package siga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlunoDAOMemoria implements AlunoDAO {
    private final Map<String, Aluno> armazenamento = new HashMap<>();

    @Override
    public void inserir(Aluno aluno) {
        armazenamento.put(aluno.getMatricula(), aluno);
    }

    @Override
    public Aluno buscarPorMatricula(String matricula) {
        return armazenamento.get(matricula);
    }

    @Override
    public List<Aluno> listarTodos(){
        return new ArrayList<>(armazenamento.values());
    }

    @Override
    public void atualizar(Aluno aluno) {
        if(armazenamento.containsKey(aluno.getMatricula())) {
            armazenamento.put(aluno.getMatricula(), aluno);
        }
    }

    @Override
    public void remover(String matricula) {
        armazenamento.remove(matricula);
    }


}
