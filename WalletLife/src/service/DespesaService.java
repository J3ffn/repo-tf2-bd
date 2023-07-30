package service;

import exceptions.BancoDeDadosException;
import modelos.Despesa;
import modelos.Investimento;
import repository.DespesaRepository;

import java.util.List;

public class DespesaService {

    private DespesaRepository despesaRepository;

    public DespesaService() {
        despesaRepository = new DespesaRepository();
    }

    // criação de um objeto
    public void adicionarDespesa(Despesa despesa) {
        try {

            Despesa despesaAdicionado = despesaRepository.adicionar(despesa);
            System.out.println("despesa adicinada com sucesso! " + despesaAdicionado);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
//            System.out.println("TRACE: ");
//            e.printStackTrace();
        }
    }

    // remoção
    public void removerDespesa(Integer id) {
        try {
            boolean conseguiuRemover = despesaRepository.remover(id);
            System.out.println("despesa removida? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editarDespesa(Integer id, Despesa despesa) {
        try {
            boolean conseguiuEditar = despesaRepository.editar(id, despesa);
            System.out.println("despesa editada? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public void listarDespesa() {
        try {
            List<Despesa> listar = despesaRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }


}
