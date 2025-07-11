package org.teiacoltec.poo.tp2;

import org.teiacoltec.poo.tp2.Excecoes.PessoaForaDaListaException;
import org.teiacoltec.poo.tp2.Excecoes.PessoaNaoEncontradaException;
import org.teiacoltec.poo.tp2.Pessoas.Pessoa;

public class FuncoesMenu extends InterfaceDoUsuario {
    public static void criarPessoa() {
        Pessoa pessoa = Pessoa.criarPessoa();
        System.out.println("Pessoa criada com as informacoes:\n");
        imprimirInformacoes(pessoa);

        imprimirLinha();
        Entrada.esperaEnter();
    }
    
    public static void removerPessoa() {
        String cpf = Entrada.lerString("Digite o CPF da pessoa que deseja apagar");
        try {
            Pessoa pessoa = Pessoa.obtemPessoaPorCPF(cpf);
            Pessoa.apagarPessoaDaLista(pessoa);
            System.out.printf("Pessoa com CPF %s apagada com sucesso.\n", cpf);
        } catch (PessoaForaDaListaException e) {
            System.out.printf("A pessoa com CPF %s esta fora da lista.\n", cpf);
        } catch (PessoaNaoEncontradaException e) {
            System.out.printf("A pessoa com CPF %s nao foi encontrada.\n", cpf);
        }
        Entrada.esperaEnter();
    }

    public static void atualizarPessoa() {
        String cpf = Entrada.lerString("Digite o CPF da pessoa que deseja atualizar");
        try {
            Pessoa pessoa = Pessoa.obtemPessoaPorCPF(cpf);
            int opcao;
            do {
                System.out.println("""
                        Qual atributo deseja alterar?
                        0 - Sair 
                        1 - Nome (%s)
                        2 - Email (%s)
                        3 - Data de Nascimento (%s)
                        4 - Endereco (%s)
                        """.formatted(pessoa.getNome(), pessoa.getEmail(), Utils.data_ddMMaaaa(pessoa.getNascimento()), pessoa.getEndereco()));


                opcao = Entrada.lerInteiro("Opcao");

                switch (opcao) {
                    case 1 -> pessoa.setNome(Entrada.lerString("Digite o novo nome"));
                    case 2 -> pessoa.setEmail(Entrada.lerString("Digite o novo email"));
                    case 3 -> pessoa.setNascimento(Entrada.lerData("Digite a nova data de nascimento"));
                    case 4 -> pessoa.setEndereco(Entrada.lerString("Digite o novo endereco"));
                    case 0 -> System.out.println("Saiu da atualizacao.");
                    default -> System.out.println("Opcao invalida!");
                }
            } while (opcao != 0);

        } catch (PessoaNaoEncontradaException e) {
            System.out.printf("A pessoa com CPF %s nao foi encontrada.\n", cpf);
        }
        Entrada.esperaEnter();
    }

    public static void listarPessoas() {
        for (Pessoa p : Pessoa.getPessoas().values()) {
            imprimirInformacoes(p);
        }

        Entrada.esperaEnter();
    }



}
