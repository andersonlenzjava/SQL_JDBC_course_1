package model;

public class Telefone {

	private Long id;
	private String numero;
	private String tipo;

	private Long usuario;// fk do usuário pai

	public Long getId() {
		return id;
	}

	//teste
	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

}
