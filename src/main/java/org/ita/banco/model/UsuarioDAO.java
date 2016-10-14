package org.ita.banco.model;

import org.ita.banco.model.impl.Usuario;

import java.util.List;

/**
 * Usuario com as informações presentes na tabela
 */
public interface UsuarioDAO {

    /**
     * insere um novo usuário no banco de dados
     * @param usuario
     */
    public void inserir(Usuario usuario);

    /**
     * Recupera o usuário pelo seu login
     * @param login
     * @return
     */
    public Usuario recuperar(String login);

    /**
     * Adiciona os pontos para o usuário no banco
     * @param login
     * @param pontos
     */
    public void adicionarPontos(String login, int pontos);

    /**
     * Retorna a lista de usuários ordenada por pontos (maior primeiro)
     * @return
     */
    public List<Usuario> ranking();
}