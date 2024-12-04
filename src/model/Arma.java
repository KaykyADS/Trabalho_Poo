package model;

public class Arma {
	private int id;
	private String nome;
	private double peso;
	private String descricao;
	private String tipo_dano;
	private int dano;
	private String forca, fe, destreza, inteligencia;
	
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
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo_dano() {
		return tipo_dano;
	}
	public void setTipo_dano(String tipo_dano) {
		this.tipo_dano = tipo_dano;
	}
	public int getDano() {
		return dano;
	}
	public void setDano(int dano) {
		this.dano = dano;
	}
	public String getForca() {
		return forca;
	}
	public void setForca(String forca) {
		this.forca = forca;
	}
	public String getFe() {
		return fe;
	}
	public void setFe(String fe) {
		this.fe = fe;
	}
	public String getDestreza() {
		return destreza;
	}
	public void setDestreza(String destreza) {
		this.destreza = destreza;
	}
	public String getInteligencia() {
		return inteligencia;
	}
	public void setInteligencia(String inteligencia) {
		this.inteligencia = inteligencia;
	}
}
