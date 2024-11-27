package src.edu.curso;

import java.util.List;

public interface MedicamentoDAO{
    void inserir(Medicamento m) throws MedicamentoException;
    void atualizar(Medicamento m) throws MedicamentoException;
    void remover(Medicamento m) throws MedicamentoException;
    List<Medicamento> pesquisarPorNome(String nome) throws MedicamentoException;
 }