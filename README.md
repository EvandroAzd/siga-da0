# SIGA DAO

Atividade prática da Aula 7 de Técnicas de Programação II (FATEC Porto Ferreira).

Demonstra o padrão **DAO (Data Access Object)** aplicando os princípios **SRP** e **DIP**.

## Pré-requisitos

- Java 11+
- IntelliJ IDEA
- MariaDB instalado (para usar `AlunoDAOBanco`)

## Configuração do banco

Execute o script `banco.sql` no MariaDB:

```bash
mariadb -u root < banco.sql
```

## Variável de ambiente

A senha do banco é lida via variável de ambiente. Configure antes de rodar:

```bash
export DB_SENHA=suasenha
```

No IntelliJ: `Run → Edit Configurations → Environment variables` e adicione `DB_SENHA=suasenha`.

## Como rodar

1. Clone o repositório
2. Abra no IntelliJ IDEA
3. Adicione o driver MariaDB: `File → Project Structure → Libraries → + → Java` e selecione o `mariadb-java-client.jar`
4. Configure a variável de ambiente `DB_SENHA`
5. Execute a classe `Main`

## Estrutura

```
src/siga/
├── AlunoDAO.java          # Interface do padrão DAO
├── AlunoDAOMemoria.java   # Implementação em memória (HashMap)
├── AlunoDAOBanco.java     # Implementação com MariaDB (JDBC)
├── ConexaoBanco.java      # Gerencia a conexão com o banco
├── ServicoMatricula.java  # Regra de negócio — depende só da interface
├── Aluno.java             # Entidade de domínio
└── Main.java              # Demonstra a troca de implementação
```