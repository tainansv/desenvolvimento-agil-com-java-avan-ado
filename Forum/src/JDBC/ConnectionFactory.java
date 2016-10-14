package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	static{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConexao() {
		try {
			Connection conexao = DriverManager.getConnection(
					"jdbc:postgresql://localhost/forum", "postgres", "senha");
			return conexao;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao conectar banco de dados", e);
		}
	}
}
