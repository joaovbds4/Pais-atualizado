package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Pais;
import service.PaisService;

/**
 * Servlet implementation class ManterpaisController
 */
@WebServlet("/ManterPais.do")
public class ManterPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pId = Integer.parseInt(request.getParameter("id"));
		String pNome = request.getParameter("nome");
		double pArea = -1;
		long pPopulacao = -1;
		String pAcao = request.getParameter("acao");
		
		//instanciar o javabean
		Pais pais = new Pais();
		pais.setId(pId);
		pais.setNome(pNome);
		pais.setArea(pArea);
		pais.setPopulacao(pPopulacao);
		
		//instanciar o service
		PaisService cs = new PaisService();

		RequestDispatcher view = null;
		HttpSession session = request.getSession();
		
		
		if(pAcao.equals("Criar")) {
			pArea = Double.parseDouble(request.getParameter("area"));
		    pPopulacao = Long.parseLong(request.getParameter("populacao"));
		    
			pais.setArea(pArea);
			pais.setPopulacao(pPopulacao);
			cs.criar(pais);
			ArrayList<Pais> lista = new ArrayList<>();
			lista.add(pais);
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarPais.jsp");
		}else if(pAcao.contentEquals("Excluir")) {
			cs.excluir(pais.getId());
			ArrayList<Pais> lista = (ArrayList<Pais>) session.getAttribute("lista");
			lista.remove(busca(pais, lista));
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarPais.jsp");
		}else if(pAcao.contentEquals("Alterar")) {
			pArea = Double.parseDouble(request.getParameter("area"));
		    pPopulacao = Long.parseLong(request.getParameter("populacao"));
		    
			pais.setArea(pArea);
			pais.setPopulacao(pPopulacao);
			cs.atualizar(pais);
			ArrayList<Pais> lista = (ArrayList<Pais>) session.getAttribute("lista");
			int pos = busca(pais, lista);
			lista.remove(pos);
			lista.add(pos, pais);
			session.setAttribute("lista", lista);
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("ViewPais.jsp");
		}else if(pAcao.contentEquals("Visualizar")) {
			pais = cs.carregar(pais.getId());
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("ViewPais.jsp");
		}else if(pAcao.equals("Editar")) {
			pais = cs.carregar(pais.getId());
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("AlterarPais.jsp");
		}
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