package service;

import model.Pais;

import java.util.ArrayList;

import dao.PaisDAO;


public class PaisService {
	PaisDAO dao;
	
	public PaisService() {
		dao = new PaisDAO();
	}
	
	public int criar(Pais Pais) {
		return dao.insert(Pais);
	}
	
	public void atualizar(Pais pais){
		dao.updatePais(pais);
	}
	
	public void excluir(int id){
		dao.delete(id);
	}
	
	public Pais carregar(int id){
		return dao.selectPais(id);
	}
	
	public ArrayList<Pais> listarPaises(){
		return dao.listarPaises();
	}
	
	public ArrayList<Pais> listarPaises(String chave){
		return dao.listarPaises(chave);
	}

}