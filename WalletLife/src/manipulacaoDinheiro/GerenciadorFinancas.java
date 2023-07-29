package manipulacaoDinheiro;

import enumerators.TipoDespesaEReceita;
import modelos.*;

public class GerenciadorFinancas implements IManipularFinancas, IImpressao {

    private final Usuario usuario;

    private ListFinancas<Despesa> despesas;

    private ListFinancas<Investimento> investimentos;

    private ListFinancas<Receita> receitas;

    private double totalReceita;

    private double totalDespesas;

    private double totalInvestimento;

    public GerenciadorFinancas(Usuario usuario) {
        this.despesas = new ListFinancas<>();
        this.receitas = new ListFinancas<>();
        this.investimentos = new ListFinancas<>();
        this.usuario = usuario;
    }

    @Override
    public double calcularDespesaTotal() {
        if (totalDespesas == 0) {
            for (AbstractMovimentoDinheiro<TipoDespesaEReceita> despesa : despesas.getLista().values()) {
                totalDespesas += despesa.getValor();
            }
        }
        return totalDespesas;
    }

    @Override
    public double calcularReceitaTotal() {
        if (totalReceita == 0) {
            for (AbstractMovimentoDinheiro<TipoDespesaEReceita> receita : receitas.getLista().values()) {
                totalReceita += receita.getValor();
            }
        }
        return totalReceita;
    }

    @Override
    public double calcularInvestimentos() {
        if (totalInvestimento == 0) {
            for (AbstractMovimentoDinheiro<String> investimento : investimentos.getLista().values()) {
                totalInvestimento += investimento.getValor();
            }
        }
        return totalInvestimento;
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

    public ListFinancas<Despesa> getDespesas() {
        return despesas;
    }

    public void addDespesa(Despesa despesa) {
        despesa.setId(despesas.size());
        this.despesas.add(despesa);
        totalDespesas += despesa.getValor();
    }

    public void updateValorDespesa(int id, double valor) {
        this.totalDespesas -= despesas.get(id).getValor();
        this.despesas.get(id).setValor(valor);
        this.totalDespesas += valor;
        this.despesas.update(id, despesas.get(id));
    }

    public void updateDescricaoDespesa(int id, String descricao) {
        this.despesas.get(id).setDescricao(descricao);
        this.despesas.update(id, despesas.get(id));
    }

    public boolean deleteDespesa(int id) {
        this.totalDespesas -= despesas.get(id).getValor();
        return despesas.delete(id, despesas.get(id));
    }

    public ListFinancas<Investimento> getInvestimentos() {
        return investimentos;
    }

    public void addInvestimento(Investimento investimento) {
        investimento.setId(investimentos.size());
        this.investimentos.add(investimento);
        this.totalInvestimento += investimento.getValor();
    }

    public void updateValorInvestimento(int id, double valor) {
        this.totalInvestimento -= investimentos.get(id).getValor();
        this.investimentos.get(id).setValor(valor);
        this.totalInvestimento += investimentos.get(id).getValor();
        this.investimentos.update(id, investimentos.get(id));
    }

    public void updateDescricaoInvestimento(int id, String descricao) {
        this.investimentos.get(id).setDescricao(descricao);
        this.investimentos.update(id, investimentos.get(id));
    }

    public boolean deleteInvestimento(int id) {
        this.totalInvestimento -= investimentos.get(id).getValor();
        return this.investimentos.delete(id, investimentos.get(id));
    }

    public ListFinancas<Receita> getReceitas() {
        return receitas;
    }

    public void addReceita(Receita receita) {
        receita.setId(receita.getId());
        receitas.add(receita);
        totalReceita += receita.getValor();
    }

    public void updateValorReceita(int id, double valor) {
        this.totalReceita -= receitas.get(id).getValor();
        this.receitas.get(id).setValor(valor);
        this.totalReceita += receitas.get(id).getValor();
        this.receitas.update(id, receitas.get(id));
    }

    public void updateDescricaoReceita(int id, String descricao) {
        this.receitas.get(id).setDescricao(descricao);
        this.receitas.update(id, receitas.get(id));
    }

    public boolean deleteReceita(int id) {
        totalReceita -= receitas.get(id).getValor();
        return receitas.delete(id, receitas.get(id));
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
}
