import enumerators.TipoDespesaEReceita;
import manipulacaoDinheiro.GerenciadorFinancas;
import manipulacaoDinheiro.PlanejamentoFinanceiroPessoal;
import modelos.Despesa;
import modelos.Investimento;
import modelos.Receita;
import modelos.Usuario;
import service.UsuarioService;
import utils.AbstractFormatoEmail;
import utils.AbstractValidarData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static boolean validarData(String data) {
        boolean valida = AbstractValidarData.validarData(data);
        if (!valida) {
            System.out.println("Data inválida!");
        }
        return valida;
    }

    public static boolean validarEmail(String email) {
        boolean valida = AbstractFormatoEmail.formatadorEmail(email);
        if (!valida) {
            System.out.println("Formato de email inválido!");
        }
        return valida;
    }

    public static void main(String[] args) {

        /** @Início */
        Scanner sc = new Scanner(System.in);
        Usuario usuario = null;
        GerenciadorFinancas gerenciadorFinancas;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        boolean logado = false;
        do {
            boolean voltar;
            Integer logarOuRegistrar;
            do {
                voltar = false;
                // Registro:
                System.out.print("""
                        \u001b[32m- BEM-VINDO(A) AO WALLET LIFE -\u001b[m
                        Opções disponíveis:
                        \u001b[36m1 - Logar.\u001b[m
                        \u001b[34m2 - Registrar-se.\u001b[m
                        \u001b[31m0 - Sair.\u001b[m
                         """);
                System.out.print("Escolha: ");
                do {
                    logarOuRegistrar = sc.nextInt();
                    sc.nextLine();
                } while (logarOuRegistrar > 2 || logarOuRegistrar < 0);

                if (logarOuRegistrar == 0) {
                    break;
                }

                while (usuario == null && logarOuRegistrar == 2) {
                    System.out.println("\nInsira 0 em qualquer etapa do cadastro para voltar à tela de login.\n");
                    System.out.print("Nome completo: ");
                    String nomeCompleto = sc.nextLine();
                    if ((nomeCompleto.equals("0"))) {
                        voltar = true;
                        break;
                    }

                    System.out.print("Data de nascimento: ");
                    String dataPedida = sc.nextLine();
                    if (dataPedida.equals("0")) {
                        voltar = true;
                        break;
                    }

                    while (!validarData(dataPedida)) {
                        dataPedida = sc.next();
                    }

                    LocalDate dataNascimento = LocalDate.parse(dataPedida, formatter);

                    System.out.print("CPF: ");
                    String cpf = sc.next();
                    if (cpf.equals("0")) {
                        voltar = true;
                        break;
                    }

                    String email = "";
                    boolean checado = false;
                    do {
                        System.out.print("Email: ");
                        email = sc.next();
                        if (email.equals("0")) {
                            voltar = true;
                            break;
                        }
                        if (!AbstractFormatoEmail.formatadorEmail(email)) {
                            System.out.println("Formato de e-mail invalido!\n");
                        } else if (UsuarioService.validarEmail(email)) {
                            System.out.println("E-mail ja em uso! Digite outro email\n");

                        } else {
                            checado = true;
                        }
                    } while (!checado);

                    if (email.equals("0")) {
                        voltar = true;
                        break;
                    }

                    System.out.print("Senha: ");
                    String senha = sc.next();

                    usuario = new Usuario(nomeCompleto, dataNascimento, cpf, email.toLowerCase(), senha);
                    UsuarioService.adicionarUsuario(usuario);
                }

                if (voltar) {
                    continue;
                }
                logado = false;
                // Login:
                while (!logado) {

                    System.out.println("\nInsira os dados de login:\n");
                    System.out.print("E-mail: ");
                    String email = sc.next();

                    System.out.print("Senha: ");
                    String senha = sc.next();

                    usuario = UsuarioService.login(email, senha);
                    if (usuario != null) {
                        logado = true;
                        System.out.println();
                    } else {
                        System.out.print("Tentar novamente? 1 - sim, 2 - não: ");
                        int escolha = sc.nextInt();
                        if (escolha == 2) {
                            voltar = true;
                            System.out.println();
                            break;
                        }
                    }
                }
            } while (!logado);

            if (logarOuRegistrar == 0) {
                break;
            }

            if (voltar) {
                continue;
            }

            gerenciadorFinancas = new PlanejamentoFinanceiroPessoal(usuario);

            int[] escolhasManipulacao = {-2, -2, -2};
            while (escolhasManipulacao[0] != 0 && logado) {
                try {
                    System.out.println("""
                            Menu:
                            1 - Despesa
                            2 - Investimento
                            3 - Receita
                            4 - Relatório
                            0 - Trocar de conta""");
                    System.out.print("\nEscolha: ");
                    escolhasManipulacao[0] = sc.nextInt();

                    if (escolhasManipulacao[0] == 0) {
                        usuario = null;
                        logado = false;
                    }

                    System.out.println();
                    if (escolhasManipulacao[0] > 0 && escolhasManipulacao[0] <= 4 && escolhasManipulacao[0] != 4) {
                        do {
                            System.out.println("""
                                    1 - Adicionar
                                    2 - Remover
                                    3 - Atualizar
                                    4 - Total
                                    -1 - Voltar""");
                            System.out.print("Escolha: ");
                            escolhasManipulacao[1] = sc.nextInt();
                        } while (escolhasManipulacao[1] != -1 && escolhasManipulacao[1] < 1 || escolhasManipulacao[1] > 4);

                        System.out.println();
                        do {
                            switch (escolhasManipulacao[1]) {
                                case 1:
                                    System.out.print("Valor: ");
                                    double valor = sc.nextDouble();

                                    sc.nextLine();

                                    System.out.print("Descrição: ");
                                    String descricao = sc.nextLine();

                                    System.out.print("Data: ");
                                    String dataString = sc.next();
                                    while (!validarData(dataString)) {
                                        dataString = sc.next();
                                    }

                                    LocalDate data = LocalDate.parse(dataString, formatter);
                                    Integer tipoDespesa = null;
                                    switch (escolhasManipulacao[0]) {
                                        case 1:

                                            do {
                                                System.out.println("""
                                                        TIPO:
                                                        1- FIXA
                                                        2- VARIÁVEL
                                                        """);
                                                tipoDespesa = sc.nextInt();
                                                if (tipoDespesa > 0 && tipoDespesa < 3) {
                                                    switch (tipoDespesa) {
                                                        case 1:
                                                            gerenciadorFinancas.addDespesa(new Despesa(TipoDespesaEReceita.FIXA, valor, descricao, data, usuario.getId()));
                                                            break;
                                                        case 2:
                                                            gerenciadorFinancas.addDespesa(new Despesa(TipoDespesaEReceita.VARIAVEL, valor, descricao, data, usuario.getId()));
                                                    }

                                                }
                                            } while (tipoDespesa < 1 || tipoDespesa > 2);
                                            break;
                                        case 2:
                                            LocalDate dataInicio = data;
                                            System.out.print("Corretora: ");
                                            String corretora = sc.next();
                                            Investimento investimento = new Investimento(valor, descricao, corretora, dataInicio, usuario.getId());
                                            gerenciadorFinancas.addInvestimento(investimento);

                                            break;
                                        case 3:
                                            sc.nextLine();
                                            System.out.print("Banco: ");
                                            String banco = sc.nextLine();
                                            System.out.print("Empresa: ");
                                            String empresa = sc.nextLine();
                                            gerenciadorFinancas.addReceita(new Receita(valor, descricao, banco, empresa, usuario.getId()));
                                            break;
                                    }
                                    break;
                                case 2:
                                    Integer id = null;
                                    Integer qtdd = null;
                                    switch (escolhasManipulacao[0]) {
                                        case 1:
                                            qtdd = gerenciadorFinancas.getDespesas().size();
                                            gerenciadorFinancas.getDespesas().forEach((key, despesa) -> {
                                                System.out.println();
                                                System.out.println("------------------------------");
                                                System.out.println("\u001b[32mId: " + key + "\u001B[m");
                                                System.out.println(despesa);
                                                System.out.println();
                                            });
                                            if (qtdd > 0) {
                                                do {
                                                    System.out.print("Id: ");
                                                    id = sc.nextInt();
                                                } while (id < 0 || id > qtdd - 1);
                                                gerenciadorFinancas.deleteDespesa(id);
                                            }
                                            break;
                                        case 2:
                                            qtdd = gerenciadorFinancas.getInvestimentos().size();
                                            gerenciadorFinancas.getInvestimentos().forEach((key, investimento) -> {
                                                System.out.println();
                                                System.out.println("------------------------------");
                                                System.out.println("\u001b[32mId: " + key + "\u001B[m");
                                                System.out.println(investimento);
                                                System.out.println();
                                            });
                                            if (qtdd > 0) {
                                                do {
                                                    System.out.print("Id: ");
                                                    id = sc.nextInt();
                                                } while (id < 0 || id > qtdd - 1);
                                                gerenciadorFinancas.deleteInvestimento(id);
                                            }
                                            break;
                                        case 3:
                                            qtdd = gerenciadorFinancas.getReceitas().size();
                                            gerenciadorFinancas.getReceitas().forEach((key, receita) -> {
                                                System.out.println();
                                                System.out.println("------------------------------");
                                                System.out.println("\u001b[32mId: " + key + "\u001B[m");
                                                System.out.println(receita);
                                                System.out.println();
                                            });
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
                                    int qtddDespesas = gerenciadorFinancas.getDespesas().size();
                                    ;
                                    int qtddInvestimentos = gerenciadorFinancas.getInvestimentos().size();
                                    int qtddReceita = gerenciadorFinancas.getReceitas().size();
                                    boolean continuarAtualizacao = true;

                                    switch (escolhasManipulacao[0]) {
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
                                                sc.nextLine(); // Lixo
                                                novaDescricao = sc.nextLine();
                                                break;
                                        }
                                    }

                                    switch (escolhasManipulacao[0]) {
                                        case 1:
                                            gerenciadorFinancas.getDespesas().forEach((key, despesa) -> {
                                                System.out.println();
                                                System.out.println("------------------------------");
                                                System.out.println("\u001b[32mId: " + key + "\u001B[m");
                                                System.out.println(despesa);
                                                System.out.println();
                                            });
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
                                            gerenciadorFinancas.getInvestimentos().forEach((key, investimento) -> {
                                                System.out.println();
                                                System.out.println("------------------------------");
                                                System.out.println("\u001b[32mId: " + key + "\u001B[m");
                                                System.out.println(investimento);
                                                System.out.println();
                                            });
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
                                            gerenciadorFinancas.getReceitas().forEach((key, receita) -> {
                                                System.out.println();
                                                System.out.println("------------------------------");
                                                System.out.println("\u001b[32mId: " + key + "\u001B[m");
                                                System.out.println(receita);
                                                System.out.println();
                                            });
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
                                    sc.nextLine();
                                    break;

                                case 4:
                                    System.out.println("Total: ");
                                    switch (escolhasManipulacao[0]) {
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
                        } while (escolhasManipulacao[1] < -1 || escolhasManipulacao[1] > 4);
                    }
                } catch (Exception e) {
                    System.out.println("Erro: você deve digitar um número válido.");
                    sc.nextLine();
                }
                if (escolhasManipulacao[0] == 4) {
                    System.out.println(gerenciadorFinancas.relatorioGeral());
                }
            }

        } while (!logado);

    }
}
