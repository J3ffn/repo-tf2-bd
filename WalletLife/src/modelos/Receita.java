package modelos;

import enumerators.TipoDespesaEReceita;

public class Receita extends AbstractMovimentoDinheiro<TipoDespesaEReceita> {

    private String banco;

    private String empresa;

    private int idFK;;

    public Receita(){
        super();
    }

    public Receita(TipoDespesaEReceita tipo, double valor, String descricao, int idFK) {
        super(tipo, valor, descricao);
        this.idFK = idFK;
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

    public int getIdFK() {
        return idFK;
    }

    public void setIdFK(int idFK) {
        this.idFK = idFK;
    }

    @Override
    public String toString() {
        return "Receita{" +
                "banco='" + banco + '\'' +
                ", empresa='" + empresa + '\'' +
                '}';
    }
}
