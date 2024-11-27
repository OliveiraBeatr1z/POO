package src.edu.curso;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MedicamentoControl {
    private ObservableList<Medicamento> lista = FXCollections.observableArrayList();
    private long contador = 2;

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty fabricante = new SimpleStringProperty("");
    private StringProperty categoria = new SimpleStringProperty("");
    private StringProperty descricao = new SimpleStringProperty("");
    private IntegerProperty quantidade = new SimpleIntegerProperty(0);
    private ObjectProperty<LocalDate> validade = new SimpleObjectProperty<>(LocalDate.now());

    private MedicamentoDAO medicamentoDAO = null;

    public MedicamentoControl() throws Exception{
        medicamentoDAO = new MedicamentoDAOImpl();
    }

    public Medicamento paraEntidade(){
        Medicamento m = new Medicamento();
        m.setId(id.get());
        m.setNome(nome.get());
        m.setFabricante(fabricante.get());
        m.setCategoria(categoria.get());
        m.setDescricao(descricao.get());
        m.setQuantidade(quantidade.get());
        m.setValidade(validade.get());
        return m;
    }
    
    public void excluir( Medicamento m ) throws MedicamentoException{
        medicamentoDAO.remover(m);
        pesquisarTodos();
    }

    public void limparTudo(){
        id.set(0);
        nome.set("");
        fabricante.set("");
        categoria.set("");
        descricao.set("");
        quantidade.set(0);
        validade.set(LocalDate.now());
    }

    public void paraTela( Medicamento m ) {
        if(m != null){
            id.set(m.getId());
            nome.set(m.getNome());
            fabricante.set(m.getFabricante());
            categoria.set(m.getCategoria());
            descricao.set(m.getDescricao());
            quantidade.set(m.getQuantidade());
            validade.set(m.getValidade());
        }
    }

    public void gravar() throws MedicamentoException {
        Medicamento m = paraEntidade();
        if(m.getId() ==0) {
            this.contador +=1;
            m.setId(this.contador);
            medicamentoDAO.inserir(m);
        } else{
            medicamentoDAO.atualizar(m);
        }
        pesquisarTodos();
    }

    public void pesquisar() throws MedicamentoException{
        lista.clear();
        lista.addAll(medicamentoDAO.pesquisarPorNome(""));
    }

    public void pesquisarTodos() throws MedicamentoException{
        lista.clear();
        lista.addAll(medicamentoDAO.pesquisarPorNome(""));
    }

    public LongProperty idProperty(){
        return this.id;
    }
    public StringProperty nomeProperty() { 
        return this.nome;
    }
    public StringProperty fabricanteProperty() { 
        return this.fabricante;
    }
    public StringProperty categoriaProperty() { 
        return this.categoria;
    }
    public StringProperty descricaoProperty() { 
        return this.descricao;
    }
    public IntegerProperty quantidadeProperty(){
        return this.quantidade;
    }
    public ObjectProperty<LocalDate> validadeProperty() { 
        return this.validade;
    }
    public ObservableList<Medicamento> getLista() { 
        return this.lista;
    }

}
