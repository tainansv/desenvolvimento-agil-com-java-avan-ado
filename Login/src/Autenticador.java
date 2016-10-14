import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Autenticador {

	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String autenticar(String login, String senha) throws Exception{
		
		try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost/usuarios?autoReconnect=true&useSSL=false", "root", "senha")){
			PreparedStatement ps = c.prepareStatement("select nome from usuario where login = ? and senha = ?");
			ps.setString(1, login);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return rs.getString("nome");
				
			}
			else
				throw new Exception("Não foi possivel encontrar usuario");
		}
	}
}
