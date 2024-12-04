package model;

public class Personagem {
    private int id;
    private String nome;
    private String classe;
    private String item;
    private String genero;
    private int idade;
    private String descricao;

    public Personagem(int id, String nome, String classe, String item, String genero, int idade, String descricao) {
        this.id = id;
        this.nome = nome;
        this.classe = classe;
        this.item = item;
        this.genero = genero;
        this.idade = idade;
        this.descricao = descricao;
    }

    public Personagem() {
	}

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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
