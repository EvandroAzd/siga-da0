package siga;

import java.util.List;

public interface AlunoDAO {
    void inserir(Aluno aluno);
    Aluno buscarPOrMatricula(String matricula);
    List<Aluno> listarTodos();
    void atualizar(Aluno aluno);
    void remover(String matricula);
}
