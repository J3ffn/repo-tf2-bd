package modelos;

import enumerators.TipoDespesaEReceita;

public class Despesa extends AbstractMovimentoDinheiro<TipoDespesaEReceita> {

    private String dataPagamento;

    public Despesa(TipoDespesaEReceita tipoDespesa, double valor, String descricao, String dataPagamento) {
        super(tipoDespesa, valor, descricao);
        this.dataPagamento = dataPagamento;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "dataPagamento='" + dataPagamento + '\'' +
                '}';
    }
}
