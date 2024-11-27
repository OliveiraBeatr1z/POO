package src.edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAOImpl implements MedicamentoDAO{

    private final static String DB_CLASS = "org.mariadb.jdbc.Driver";
    private final static String DB_URL = "jdbc:mariadb://localhost:3306/medicamentos?allowPublicKeyRetrieval=true";
    private final static String DB_USER = "root";
    private final static int DB_PASS = 8250;

    private Connection con = null;

    public MedicamentoDAOImpl() throws MedicamentoException{
        try{
            Class.forName(DB_CLASS);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_CLASS);
        }catch(ClassNotFoundException | SQLException e){
            throw new MedicamentoException(e);
        }
    }

    @Override
    public void inserir(Medicamento m) throws MedicamentoException {
        java.sql.Date dt = java.sql.Date.valueOf(m.getValidade());
        try{
            String SQL = """
            INSERT INTO medicamentos (id, nome, fabricante, categoria, descricao, quantidade, validade)
            VALUES (? ? ? ? ? ? ?)
            """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setLong(1, m.getId());
            stm.setString(2, m.getNome());
            stm.setString(3, m.getFabricante());
            stm.setString(4, m.getCategoria());
            stm.setString(5, m.getDescricao());
            stm.setInt(6, m.getQuantidade());           
            stm.setDate(7, dt);
            int i = stm.executeUpdate();
        } catch(SQLException e){
            throw new MedicamentoException(e);
        }
    }

    @Override
    public void atualizar(Medicamento m) throws MedicamentoException {
        java.sql.Date dt = java.sql.Date.valueOf(m.getValidade());
        try{
            String SQL = """
            UPDATE medicamentos SET nome = ?, fanricante = ?, categoria  = ?, descricao  = ?, quantidade  = ?, validade  = ?
            WHERE id = ?
            """;
            PreparedStatement stm = con.prepareStatement(SQL);

            stm.setString(2, m.getNome());
            stm.setString(3, m.getFabricante());
            stm.setString(4, m.getCategoria());
            stm.setString(5, m.getDescricao());
            stm.setInt(6, m.getQuantidade());
            stm.setDate(7, dt);

        }catch(SQLException e){
            throw new MedicamentoException(e);
        }
    }

    @Override
    public void remover(Medicamento m) throws MedicamentoException {
        try {
            String SQL = """
            DELETE FROM medicamentos Where id = ?
            """; 
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setLong(1, m.getId());
            int i = stm.executeUpdate();
        } catch (SQLException e) {
            throw new MedicamentoException(e);
        }
    }

    @Override
    public List<Medicamento> pesquisarPorNome(String nome) throws MedicamentoException {
        List<Medicamento> lista = new ArrayList<>();
        try{
            String SQL = """
            SELECT * FROM medicamentos WHERE nome LIKE ?
            """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1, "%" + nome + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Medicamento m = new Medicamento();
                m.setId(rs.getLong("id"));
                m.setNome(rs.getString("nome"));
                m.setFabricante(rs.getString("fabricante"));
                m.setCategoria(rs.getString("categoria"));
                m.setDescricao(rs.getString("descricao"));
                m.setQuantidade(rs.getInt("quantidade"));
                m.setValidade(rs.getDate("validade").toLocalDate());

                lista.add(m);
            }
        } catch (SQLException e){
            throw new MedicamentoException(e);
        }
        return lista;
    }
    
}
