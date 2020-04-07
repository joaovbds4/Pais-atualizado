package service;

import model.Pais;
import dao.PaisDAO;

public class PaisService {
	
		PaisDAO dao = new PaisDAO(null);
		
		public int insert (Pais pais) {
			 return dao.insert(pais);
		}
		
		public void atualizar(Pais pais){
			dao.Update(pais);
		}
		
		public void excluir(int id){
			dao.delete(id);
		}	
		
		public Pais consulta (int id){
			return dao.selectPais(id);
		}

}
