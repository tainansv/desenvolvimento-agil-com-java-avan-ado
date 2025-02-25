package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOs.ComentarioDAO;
import DAOs.TopicosDAO;
import DAOs.UsuarioDAO;
import modelos.Comentario;
import modelos.Topico;
import modelos.Usuario;

@WebServlet("/exibir")
public class ExibirTopico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("topicoID"));
			Topico topico = new TopicosDAO().getTopico(id);
			Usuario usuario = new UsuarioDAO().getUsuario(topico.getLogin());
			List<Comentario> comentarios = new ComentarioDAO().getComentariosTopico(topico.getId());
			List<Usuario> autores = new ComentarioDAO().getAutores(comentarios);
			Map<String, String> lista = new HashMap<>();
			
			int c = 0;
			for (Comentario com : comentarios) {
				lista.put(autores.get(c).getNome(), com.getComentario());
				c++;
			}
			
			request.setAttribute("topico", topico);
			request.setAttribute("topicoUsuario", usuario);
			request.setAttribute("lista", lista);
			request.getRequestDispatcher("exibe-topico.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
