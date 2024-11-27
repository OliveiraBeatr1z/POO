package src.edu.curso;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;


public class MedicamentoBoundary extends Application {

    private Label lblId = new Label("");
    private TextField txtNome = new TextField();
    private TextField txtFabricante = new TextField();
    private TextField txtCategoria = new TextField();
    private TextField txtDescricao = new TextField();
    private TextField txtQuantidade = new TextField();
    private DatePicker datevalidade = new DatePicker();

    private MedicamentoControl control = null;

    private TableView<Medicamento> tableView = new TableView<>();

    @Override
    public void start(Stage stage) throws Exception {
        try {
            control = new MedicamentoControl();
           
        } catch (MedicamentoException e) {
            new Alert(AlertType.ERROR, "Erro ao iniciair o sistema", ButtonType.OK).showAndWait();
        }

        BorderPane panePrincipal = new BorderPane();
        GridPane paneForm = new GridPane();

        Button btnGravar = new Button("Gravar");
        btnGravar.setOnAction(e -> {
            try{
                control.gravar();
            }catch (MedicamentoException err){
                new Alert(AlertType.ERROR, "Erro ao gravar o medicamento", ButtonType.OK).showAndWait();            
            }
            tableView.refresh();
        });

        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(e ->{
            try{
                control.pesquisar();
            } catch (MedicamentoException err){
                new Alert(AlertType.ERROR, "Erro ao pesquisar por nome", ButtonType.OK).showAndWait();
            }
        });

        Button btnNovo = new Button("*");
        btnNovo.setOnAction(e-> control.limparTudo());

        paneForm.add(new Label("Id: "), 0, 0);
        paneForm.add(lblId, 1, 0);

        paneForm.add(new Label("Nome: "), 0, 1);
        paneForm.add(txtNome, 1, 1);
        paneForm.add(btnNovo, 2, 1);
        
        paneForm.add(new Label("Fabricante: "), 0, 2);
        paneForm.add(txtFabricante, 1, 2);

        paneForm.add(new Label("Categoria: "), 0, 3);
        paneForm.add(txtCategoria, 1, 3);

        paneForm.add(new Label("Descrição: "), 0, 4);
        paneForm.add(txtDescricao, 1, 4);

        paneForm.add(new Label("Quantidade: "), 0, 5);
        paneForm.add(txtQuantidade, 1, 5);

        paneForm.add(new Label("Validade: "), 0, 6);
        paneForm.add(datevalidade, 1, 6);

        paneForm.add(btnGravar, 0, 7);
        paneForm.add(btnPesquisar, 1, 7);

        remedios();
        gerarColunas();

        panePrincipal.setTop(paneForm);
        panePrincipal.setCenter(tableView);

        Scene scn = new Scene(panePrincipal, 600, 400);
        stage.setScene(scn);
        stage.setTitle("Estoque de Medicamentos");
        stage.show();

    }

    // passar o que ta na tela para o control e vise versa
    public void remedios(){
        control.idProperty().addListener( (obs, antigo, novo) -> {
            lblId.setText( String.valueOf(novo));
        });
        IntegerStringConverter conv = new IntegerStringConverter();

        Bindings.bindBidirectional(control.nomeProperty(), txtNome.textProperty());
        Bindings.bindBidirectional(control.fabricanteProperty(), txtFabricante.textProperty());
        Bindings.bindBidirectional(control.categoriaProperty(), txtCategoria.textProperty());
        Bindings.bindBidirectional(control.descricaoProperty(), txtDescricao.textProperty());
        Bindings.bindBidirectional(txtQuantidade.textProperty(), control.quantidadeProperty(),(StringConverter) conv);
        Bindings.bindBidirectional(control.validadeProperty(),datevalidade.valueProperty());
    }

    // criando a colunas da tabelinha da tela
    public void gerarColunas(){

        TableColumn<Medicamento, Long> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<Medicamento, Long>("id"));

        TableColumn<Medicamento, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(new PropertyValueFactory<Medicamento,String>("nome"));

        TableColumn<Medicamento, String> col3 = new TableColumn<>("Fabricante");
        col3.setCellValueFactory( new PropertyValueFactory<Medicamento, String>("Fabricante"));

        TableColumn<Medicamento, String> col4 = new TableColumn<>("Categoria");
        col4.setCellValueFactory( new PropertyValueFactory<Medicamento, String>("Categoria"));

        TableColumn<Medicamento, String> col5 = new TableColumn<>("Descricao");
        col5.setCellValueFactory( new PropertyValueFactory<Medicamento, String>("descricao"));

        TableColumn<Medicamento, Integer> col6 = new TableColumn<>("Quantidade");
        col6.setCellValueFactory(new PropertyValueFactory<Medicamento, Integer>("quantidade"));

        TableColumn<Medicamento, LocalDate> col7 = new TableColumn<>("Validade");
        col7.setCellValueFactory(new PropertyValueFactory<Medicamento, LocalDate>("validade"));

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) ->{
            if(novo != null){
                System.out.println("Nome: " + novo.getNome());
                control.paraTela(novo);
            }
        });

        Callback<TableColumn<Medicamento, Void>, TableCell<Medicamento, Void>> cb = new Callback<>(){

            @Override
            public TableCell<Medicamento, Void> call(
                TableColumn<Medicamento,Void> param){
                TableCell<Medicamento, Void> celula = new TableCell<>(){
                    final Button btnApagar = new Button("Apagar");

                    {
                        btnApagar.setOnAction( e -> {
                            Medicamento medicamento = tableView.getItems().get( getIndex() );
                            try { 
                                control.excluir( medicamento ); 
                            } catch (MedicamentoException err) { 
                                new Alert(AlertType.ERROR, "Erro ao excluir o contato", ButtonType.OK).showAndWait();
                            }
                        });
                    
                    }
                    @Override
                    public void updateItem(Void item, boolean empty){
                        if(!empty){
                            setGraphic(btnApagar);
                        } else{
                            setGraphic(null);
                        }
                    }
                };
                return celula;
            }
        };

        TableColumn<Medicamento, Void> col8 = new TableColumn<>("Ação");
        col8.setCellFactory(cb);

        tableView.getColumns().addAll(col1, col2, col3, col4, col5,col6, col7);
        tableView.setItems(control.getLista());
    }

    public static void main(String[] args) {
        Application.launch(MedicamentoBoundary.class, args);
    }
}
