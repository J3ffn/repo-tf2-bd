import enumerators.TipoDespesaEReceita;
import manipulacaoDinheiro.GerenciadorFinancas;
import modelos.Despesa;
import modelos.Investimento;
import modelos.Receita;
import modelos.Usuario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /** @Início */
        Usuario userJeff = new Usuario("Jeff", "01/01/1111", "93812739812", "Jeff@gmail.com", "Testando");
        GerenciadorFinancas gerenciadorFinancas = new PlanejamentoFinanceiroPessoal(userJeff);

        int[] escolhas = {-2, -2, -2};
        Scanner sc = new Scanner(System.in);
        while (escolhas[0] != 0) {
            try {
                System.out.println("""
                        Menu:
                        1 - Despesa
                        2 - Investimento
                        3 - Receita
                        4 - Relatório
                        0 - Sair""");
                System.out.print("Escolha: ");
                escolhas[0] = sc.nextInt();

                System.out.println();
                if (escolhas[0] > 0 && escolhas[0] <= 4 && escolhas[0] != 4) {
                    do {
                        System.out.println("""
                                1 - Adicionar
                                2 - Remover
                                3 - Atualizar
                                4 - Total
                                -1 - Voltar""");
                        System.out.print("Escolha: ");
                        escolhas[1] = sc.nextInt();
                    } while (escolhas[1] != -1 && escolhas[1] < 1 || escolhas[1] > 4 );

                    System.out.println();
                    do {
                        switch (escolhas[1]) {
                            case 1:
                                System.out.print("Valor: ");
                                double valor = sc.nextDouble();

                                sc.nextLine();

                                System.out.print("Descrição: ");
                                String descricao = sc.nextLine();

                                System.out.print("Data: ");
                                String data = sc.nextLine();
                                Integer tipoDespesa = null;
                                switch (escolhas[0]) {
                                    case 1:
                                        do {
                                            System.out.println("""
                                                    1- FIXA
                                                    2- VARIÁVEL
                                                    """);
                                            tipoDespesa = sc.nextInt();
                                            if (tipoDespesa > 0 && tipoDespesa < 3) {
                                                switch (tipoDespesa) {
                                                    case 1:
                                                        gerenciadorFinancas.addDespesa(new Despesa(TipoDespesaEReceita.FIXA, valor, descricao, data));
                                                        break;
                                                    case 2:
                                                        gerenciadorFinancas.addDespesa(new Despesa(TipoDespesaEReceita.VARIAVEL, valor, descricao, data));
                                                }
                                            }
                                        } while (tipoDespesa < 1 || tipoDespesa > 2);
                                        break;
                                    case 2:
                                        System.out.print("Data início: ");
                                        String dataInicio = sc.next();
                                        System.out.print("Corretora: ");
                                        String corretora = sc.next();
                                        gerenciadorFinancas.addInvestimento(new Investimento(valor, descricao, corretora, dataInicio));
                                        break;
                                    case 3:
                                        do {
                                            System.out.println("""
                                                    1- FIXA
                                                    2- VARIÁVEL
                                                    """);
                                            tipoDespesa = sc.nextInt();
                                            if (tipoDespesa > 0 && tipoDespesa < 3) {
                                                switch (tipoDespesa) {
                                                    case 1:
                                                        gerenciadorFinancas.addReceita(new Receita(TipoDespesaEReceita.FIXA, valor, descricao));
                                                        break;
                                                    case 2:
                                                        gerenciadorFinancas.addReceita(new Receita(TipoDespesaEReceita.VARIAVEL, valor, descricao));
                                                }
                                            }
                                        } while (tipoDespesa < 1 || tipoDespesa > 2);
                                        break;
                                }
                                break;
                            case 2:
                                Integer id = null;
                                Integer qtdd = null;
                                switch (escolhas[0]) {
                                    case 1:
                                        qtdd = gerenciadorFinancas.getDespesas().getLista().size();
                                        gerenciadorFinancas.getDespesas().getLista().forEach((key, despesa) -> System.out.println("Id: " + key + " Despesa: " + despesa));
                                        if (qtdd > 0) {
                                            do {
                                                System.out.print("Id: ");
                                                id = sc.nextInt();
                                            } while (id < 0 || id > qtdd - 1);
                                                gerenciadorFinancas.deleteDespesa(id);
                                        }
                                        break;
                                    case 2:
                                        qtdd = gerenciadorFinancas.getInvestimentos().getLista().size();
                                        gerenciadorFinancas.getInvestimentos().getLista().forEach((key, investimento) -> System.out.println("Id: " + key + " Investimento: " + investimento));
                                        if (qtdd > 0) {
                                            do {
                                                System.out.print("Id: ");
                                                id = sc.nextInt();
                                            } while (id < 0 || id > qtdd - 1);
                                            gerenciadorFinancas.deleteInvestimento(id);
                                        }
                                        break;
                                    case 3:
                                        qtdd = gerenciadorFinancas.getReceitas().getLista().size();
                                        gerenciadorFinancas.getReceitas().getLista().forEach((key, receita) -> System.out.println("Id: " + key + " Receita: " + receita));
                                        if (qtdd > 0) {
                                            do {

                                                System.out.print("Id: ");
                                                id = sc.nextInt();
                                                gerenciadorFinancas.deleteReceita(id);
                                            } while (id < 0 || id > qtdd - 1);
                                        }
                                        break;
                                }
                                break;

                            case 3:
                                Integer tipoAtualizacao = null;
                                int qtddDespesas = gerenciadorFinancas.getDespesas().getLista().size();;
                                int qtddInvestimentos = gerenciadorFinancas.getInvestimentos().getLista().size();
                                int qtddReceita = gerenciadorFinancas.getReceitas().getLista().size();
                                boolean continuarAtualizacao = true;

                                switch (escolhas[0]) {
                                    case 1:
                                        if (qtddDespesas == 0)
                                            continuarAtualizacao = false;
                                        break;
                                    case 2:
                                        if (qtddInvestimentos == 0)
                                            continuarAtualizacao = false;
                                        break;
                                    case 3:
                                        if (qtddReceita == 0)
                                            continuarAtualizacao = false;
                                        break;
                                }

                                double novoValor = 0;
                                String novaDescricao = "";

                                if (continuarAtualizacao) {
                                    do {
                                        System.out.println("""
                                                O que será atualizado?
                                                1 - Valor
                                                2 - Descricao
                                                """);
                                        System.out.print("Escolha: ");
                                        tipoAtualizacao = sc.nextInt();
                                    } while (tipoAtualizacao < 1 || tipoAtualizacao > 2);


                                    switch (tipoAtualizacao) {
                                        case 1:
                                            System.out.print("Novo valor: ");
                                            novoValor = sc.nextDouble();
                                            break;
                                        case 2:
                                            System.out.print("Nova descrição: ");
                                            novaDescricao = sc.nextLine();
                                            break;
                                    }
                                }

                                switch (escolhas[0]) {
                                    case 1:
                                        gerenciadorFinancas.getDespesas().getLista().forEach((key, despesa) -> System.out.println("Id: " + key + " Despesa: " + despesa));
                                        if (qtddDespesas > 0) {
                                            do {
                                                System.out.print("Id: ");
                                                id = sc.nextInt();
                                            } while (id < 0 || id > qtddDespesas - 1);
                                                if (tipoAtualizacao == 1) {
                                                    gerenciadorFinancas.updateValorDespesa(id, novoValor);
                                                } else {
                                                    gerenciadorFinancas.updateDescricaoDespesa(id, novaDescricao);
                                                }
                                        }
                                        break;
                                    case 2:
                                        gerenciadorFinancas.getInvestimentos().getLista().forEach((key, investimento) -> System.out.println("Id: " + key + " Investimento: " + investimento));
                                        if (qtddInvestimentos > 0) {
                                            do {
                                                System.out.print("Id: ");
                                                id = sc.nextInt();
                                            } while (id < 0 || id > qtddInvestimentos - 1);
                                            if (tipoAtualizacao == 1) {
                                                gerenciadorFinancas.updateValorInvestimento(id, novoValor);
                                            } else {
                                                gerenciadorFinancas.updateDescricaoInvestimento(id, novaDescricao);
                                            }
                                        }
                                        break;
                                    case 3:
                                        gerenciadorFinancas.getReceitas().getLista().forEach((key, receita) -> System.out.println("Id: " + key + " Receita: " + receita));
                                        if (qtddReceita > 0) {
                                            do {
                                                System.out.print("Id: ");
                                                id = sc.nextInt();
                                            } while (id < 0 || id > qtddReceita - 1);
                                            if (tipoAtualizacao == 1) {
                                                gerenciadorFinancas.updateValorReceita(id, novoValor);
                                            } else {
                                                gerenciadorFinancas.updateDescricaoReceita(id, novaDescricao);
                                            }
                                        }
                                        break;
                                }

                                break;

                            case 4:
                                System.out.println("Total: ");
                                switch (escolhas[0]) {
                                    case 1:
                                        System.out.println(gerenciadorFinancas.getValorTotalDespesas());
                                        break;
                                    case 2:
                                        System.out.println(gerenciadorFinancas.getValorTotalInvestimento());
                                        break;
                                    case 3:
                                        System.out.println(gerenciadorFinancas.getValorTotalReceita());
                                        break;
                                }
                                System.out.println();
                                break;

                        }
                        System.out.println();
                    } while (escolhas[1] < -1 || escolhas[1] > 4);
                }
            } catch (Exception e) {
                System.out.println("Erro: você deve digitar um número válido.");
                sc.nextLine();
            }
            if (escolhas[0] == 4) {
                System.out.println(gerenciadorFinancas.relatorioGeral());
            }
        }

    }
}