package src.edu.curso;
import java.time.LocalDate;

public class Medicamento {
    private long id;
    private String nome = "";
    private String fabricante = "";
    private String categoria = "";
    private String descricao = "";
    private int quantidade = 0;
    private LocalDate validade = LocalDate.now();

    public Medicamento (){
        super();
    }

    public Medicamento (long id, String nome, String fabricante, String categoria, int quantidade, LocalDate validade){
        this.id = id;
        this.nome = nome;
        this.fabricante = fabricante;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.validade = validade;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao; 
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getValidade() {
        return validade;
    }
    public void setValidade(LocalDate validade) {
        this.validade = validade;
    } 

}
