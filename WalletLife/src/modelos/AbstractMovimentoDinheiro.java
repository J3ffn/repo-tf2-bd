package modelos;

import manipulacaoDinheiro.GerenciadorFinancas;

public abstract class AbstractMovimentoDinheiro<T> {

    protected T tipo;

    private int id;

    private double valor;

    private String descricao;

    public AbstractMovimentoDinheiro(T tipo, double valor, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
    }

    public AbstractMovimentoDinheiro() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getTipo() {
        return this.tipo;
    }

    public void setTipo(T tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDecricao(String descricao) {
        this.descricao = descricao;
    }
}
