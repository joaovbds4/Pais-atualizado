package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Pais;
import service.PaisService;

public class ListarPaises implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chave = request.getParameter("data[search]");
		String acao = request.getParameter("acao");
		
		PaisService ps = new PaisService();
		ArrayList<Pais> lista = null;
		HttpSession session = request.getSession();
		if(acao.equals("buscar")) {
			if(chave != null && chave.length() > 0) {
				lista = ps.listarPaises(chave);
			}else {
				lista = ps.listarPaises();
			}
			session.setAttribute("lista", lista);
		}else if(acao.equals("reiniciar")) {
			session.setAttribute("lista", null);
		}
		RequestDispatcher view = request.getRequestDispatcher("ListarPais.jsp");
		view.forward(request, response);
		
	}

}