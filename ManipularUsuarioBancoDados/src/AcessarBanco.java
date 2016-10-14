import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcessarBanco implements UsuarioDAO {

	@Override
	public void inserir(Usuario u) {
		try(Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/coursera?"
					+ "autoReconnect=true&useSSL=false", "root", "senha")) {
			String sql = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stm = conexao.prepareStatement(sql);
			
			stm.setString(1, u.getLogin());
			stm.setString(2, u.getEmail());
			stm.setString(3, u.getNome());
			stm.setString(4, u.getSenha());
			stm.setInt(5, u.getPontos());
			
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível inserir usuário", e);
		}
	}

	@Override
	public Usuario recuperar(String login) {
		try(Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/coursera?"
				+ "autoReconnect=true&useSSL=false", "root", "senha")) {
			String sql = "SELECT * FROM usuario WHERE login = ?";
			
			PreparedStatement stm = conexao.prepareStatement(sql);
			Usuario u = new Usuario();
			
			stm.setString(1, login);
			ResultSet rs = stm.executeQuery();
			
			rs.next();
			u.setEmail(rs.getString("email"));
			u.setLogin(rs.getString("login"));
			u.setNome(rs.getString("nome"));
			u.setSenha(rs.getString("senha"));
			u.setPontos(rs.getInt("pontos"));
			
			return u;
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException("Não foi possível recuperar usuário", e);
		}
	}

	@Override
	public void adicionarPontos(String login, int pontos) {
		try(Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/coursera?"
				+ "autoReconnect=true&useSSL=false", "root", "senha")) {
			String sql = "UPDATE usuario SET pontos = pontos + ? WHERE login = ?";
			PreparedStatement stm = conexao.prepareStatement(sql);
			
			stm.setInt(1, pontos);
			stm.setString(2, login);
			
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível recuperar usuário", e);
		}

	}

	@Override
	public List<Usuario> ranking() {
		try(Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/coursera?"
				+ "autoReconnect=true&useSSL=false", "root", "senha")) {
			String sql = "SELECT * FROM usuario ORDER BY pontos DESC";
			PreparedStatement stm = conexao.prepareStatement(sql);
			List<Usuario> lista = new ArrayList<>();
			
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				Usuario u = new Usuario();
				u.setEmail(rs.getString("email"));
				u.setLogin(rs.getString("login"));
				u.setNome(rs.getString("nome"));
				u.setSenha(rs.getString("senha"));
				u.setPontos(rs.getInt("pontos"));
				lista.add(u);
			}
			
			return lista;

		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível recuperar usuário", e);
		}
	}

}
