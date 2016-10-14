import static org.junit.Assert.*;

import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

public class TesteUsuarioDAO {
	
	JdbcDatabaseTester jdt;

	@Before
	public void setUp() throws Exception {
		
		jdt = new JdbcDatabaseTester("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/usuarios?autoReconnect=true&useSSL=false", "root", "senha");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}

	@Test
	public void recuperaTodos() {
		List<Usuario> lista = UsuarioDAO.todosUsuarios();
		assertEquals(2, lista.size());
		assertEquals("joao", lista.get(0).getLogin());
	}
	
	@Test
	public void insereNovo() throws Exception {
		Usuario u = new Usuario();
		u.setLogin("duda");
		u.setNome("Maria Eduarda");
		u.setEmail("duda@email.com.br");
		
		UsuarioDAO.inserirUsuario(u);
		
		IDataSet currentDataSet = jdt.getConnection().createDataSet();
		ITable currentTable = currentDataSet.getTable("USUARIO");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/verifica.xml");
		ITable expectedTable = expectedDataSet.getTable("USUARIO");
		Assertion.assertEquals(expectedTable, currentTable);
 	}

}
