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

public class ExcluirPais implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		Pais pais = new Pais();
		pais.setId(pId);
		PaisService cs = new PaisService();
		
		
		cs.excluir(pais.getId());
		ArrayList<Pais> lista = (ArrayList<Pais>) session.getAttribute("lista");
		lista.remove(busca(pais, lista));
		RequestDispatcher view = request.getRequestDispatcher("ListarPais.jsp");
		view.forward(request, response);
		
	}
	public int busca(Pais pais, ArrayList<Pais> lista) {
		Pais to;
		for(int i = 0; i < lista.size(); ++i) {
			to = lista.get(i);
			if(to.getId() == pais.getId()) {
				return i;
			}
		}
		return -1;
	}

}