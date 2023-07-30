package modelos;

import enumerators.TipoDespesaEReceita;

public class Despesa extends AbstractMovimentoDinheiro<TipoDespesaEReceita> {

    private String dataPagamento;

    private int  idFK;

    public Despesa(TipoDespesaEReceita tipoDespesa, double valor, String descricao, String dataPagamento, int idFK) {
        super(tipoDespesa, valor, descricao);
        this.dataPagamento = dataPagamento;
        this.idFK = idFK;

    }

    public Despesa(){
        super();
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

    public int getIdFK() {
        return idFK;
    }

    public void setIdFK(int idFK) {
        this.idFK = idFK;
    }
}
