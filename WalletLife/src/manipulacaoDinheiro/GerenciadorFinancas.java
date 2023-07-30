package manipulacaoDinheiro;

import enumerators.TipoDespesaEReceita;
import modelos.*;
import service.DespesaService;
import service.InvestimentoService;
import service.ReceitaService;
import service.UsuarioService;

import java.util.HashMap;

public class GerenciadorFinancas implements IManipularFinancas, IImpressao {

    private final Usuario usuario;

    private HashMap<Integer, Despesa> despesas;
    private DespesaService despesaService;

    private HashMap<Integer, Investimento> investimentos;

    private InvestimentoService investimentoService;

    private HashMap<Integer, Receita> receitas;
    private ReceitaService receitaService;

    private double totalReceita;

    private double totalDespesas;

    private double totalInvestimento;

    public GerenciadorFinancas(Usuario usuario) {
        this.despesas = new HashMap<>();
        this.receitas = new HashMap<>();
        this.investimentos = new HashMap<>();
        this.usuario = usuario;

        this.investimentoService = new InvestimentoService();
        this.despesaService = new DespesaService();
        this.receitaService = new ReceitaService();
    }

    public void popularBanco() {
        Integer idUsuario = usuario.getId();
//        investimentoService.listar(idUsuario);
//        despesaService.listarDespesa(idUsuario);
//        receitaService.listar(idUsuario);
    }

    @Override
    public double calcularTotal(HashMap<?, ?> lista) {
        Double total = 0.0;
        for (AbstractMovimentoDinheiro<TipoDespesaEReceita> despesa : despesas.values()) {
            total += despesa.getValor();
        }

        return total;
    }

    @Override
    public double calcularReceitaTotal() {
        totalReceita = calcularTotal(receitas);
        return totalReceita;
    }

    @Override
    public double calcularInvestimentos() {
        totalInvestimento = calcularTotal(investimentos);
        return totalInvestimento;
    }

    @Override
    public double calcularDespesa() {
        totalDespesas = calcularTotal(despesas);
        return totalDespesas;
    }

    public HashMap<Integer, Despesa> getDespesas() {
        return despesas;
    }

    public void addDespesa(Despesa despesa) {
        despesa.setId(despesas.size());
        this.despesas.put(despesa.getId(), despesa);
        totalDespesas += despesa.getValor();
        despesaService.adicionarDespesa(despesa);
    }

    public void updateValorDespesa(int id, double valor) {
        this.totalDespesas -= despesas.get(id).getValor();
        this.despesas.get(id).setValor(valor);
        this.totalDespesas += valor;
        this.despesas.replace(id, despesas.get(id));
    }

    public void updateDescricaoDespesa(int id, String descricao) {
        this.despesas.get(id).setDescricao(descricao);
        this.despesas.replace(id, despesas.get(id));
    }

    public boolean deleteDespesa(int id) {
        this.totalDespesas -= despesas.get(id).getValor();
        despesaService.removerDespesa(despesas.get(id).getId());
        return despesas.replace(id, despesas.get(id)) != null;
    }

    public HashMap<Integer, Investimento> getInvestimentos() {
        return investimentos;
    }

    public void addInvestimento(Investimento investimento) {
        investimento.setId(investimentos.size());
        this.investimentos.put(investimento.getId(), investimento);
        this.totalInvestimento += investimento.getValor();
        investimentoService.adicionarInvestimento(investimento);
    }

    public void updateValorInvestimento(int id, double valor) {
        this.investimentoService.editarInvestimento(investimentos.get(id).getId(), investimentos.get(id));
        this.totalInvestimento -= investimentos.get(id).getValor();
        this.investimentos.get(id).setValor(valor);
        this.totalInvestimento += investimentos.get(id).getValor();
        this.investimentos.replace(id, investimentos.get(id));
    }

    public void updateDescricaoInvestimento(int id, String descricao) {
        this.investimentos.get(id).setDescricao(descricao);
        this.investimentos.replace(id, investimentos.get(id));
    }

    public boolean deleteInvestimento(int id) {
        this.totalInvestimento -= investimentos.get(id).getValor();
        return this.investimentos.replace(id, investimentos.get(id)) != null;
    }

    public HashMap<Integer, Receita> getReceitas() {
        return receitas;
    }

    public void addReceita(Receita receita) {
        receita.setId(receita.getId());
        receitas.put(receita.getId(), receita);
        totalReceita += receita.getValor();
        receitaService.adicionarReceita(receita);
    }

    public void updateValorReceita(int id, double valor) {
        this.totalReceita -= receitas.get(id).getValor();
        this.receitas.get(id).setValor(valor);
        this.totalReceita += receitas.get(id).getValor();
        this.receitas.replace(id, receitas.get(id));
    }

    public void updateDescricaoReceita(int id, String descricao) {
        this.receitas.get(id).setDescricao(descricao);
        this.receitas.replace(id, receitas.get(id));
    }

    public boolean deleteReceita(int id) {
        totalReceita -= receitas.get(id).getValor();
        return receitas.replace(id, receitas.get(id)) != null;
    }

    public double getValorTotalReceita() {
        return this.totalReceita;
    }

    public void setValorTotalReceita(double totalReceita) {
        this.totalReceita = totalReceita;
    }

    public double getValorTotalDespesas() {
        return this.totalDespesas;
    }

    public void setValorTotalDespesas(double totalDespesas) {
        this.totalDespesas = totalDespesas;
    }

    public double getValorTotalInvestimento() {
        return totalInvestimento;
    }

    public void setTotalInvestimentos(double totalInvestimento) {
        this.totalInvestimento = totalInvestimento;
    }

    public String relatorioGeral() {
        return String.format("""
                ----------------------------------
                | Tipos: | Quantidade | Valor
                ----------------------------------
                | Receita | %d | %5.2f
                ----------------------------------
                | Despesa | %d | %5.2f
                ----------------------------------
                | Investimento |  %d  | %5.2f
                ----------------------------------
                """, receitas.size(), totalReceita, despesas.size(), totalDespesas, investimentos.size(), totalInvestimento
        );
    }

    @Override
    public void imprimir() {
        System.out.println(
                "GerenciadorFinancas {" +
                        "despesas=" + despesas +
                        ", investimentos=" + investimentos +
                        ", receitas=" + receitas +
                        ", totalReceita=" + totalReceita +
                        ", totalDespesas=" + totalDespesas +
                        ", totalInvestimento=" + totalInvestimento +
                        ", usuario=" + usuario +
                        '}'
        );
    }

}
