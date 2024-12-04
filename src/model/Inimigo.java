package model;

public class Inimigo {
	
	private int id;
	private String nome;
	private int danoCausa;
	private int vida;
	private String fraqueza;
	private String descricao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getDanoCausa() {
		return danoCausa;
	}
	public void setDanoCausa(int danoCausa) {
		this.danoCausa = danoCausa;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public String getFraqueza() {
		return fraqueza;
	}
	public void setFraqueza(String fraqueza) {
		this.fraqueza = fraqueza;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Inimigo() {
		super();
	}
	
}
