package pos_java_jdbc.pos_java_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

//classe para testar separadamente cada parte do sistema, e a conexão com o banco

public class TesteBancojdbc {// realiza o teste unitário como se fosse um main

	@Test
	public void initBanco() {

		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();

		 //add dinâmico // agora com id automatico ele vai adicionar a posição de forma automatica
		// userposjava.setId(6L);//disque é uma posição long // agora é gerado automaticamente dentro do banco 
		
		 userposjava.setNome("João");
		 userposjava.setEmail("joao@gmail.com");

		 userPosDAO.salvar(userposjava);

		System.out.println("teste executado");

		// SingleConnection.getConnection();

	}
	
	// naõ precisa instanciar pois é um método static
	// traz os dados da lista
	@Test
	public void inintListar() {
		UserPosDAO dao = new UserPosDAO();// para usar os métodos da classe
		try {
			List<Userposjava> list = dao.listar();

			// debuga a lista
			for (Userposjava userposjava : list) {
				System.out.println(userposjava);
				System.out.println(userposjava.getEmail());
				System.out.println("----------------------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// buscar um unuco elemento da lista do banco
	@Test
	public void initbuscar() {

		UserPosDAO dao = new UserPosDAO();

		try {
			Userposjava userposjava = dao.buscar(5L);

			System.out.println(userposjava);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//mudar o nome de uma coluna de um item id
	@Test
	public void initAtualizar () {
		try {
		
		UserPosDAO dao = new UserPosDAO();
		
			Userposjava objetoBanco = dao.buscar(5L);
			objetoBanco.setNome("Nome mudado com método atualizar ");
			
			dao.atualizar(objetoBanco);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//deletar pelo id 
	@Test
	public void initDeletar() {
		try {
			
			UserPosDAO dao = new UserPosDAO();
			dao.deletar(9L);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//insere dados em uma tabela filha pelo id 
	@Test
	public void testeInsertTelefone() {
		
		Telefone telefone = new Telefone();
		telefone.setNumero("(45) 999288539");
		telefone.setTipo("celular");
		telefone.setUsuario(11L);
		
		UserPosDAO dao = new UserPosDAO();
		dao.salvarTelefone(telefone);
	}

	//retorna a lista de telefones pelo id 
	@Test
	public void testeCarregaFonesUser (){
		
		UserPosDAO dao = new UserPosDAO();
		
		List<BeanUserFone> beanUserFones = dao.listaUserFone(10L);
		
		for (BeanUserFone beanUserFone : beanUserFones) {
			
			System.out.println(beanUserFone);
			System.out.println("----------------------------------------");
			
		}
	}
	
	//deleta o aluno pelo id 
	@Test
	public void testDeleteUserFone() {
		
		UserPosDAO dao = new UserPosDAO();
		dao.deleteFonesPorUser(13L);
		
	}
	
}
