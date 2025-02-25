package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOs.UsuarioDAO;
import modelos.Usuario;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean tentativa = false;
		try {
			tentativa = new UsuarioDAO().login(request.getParameter("login"), request.getParameter("senha"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (tentativa) {

			try {
				HttpSession session = request.getSession();
				Usuario usuario = null;
				usuario = new UsuarioDAO().getUsuario(request.getParameter("login"));
				session.setAttribute("usuario", usuario);
				request.getRequestDispatcher("topicos").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			request.setAttribute("erro", "Login ou senha incorretos. Tente novamente.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
