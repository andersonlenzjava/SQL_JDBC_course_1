package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Telefone;
import model.Userposjava;

/*classe para manipular os dados do banco através da classe objetos e fazer a conexão */
//usuário do banco 

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();// objeto para gravar no banco

	}

	// inserir dados no banco, recebidos pelo objeto com seus dados
	public void salvar(Userposjava userposjava) {
		try {
			String sql = "insert into userposjava (nome, email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);// objeto para setar e enviar o para o banco
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.executeUpdate();
			connection.commit();// salva no banco

		} catch (Exception e) {

			try {
				connection.rollback();// reverte a operação do banco caso de erro
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void salvarTelefone(Telefone telefone) {

		try {

			String sql = "INSERT INTO public.telefoneuser(numero, tipo, usuariopessoa) VALUES ( ?, ?, ?);";
			PreparedStatement stateman = connection.prepareStatement(sql);
			stateman.setString(1, telefone.getNumero());
			stateman.setString(2, telefone.getTipo());
			stateman.setLong(3, telefone.getUsuario());
			stateman.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();//colocar aqui dentro para mostrar se nao passar 
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	// trows exception para permitir algumas situações
	// metodo que retorna os dadados do banco para uma lista
	// cria uma lista com base na classe userposjava
	public List<Userposjava> listar() throws Exception {
		List<Userposjava> list = new ArrayList<Userposjava>();

		String sql = "select * from userposjava";// retorna todas as colunas do banco

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		// traz o valor de cada linha para o objeto resultado

		// enquanto houver linhas
		while (resultado.next()) {
			Userposjava userposjava = new Userposjava();
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));

			list.add(userposjava);
		}

		return list;

	}

	// retorna um objeto pelo id, pois é isto que o metodo recebe
	public Userposjava buscar(Long id) throws Exception {
		Userposjava retorno = new Userposjava();

		String sql = "select * from userposjava where id = " + id;// retorna todas as colunas do banco

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		// traz o valor de cada linha para o objeto resultado

		// enquanto houver linhas
		while (resultado.next()) {// retorna um usuário ou nenhum
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));

		}
		return retorno;

	}

	public void atualizar(Userposjava userposjava) {
		try {

			// interogação para ficar dinâmico
			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userposjava.getNome());

			statement.execute();
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void deletar(Long id) {
		try {

			String sql = "delete from userposjava where id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
