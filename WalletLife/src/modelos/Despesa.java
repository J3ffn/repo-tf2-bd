package modelos;

import enumerators.TipoDespesaEReceita;

import java.time.LocalDate;

public class Despesa extends AbstractMovimentoDinheiro<TipoDespesaEReceita> {

    private LocalDate dataPagamento;

    public Despesa(TipoDespesaEReceita tipoDespesa, double valor, String descricao, LocalDate dataPagamento) {
        super(tipoDespesa, valor, descricao);
        this.dataPagamento = dataPagamento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "dataPagamento='" + dataPagamento + '\'' +
                '}';
    }
}
