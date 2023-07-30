package manipulacaoDinheiro;

import modelos.AbstractMovimentoDinheiro;

import java.util.HashMap;
import java.util.HashSet;

public interface IManipularFinancas {

    double calcularTotal(HashMap<?, ?> lista);

    double calcularReceitaTotal();

    double calcularInvestimentos();

}
