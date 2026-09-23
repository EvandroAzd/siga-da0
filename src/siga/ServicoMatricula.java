package siga;

import java.util.List;

//ANTES

//public class ServicoMatricula {
//
//    public void matricular(Aluno aluno) {
//        // --- regra de negócio (é o que esta classe deveria fazer) ---
//        if (aluno.getMedia() < 0 || aluno.getMedia() > 10) {
//            throw new IllegalArgumentException("Média inválida: " + aluno.getMedia());
//        }
//
//        // --- ...e, no meio dela, acesso a dados (não deveria estar aqui) ---
//        String sql = "INSERT INTO aluno (nome, matricula, media) VALUES ('"
//                + aluno.getNome() + "', '"
//                + aluno.getMatricula() + "', "
//                + aluno.getMedia() + ")";
//        BancoSimulado.executar(sql, aluno.toString());
//    }
//
//    public void gerarRelatorio() {
//        // Duplicação: o mesmo acesso a dados aparece aqui de novo.
//        String sql = "SELECT nome, matricula, media FROM aluno";
//        List<String> linhas = BancoSimulado.consultar(sql);
//
//        System.out.println("=== Relatório de Alunos ===");
//        for (String linha : linhas) {
//            System.out.println(linha);
//        }
//    }

//DEPOIS
public class ServicoMatricula {
    private final AlunoDAO dao; // não sabe se é banco ou memória

    public ServicoMatricula(AlunoDAO dao) { // recebe de fora
        this.dao = dao;
    }

    public void matricular(Aluno aluno) {
        // só regra de negócio aqui
        if (aluno.getMedia() < 0 || aluno.getMedia() > 10) {
            throw new IllegalArgumentException("Média inválida");
        }
        dao.inserir(aluno); // delega ao DAO
    }

    public void gerarRelatorio() {
        List<Aluno> alunos = dao.listarTodos(); // delega ao DAO
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }
}

