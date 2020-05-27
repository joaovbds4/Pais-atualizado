package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pais;
import service.PaisService;

public class EditarPais implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pId = Integer.parseInt(request.getParameter("id"));
		PaisService cs = new PaisService();
		Pais pais = new Pais();
		pais.setId(pId);
		pais = cs.carregar(pais.getId());
		request.setAttribute("pais", pais);
		
		RequestDispatcher view = request.getRequestDispatcher("AlterarPais.jsp");
		view.forward(request, response);
	}

}