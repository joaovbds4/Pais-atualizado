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

public class CriarPais implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pNome = request.getParameter("nome");
		double pArea = Double.parseDouble(request.getParameter("area"));
		long pPopulacao = Long.parseLong(request.getParameter("populacao"));
		String pAcao = request.getParameter("acao");
		
		//instanciar o javabean
		Pais pais = new Pais();
		pais.setNome(pNome);
		pais.setArea(pArea);
		pais.setPopulacao(pPopulacao);
		
		//instanciar o service
		PaisService cs = new PaisService();
		cs.criar(pais);
		pais = cs.carregar(pais.getId());
		ArrayList<Pais> lista = new ArrayList<>();
		lista.add(pais);
		
		
		HttpSession session = request.getSession();
		session.setAttribute("lista", lista);
		RequestDispatcher view = request.getRequestDispatcher("ListarPais.jsp");
		view.forward(request, response);
		
	}

}