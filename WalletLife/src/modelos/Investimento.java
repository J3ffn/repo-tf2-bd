package modelos;

public class Investimento extends AbstractMovimentoDinheiro<String> {

    protected String corretora;

    private String dataInicio;

    public Investimento(double valor, String descricao, String corretora, String dataInicio) {
        super("Investimento", valor, descricao);
        this.corretora = corretora;
        this.dataInicio = dataInicio;
    }

    public String getCorretora() {
        return corretora;
    }

    public void setCorretora(String corretora) {
        this.corretora = corretora;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public String toString() {
        return "Ivestimento{" +
                "corretora='" + corretora + '\'' +
                ", dataInicio='" + dataInicio + '\'' +
                '}';
    }
}
