package service;

import exceptions.BancoDeDadosException;
import modelos.Receita;
import repository.ReceitaRepository;

import java.util.List;

public class ReceitaService {

    private ReceitaRepository receitaRepository;

    public ReceitaService() {
        receitaRepository = new ReceitaRepository();
    }

    // criação de um objeto
    public void adicionarReceita(Receita receita) {
        try {
            Receita receitaAdicionado = receitaRepository.adicionar(receita);
            System.out.println("contato adicinado com sucesso! " + receitaAdicionado);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
//            System.out.println("TRACE: ");
//            e.printStackTrace();
        }
    }

    // remoção
    public void removerReceita(Integer id) {
        try {
            boolean conseguiuRemover = receitaRepository.remover(id);
            System.out.println("removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editarReceita(Integer id, Receita receita) {
        try {
            boolean conseguiuEditar = receitaRepository.editar(receita);
            System.out.println("editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public void listar(Integer idUsuario) {
        try {
            List<Receita> listar = receitaRepository.listar(idUsuario);
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}
