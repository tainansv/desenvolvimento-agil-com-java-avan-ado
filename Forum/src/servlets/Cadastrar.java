package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOs.UsuarioDAO;
import modelos.Usuario;

@WebServlet("/cadastrar")
public class Cadastrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario us = new Usuario();
		UsuarioDAO usDao = new UsuarioDAO();

		us.setEmail(request.getParameter("email"));
		us.setLogin(request.getParameter("login"));
		us.setNome(request.getParameter("nome"));
		us.setSenha(request.getParameter("senha"));
		us.setPontos(0);
		usDao.adicionarUsuario(us);

		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
