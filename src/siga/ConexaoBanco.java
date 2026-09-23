package siga;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    public static Connection getConexao() throws SQLException { //utilizamos a interface Connection e informamos que quem chamar o metodo deve tratar um possivel Exceção
        String url = "jdbc:mariadb://localhost:3306/aluno-dao"; //url para conectar o MariaDB
        String user = "root";
        String password = System.getenv("DB_SENHA"); //variavel de ambiente
        return DriverManager.getConnection(url, user, password);
    };
}
