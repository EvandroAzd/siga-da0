package siga;

import java.sql.SQLException;

public class AlunoDAOBanco implements AlunoDAO {
    Connection conexao; //atributo de conexão

    public AlunoDAOBanco() { // Construtor
        try { // try tenta executar
            this.conexao = ConexaoBanco.getConexao(); //Se estiver tudo certo conecta ao banco
        } catch (SQLException e) { //se der errado, entra no catch
            throw new RuntimeException("Erro ao conectar ao banco", e); // exceção finaliza a execução do programa
        }
    }

    public void inserir(Aluno aluno) {

    }
}
