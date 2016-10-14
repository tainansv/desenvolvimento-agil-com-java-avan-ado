package servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/converter")
public class ConveterTemperatura extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String opcao = req.getParameter("opcao");
		int valor = Integer.parseInt(req.getParameter("valor"));
		int valorConvertido;
		String mensagem;
		
		if(opcao.equals("celsius")){
			valorConvertido = (int) (((valor * 9) /5) + 32);
			mensagem = valor + " Celsius = " + valorConvertido + " Fahrenheit";
		}else{
			valorConvertido = (int) (((valor - 32) *5) /9);
			mensagem = valor +" Fahrenheit = " + valorConvertido + " Celsius";
		}
		
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Resultatdo da conversão</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Conversão executada</h1>");
        out.println("<h2 id='mensagem'>"+mensagem+"</h2>");
        out.println("</body>");
        out.println("</html>");
	}

	
}
