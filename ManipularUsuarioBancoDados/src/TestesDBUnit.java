import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

public class TestesDBUnit {

	JdbcDatabaseTester jdt;
	AcessarBanco acessar;
	
	@Before
	public void setUp() throws Exception {
		jdt = new JdbcDatabaseTester("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/coursera?autoReconnect=true&useSSL=false", "root", "senha");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
		acessar = new AcessarBanco();
	}

	@Test
	public void inserir() throws Exception {
		Usuario u = new Usuario();
		u.setLogin("karen");
		u.setEmail("karen@email.com");
		u.setNome("Karen Santos");
		u.setSenha("senha");
		u.setPontos(28);
		
		acessar.inserir(u);
		
		IDataSet currentDataSet = jdt.getConnection().createDataSet();
		ITable currentTable = currentDataSet.getTable("usuario");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/adicionarUsuario.xml");
		ITable expectedTable = expectedDataSet.getTable("usuario");
		Assertion.assertEquals(expectedTable, currentTable);
	}

	@Test
	public void recuperar() {
		Usuario u = acessar.recuperar("tainan");
		assertEquals("Tainan Valentim", u.getNome());
		assertEquals("tainan@email.com", u.getEmail());
	}
	
	@Test
	public void adicionarPontos() throws Exception{
		acessar.adicionarPontos("tainan", 5);
		
		IDataSet currentDataSet = jdt.getConnection().createDataSet();
		ITable currentTable = currentDataSet.getTable("USUARIO");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/adicionarPontos.xml");
		ITable expectedTable = expectedDataSet.getTable("USUARIO");
		Assertion.assertEquals(expectedTable, currentTable);
	}
	
	@Test
	public void ranking(){
		List<Usuario> lista = acessar.ranking();
		assertEquals("lany", lista.get(0).getLogin());
		assertEquals("tainan", lista.get(1).getLogin());
	}
}
