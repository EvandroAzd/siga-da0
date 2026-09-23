package siga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOBanco implements AlunoDAO {
    Connection conexao; //atributo de conexão

    public AlunoDAOBanco() { // Construtor
        try { // try tenta executar
            this.conexao = ConexaoBanco.getConexao(); //Se estiver tudo certo conecta ao banco
        } catch (SQLException e) { //se der errado, entra no catch
            throw new RuntimeException("Erro ao conectar ao banco", e); // exceção finaliza a execução do programa
        }
    }

    @Override
    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO aluno (matricula, nome, media) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql); //evita SQL Injection
            stmt.setString(1, aluno.getMatricula()); //substituido no ?
            stmt.setString(2, aluno.getNome()); //substituido no ?
            stmt.setDouble(3, aluno.getMedia()); //substituido no ?
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir aluno", e); //tratando exceções
        }
    }

    @Override
    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, media = ? WHERE matricula = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setDouble(2, aluno.getMedia());
            stmt.setString(3, aluno.getMatricula());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados de aluno", e);
        }
    }

    @Override
    public void remover(String matricula) {
        String sql = "DELETE FROM aluno WHERE matricula = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, matricula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover aluno", e);
        }
    }

    @Override
    public Aluno buscarPorMatricula(String matricula) {
        String sql = "SELECT * FROM aluno WHERE matricula = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery(); //executa o SELECT e armazena o resultado no rs
            if (rs.next()) { //rs.next() vem da interface ResultSet - o objeto que representa o resultado de um SELECT
                //testamos se a matricula existe no banco, se não retornamos uma exceção
                return new Aluno(rs.getString("nome"), rs.getString("matricula"), rs.getDouble("media"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno", e);
        }
    }

    @Override
    public List<Aluno> listarTodos() {
        String sql = "SELECT * FROM aluno";
        List<Aluno> alunos = new ArrayList<>(); //lista vazia que vai receber os alunos
        try {
            Statement stmt = conexao.createStatement(); //cria comando simples (sem parametros)
            ResultSet rs = stmt.executeQuery(sql); //executa o SELECT e armazena todas as linhas
            while (rs.next()) { //percorre linha por linha
                alunos.add(new Aluno(rs.getString("nome"), rs.getString("matricula"), rs.getDouble("media")));
                //le cada coluna da tabela e cria um objeto com o valor que estiver no banco
            }
            return alunos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos", e);
        }
    }
}
