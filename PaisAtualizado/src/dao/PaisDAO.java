package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Pais;

public class PaisDAO {

	private Connection conexao;
	
	public PaisDAO (Connection conexao) {
		this.conexao = conexao;
	}
	
	public int insert (Pais pais) {
		String inserir = "INSERT INTO Pais (nome, populacao, area_total)" + "VALUES(?,?,?)";
	
		try (PreparedStatement pst = conexao.prepareStatement(inserir)){
			pst.setString(1, pais.getNome());
			pst.setString(2, pais.getPopulacao());
			pst.setString(3, pais.getArea());
			pst.execute();
			
			System.out.println("Pais Cadastrado!");
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Erro ao cadastrar o Pais");
		}
		return pais.getId();	
	}
	
	//SELECT
	public Pais selectPais (int id) {
		
		String consulta = "SELECT id, nome, populacao, area_total FROM pais WHERE id = ?";
				
		try (PreparedStatement pst = conexao.prepareStatement(consulta)){
			pst.setInt(1, id);
			ResultSet resultado = pst.executeQuery();
			Pais pais = null;
			
			if(resultado.next()) {
				pais = new Pais();
				
				int idPais = resultado.getInt("id");
				String nome = resultado.getString("nome");
				String populacao = resultado.getString("populacao");
				String area = resultado.getString("area_total");
				
				//Atribuição
				pais.setId(idPais);
				pais.setNome(nome);
				pais.setPopulacao(populacao);
				pais.setArea(area);
				
				return pais;
			}
			System.out.println("Consulta feita com sucesso");
		} catch(SQLException ex) {	
			ex.printStackTrace();
			System.out.println("Falha na consulta");
		}
		return null;
	}
	
	//DELETE
	public void delete (int id) {
		String deleta = "DELETE FROM pais WHERE id = ?";
		
		try (PreparedStatement pst = conexao.prepareStatement(deleta)){
			pst.setInt(1, id);
			pst.execute();
			
			System.out.println("Pais excluido");
			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Falha ao excluir o Pais");
		}
	}
	
	
	public void Update(Pais pais) {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area_total=? WHERE id=?";
		
		try (PreparedStatement pst = conexao.prepareStatement(sqlUpdate)){
			pst.setString(1, pais.getNome());
			pst.setString(2, pais.getPopulacao());
			pst.setString(3, pais.getArea());
			pst.setInt(4, pais.getId());
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		  }
	}
}
