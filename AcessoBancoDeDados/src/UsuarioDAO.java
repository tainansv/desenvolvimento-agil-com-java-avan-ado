import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {

	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Usuario> todosUsuarios(){
		List<Usuario> todos = new ArrayList<>();
		
		try(Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/usuarios?autoReconnect=true&useSSL=false", "root", "senha")){
			
			String sql = "select login, nome, email from usuario";
			PreparedStatement stm = c.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				Usuario u = new Usuario();
				u.setLogin(rs.getString("login"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				todos.add(u);
			}
			
		}catch (SQLException e) {
			throw new RuntimeException("Nãofoi possivel conectar",e);
		}
		
		return todos;
	}
	
	public static void inserirUsuario(Usuario u){
		try(Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/usuarios?autoReconnect=true&useSSL=false", "root", "senha")) {
			
			String sql = "insert into usuario (`login`,`nome`,`email`)"
					+ "values (?,?,?)";
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setString(1, u.getLogin());
			stm.setString(2, u.getNome());
			stm.setString(3, u.getEmail());
			
			stm.executeUpdate();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
