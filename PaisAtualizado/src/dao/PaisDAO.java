package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Pais;
public class PaisDAO {

	Connection conexao = ConnectionFactory.conectar();
	

	public int insert (Pais pais) {
		String inserir = "INSERT INTO Paises (nome, area_total, populacao)" + "VALUES(?,?,?)";
	
		try (PreparedStatement pst = conexao.prepareStatement(inserir)){
			
			pst.setString(1, pais.getNome());
			pst.setDouble(2, pais.getArea());
			pst.setLong(3, pais.getPopulacao());
			
			pst.execute();
			String sql = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conexao.prepareStatement(sql);
					ResultSet rs = stm2.executeQuery();) {
				if(rs.next()) {
					pais.setId(rs.getInt(1));
					System.out.println("Id encontrado");
				}
				
			}catch(SQLException e) {
				System.out.println("Id n�o encontrado");
				e.printStackTrace();
			}
			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Erro ao cadastrar o Pais");
		}
		return pais.getId();
	}
	
	//SELECT
	public Pais selectPais (int id) {
		Pais pais = new Pais(); 
		pais.setId(id);
		String consulta = "SELECT id, nome, populacao, area_total FROM Paises WHERE paises.id = ?";
				
		try (PreparedStatement pst = conexao.prepareStatement(consulta)){
			pst.setInt(1, id);
			ResultSet resultado = pst.executeQuery();
					
			if(resultado.next()) {
				//Atribuição
				pais.setId(resultado.getInt("id"));
				pais.setNome(resultado.getString("nome"));
				pais.setPopulacao(resultado.getLong("populacao"));
				pais.setArea(resultado.getDouble("area_total"));
			}
			System.out.println("Consulta feita com sucesso");
		} catch(SQLException ex) {	
			ex.printStackTrace();
			System.out.println("Falha na consulta");
		}
		return pais;
	}
	
	//DELETE
	public void delete (int id) {
		String deleta = "DELETE FROM Paises WHERE id = ?";
		
		try (PreparedStatement pst = conexao.prepareStatement(deleta)){
			pst.setInt(1, id);
			pst.execute();
			
			System.out.println("Pais excluido");
			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Falha ao excluir o Pais");
		}
	}
	
	public void updatePais (Pais pais) {		
		String update = "UPDATE Paises SET nome = ?, area_total= ?, populacao = ? WHERE id = ?";
			
		try (PreparedStatement pst = conexao.prepareStatement(update)){
			pst.setString(1, pais.getNome());
			pst.setDouble(2, pais.getArea());
			pst.setLong(3, pais.getPopulacao());
			pst.setInt(4, pais.getId());
			pst.execute();
				
			System.out.println("Atualizado com sucesso!");
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Erro ao atualizar");
		}
	}
	public String maiorNumeroHabitante() {
		String maiorNumero = "SELECT * FROM Paises WHERE populacao = (SELECT MAX(populacao) FROM Paises);";
		
		try (PreparedStatement pst = conexao.prepareStatement(maiorNumero)){
			ResultSet resultado = pst.executeQuery();
			
			if(resultado.next()) {
				String nome = resultado.getString("nome");
				return nome;
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}
	public String menorNumeroHabitante() {
		String maiorNumero = "SELECT * FROM Paises WHERE area_total = (SELECT MIN(area_total) FROM Paises);";
		
		try (PreparedStatement pst = conexao.prepareStatement(maiorNumero)){
			ResultSet resultado = pst.executeQuery();
			
			if(resultado.next()) {
				String nome = resultado.getString("nome");
				return nome;
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return null;
	}
	
	public ArrayList<Pais> listarPaises(){
		Pais pais;
		ArrayList<Pais> lista = new ArrayList<>();
		
		String sqlSelect = "SELECT id, nome, populacao, area_total FROM paises";
		try(PreparedStatement pst = conexao.prepareStatement(sqlSelect)) {
			try(ResultSet rs = pst.executeQuery();) {
				while(rs.next()) {
					pais = new Pais();
					pais.setId(rs.getInt("id"));
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area_total"));
					lista.add(pais);
				}
				
			} catch (SQLException el) {
				el.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ArrayList<Pais> listarPaises(String chave){
		Pais pais;
		ArrayList<Pais>lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, populacao, area_total FROM paises where	upper(nome) like ?";
		try(PreparedStatement pst = conexao.prepareStatement(sqlSelect)) {
			pst.setString(1, "%" + chave.toUpperCase() + "%");
			try(ResultSet rs = pst.executeQuery();) {
				while(rs.next()) {
					pais = new Pais();
					pais.setId(rs.getInt("id"));
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area_total"));
					lista.add(pais);
				}
				
			} catch (SQLException el) {
				el.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}