package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOs.TopicosDAO;
import modelos.Topico;
import modelos.Usuario;

@WebServlet("/adicionarTopico")
public class AdicionarTopico extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Topico top = new Topico();
		TopicosDAO topDao = new TopicosDAO();
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");

		top.setTitulo(request.getParameter("titulo"));
		top.setConteudo(request.getParameter("conteudo"));
		top.setLogin(us.getLogin());

		topDao.adiconarTopico(top);
		request.getRequestDispatcher("topicos").forward(request, response);

	}
}
