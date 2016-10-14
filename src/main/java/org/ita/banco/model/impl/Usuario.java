package org.ita.banco.model.impl;

import org.ita.banco.model.Propriedade;
import org.ita.banco.model.UsuarioDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementação da interface UsuárioDAO
 */
public class Usuario implements UsuarioDAO {
    /**
     * Login do usuário
     */
    private String login;

    /**

     * Email do usuário
     */
    private String email;

    /**
     * Nome do usuário
     */
    private String nome;

    /**
     * Senha do usuário
     */
    private String senha;

    /**
     * Pontuação do usuário
     */
    private Integer pontos;

    /**
     * Logger para classe
     */
    private static final Logger logger = Logger.getLogger(Usuario.class.getName());

    static {
        try {
            Class.forName(Propriedade.DRIVER_BD);
            Connection connection = connectar();
            criarTabela(connection);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cria tabela a partir de arquivo SQL
     * @param connection
     * @throws IOException
     * @throws SQLException
     */
    private static void criarTabela(Connection connection) throws IOException, SQLException {
        final Path sqlFile = Paths.get("coursera.sql");
        String sqlData = new String(Files.readAllBytes(sqlFile));
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlData);
        statement.close();
        connection.close();
    }

    /**
     * Realiza conexão na base de dados
     * @return
     * @throws SQLException
     */
    private static Connection connectar() throws SQLException {
        return DriverManager.getConnection(Propriedade.CONNECTION_DB);
    }


    @Override
    public void inserir(Usuario usuario) {
        final String statement = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?);";
        try {
            Connection connection = connectar();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, usuario.login);
            preparedStatement.setString(2, usuario.email);
            preparedStatement.setString(3, usuario.nome);
            preparedStatement.setString(4, usuario.senha);
            preparedStatement.setInt(5, usuario.pontos);
            preparedStatement.executeQuery();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            this.logger.log(Level.SEVERE, "Não foi possível inserir na tabela", e);
        }
    }

    @Override
    public Usuario recuperar(String login) {
        Usuario usuario = null;
        final String statement = "SELECT * FROM usuario WHERE login = ?;";
        try {
            Connection connection = connectar();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                usuario = recuperarUsuario(resultSet);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            this.logger.log(Level.SEVERE, "Não foi possível recuperar da tabela", e);
        }
        return usuario;
    }

    @Override
    public void adicionarPontos(String login, int pontos) {
        final String statement = "UPDATE usuario SET pontos = pontos + ? WHERE login = ?;";
        try {
            Connection connection = connectar();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, pontos);
            preparedStatement.setString(2, login);
            preparedStatement.executeQuery();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            this.logger.log(Level.SEVERE, "Não pode atualiza indice da tabela", e);
        }

    }

    @Override
    public List<Usuario> ranking() {
        List<Usuario> usuarios = new ArrayList<Usuario>();

        final String statement = "SELECT * FROM usuario ORDER BY pontos DESC;";
        try {
            Connection connection = connectar();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                usuarios.add(recuperarUsuario(resultSet));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            this.logger.log(Level.SEVERE, "Não foi possível recuperar da tabela", e);
        }

        return usuarios;
    }

    /**
     * Recupera informação de usuário
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private Usuario recuperarUsuario(final ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.login = resultSet.getString("login");
        usuario.email = resultSet.getString("email");
        usuario.nome = resultSet.getString("nome");
        usuario.senha = resultSet.getString("senha");
        usuario.pontos = resultSet.getInt("pontos");
        return usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

}
