package service;

import model.Pais;
import dao.PaisDAO;

public class PaisService {
	PaisDAO dao = new PaisDAO();
	
	public int criar(Pais Pais) {
		return dao.insert(Pais);
	}
	
	public void atualizar(String nome, int id){
		dao.upDateNomePais (nome, id);
	}
	
	public void excluir(int id){
		dao.delete(id);
	}
	
	public Pais carregar(int id){
		return dao.selectPais(id);
	}

}