package service;

import exceptions.BancoDeDadosException;
import modelos.Investimento;
import repository.InvestimentoRepository;

import java.util.List;

public class InvestimentoService {

    private InvestimentoRepository investimentoRepository;

    public InvestimentoService() {
        investimentoRepository = new InvestimentoRepository();
    }

    // criação de um objeto
    public void adicionarInvestimento(Investimento investimento) {
        try {
            Investimento investimentoAdicionado = investimentoRepository.adicionar(investimento);
            System.out.println("investimento adicinado com sucesso! " + investimentoAdicionado);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
//            System.out.println("TRACE: ");
//            e.printStackTrace();
        }
    }

    // remoção
    public void removerInvestimento(Integer id) {
        try {
            boolean conseguiuRemover = investimentoRepository.remover(id);
            System.out.println("investimento removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editarInvestimento(Integer id, Investimento investimento) {
        try {
            boolean conseguiuEditar = investimentoRepository.editar(investimento);
            System.out.println("investimento editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public List<Investimento> listar(Integer idUsuario) {
        try {
            return investimentoRepository.listar(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

}
