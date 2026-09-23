package siga;

/**
 * Ponto de entrada do SIGA (código INICIAL da atividade da Aula 7).
 *
 * O programa FUNCIONA: matricula alunos e gera o relatório. Mas repare na
 * saída: o ServicoMatricula está emitindo comandos SQL, ou seja, a regra de
 * negócio conhece a tecnologia de persistência. Sua tarefa é extrair essa
 * responsabilidade para um DAO.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Usando AlunoDAOMemoria ===\n");

        AlunoDAO daoMemoria = new AlunoDAOMemoria();
        ServicoMatricula servicoMemoria = new ServicoMatricula(daoMemoria);

        servicoMemoria.matricular(new Aluno("Maria Silva", "2026001", 8.5));
        servicoMemoria.matricular(new Aluno("João Souza",  "2026002", 6.0));
        servicoMemoria.gerarRelatorio();

        System.out.println("\n=== Usando AlunoDAOBanco ===\n");

        AlunoDAO daoBanco = new AlunoDAOBanco();
        ServicoMatricula servicoBanco = new ServicoMatricula(daoBanco);

        servicoBanco.matricular(new Aluno("Maria Silva", "2026001", 8.5));
        servicoBanco.matricular(new Aluno("João Souza",  "2026002", 6.0));
        servicoBanco.gerarRelatorio();

        System.out.println("\n=== Regra de negócio funciona independente do DAO ===\n");

        try {
            servicoMemoria.matricular(new Aluno("Teste Inválido", "2026003", -1));
        } catch (IllegalArgumentException e) {
            System.out.println("Regra de negócio funcionou: " + e.getMessage());
        }
    }
}
