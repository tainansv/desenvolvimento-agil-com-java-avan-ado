

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contar")
public class ContarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//request
		if(request.getAttribute("contador") == null)
			request.setAttribute("contador", new Contador("request"));
		
		((Contador) request.getAttribute("contador")).contar();
		
		//session
		if(request.getSession().getAttribute("contador") == null)
			request.getSession().setAttribute("contador", new Contador("session"));
		
		((Contador) request.getSession().getAttribute("contador")).contar();
		
		//aplication
		if(getServletContext().getAttribute("contador") == null)
			getServletContext().setAttribute("contador", new Contador("aplication"));
		
		((Contador)getServletContext().getAttribute("contador")).contar();
		
		PrintWriter pw = response.getWriter();
		pw.append("<html>");
		pw.append("<h1>"+request.getAttribute("contador")+"</h1>");
		pw.append("<h1>"+request.getSession().getAttribute("contador")+"</h1>");
		pw.append("<h1>"+getServletContext().getAttribute("contador")+"</h1>");
		pw.append("</html>");
	}

}
