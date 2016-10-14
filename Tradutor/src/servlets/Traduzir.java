package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelos.Tradutor;

/**
 * Servlet implementation class Tradutor
 */
@WebServlet("/traduzir")
public class Traduzir extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String palavra = req.getParameter("palavra");
		Tradutor tradutor = new Tradutor();
		
		req.setAttribute("traducao", tradutor.traduz(palavra));
		req.getRequestDispatcher("traducao.jsp").forward(req, resp);
	}


}
