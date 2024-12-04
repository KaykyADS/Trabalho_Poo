package model;

public class Armadura {

	private int id;
	private String nome;
	private double peso;
	private String descricao;
	private String tipoArmadura;
	private int veneno;
	private int fisica;
	private int magica;
	private int fogo;
	private int eletrica;
	private int hemorragica;
	
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
	public String getTipoArmadura() {
		return tipoArmadura;
	}
	public void setTipoArmadura(String tipoArmadura) {
		this.tipoArmadura = tipoArmadura;
	}
	public int getVeneno() {
		return veneno;
	}
	public void setVeneno(int veneno) {
		this.veneno = veneno;
	}
	public int getFisica() {
		return fisica;
	}
	public void setFisica(int fisica) {
		this.fisica = fisica;
	}
	public int getMagica() {
		return magica;
	}
	public void setMagica(int magica) {
		this.magica = magica;
	}
	public int getFogo() {
		return fogo;
	}
	public void setFogo(int fogo) {
		this.fogo = fogo;
	}
	public int getEletrica() {
		return eletrica;
	}
	public void setEletrica(int eletrica) {
		this.eletrica = eletrica;
	}
	public int getHemorragica() {
		return hemorragica;
	}
	public void setHemorragica(int hemorragica) {
		this.hemorragica = hemorragica;
	}
	
	public Armadura() {
		super();
	}
}