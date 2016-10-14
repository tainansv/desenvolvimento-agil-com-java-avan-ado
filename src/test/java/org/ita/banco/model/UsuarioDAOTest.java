package org.ita.banco.model;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.ita.banco.model.impl.Usuario;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Valida comportamento do Usuario
 */
public class UsuarioDAOTest {
    private JdbcDatabaseTester jdbcDatabaseTester;
    private static final String DBUNIT_FILE = "src/test/resources/coursera.xml";
    private Usuario usuario;

    @BeforeClass
    public static void suiteSetUp() {
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     * Inicia base de dados de teste
     */
    @Before
    public void setUp() throws Exception {
        this.jdbcDatabaseTester = new JdbcDatabaseTester(Propriedade.DRIVER_BD, Propriedade.CONNECTION_DB);
        IDataSet iDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(DBUNIT_FILE));
        this.jdbcDatabaseTester.setDataSet(iDataSet);
        this.jdbcDatabaseTester.onSetup();
        usuario = new Usuario();
    }

    @Test
    public void validarTabela() throws Exception {
        final String nomeTabela = "usuario";
        IDataSet iDataSet = this.jdbcDatabaseTester.getConnection().createDataSet();
        ITable iTable = iDataSet.getTable(nomeTabela);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(DBUNIT_FILE));
        ITable expectedTable = expectedDataSet.getTable(nomeTabela);
        Assertion.assertEquals(expectedTable, iTable);
    }

    /**
     * Insere um novo usuário
     */
    @Test
    public void inserirUsuario() {
        usuario.setLogin("quico");
        usuario.setEmail("quico@chaves.com");
        usuario.setNome("Quico");
        usuario.setSenha("qu1c0");
        usuario.setPontos(25);
        usuario.inserir(usuario);
    }

    @Test
    public void recuperarUsuario() {
        usuario = usuario.recuperar("madruga");
        assertEquals("Seu Madruga", usuario.getNome());
    }

    @Test
    public void adicionarPontosUsuario() {
        usuario.adicionarPontos("madruga", 10);
        assertEquals(10, usuario.recuperar("madruga").getPontos().intValue());
    }

    @Test
    public void rankingUsuarios() {
        List<Usuario> usuarios = usuario.ranking();
        assertEquals(1, usuarios.size());
    }


}