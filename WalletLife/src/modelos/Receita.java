package modelos;

import enumerators.TipoDespesaEReceita;
import manipulacaoDinheiro.AbstractMovimentoDinheiro;

public class Receita extends AbstractMovimentoDinheiro<TipoDespesaEReceita> {

    private String banco;

    private String empresa;

    public Receita(TipoDespesaEReceita tipo, double valor, String descricao) {
        super(tipo, valor, descricao);
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Receita{" +
                "banco='" + banco + '\'' +
                ", empresa='" + empresa + '\'' +
                '}';
    }
}
