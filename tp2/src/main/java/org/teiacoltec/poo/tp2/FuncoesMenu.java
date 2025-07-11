package org.teiacoltec.poo.tp2;

import org.teiacoltec.poo.tp2.Excecoes.PessoaForaDaListaException;
import org.teiacoltec.poo.tp2.Excecoes.PessoaNaoEncontradaException;
import org.teiacoltec.poo.tp2.Pessoas.*;

public class FuncoesMenu extends InterfaceDoUsuario {
    public static void criarPessoa() {
        Pessoa pessoa = Pessoa.criarPessoa();
        System.out.println("Pessoa criada com as informacoes:");
        imprimirInformacoes(pessoa);

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
            String atributoExtra = getAtributoExtra(pessoa);
            String nome, email, nascimento, endereco, matricula;




            int opcao;
            do {
                // Obtém os atributos novamente

                // Obtém a matrícula
                if (pessoa instanceof Matriculado matriculado) {
                    matricula = matriculado.getMatricula();
                } else {
                    // Isso aqui nunca deve acontecer, mas é uma precaução
                    matricula = "Sem Matricula";
                }

                nome = pessoa.getNome();
                email = pessoa.getEmail();
                nascimento = Utils.data_ddMMaaaa(pessoa.getNascimento());
                endereco = pessoa.getEndereco();

                // Exibe o menu de opções
                System.out.println("""
                        Qual atributo deseja alterar?
                        0 - Sair 
                        1 - Nome (%s)
                        2 - Email (%s)
                        3 - Data de Nascimento (%s)
                        4 - Endereco (%s)
                        5 - Matricula (%s)
                        %s
                        """.formatted(nome, email, nascimento, endereco, matricula, atributoExtra));


                opcao = Entrada.lerInteiro("Opcao");

                switch (opcao) {
                    case 1 -> pessoa.setNome(Entrada.lerString("Digite o novo nome"));
                    case 2 -> pessoa.setEmail(Entrada.lerString("Digite o novo email"));
                    case 3 -> pessoa.setNascimento(Entrada.lerData("Digite a nova data de nascimento"));
                    case 4 -> pessoa.setEndereco(Entrada.lerString("Digite o novo endereco"));
                    case 5 -> {
                        if (pessoa instanceof Matriculado matriculado) {
                            String[] matriculas = Pessoa.obterTodasMatriculas(matriculado.getMatricula());
                            matriculado.setMatricula(Entrada.lerStringExceto("Digite a nova matricula", matriculas));
                        } else {
                            System.out.println("A pessoa nao possui matricula.");
                        }
                    }
                    case 6 -> {
                        if (pessoa instanceof Aluno aluno) {
                            aluno.setCurso(Entrada.lerString("Digite o novo curso"));
                        } else if (pessoa instanceof Professor professor) {
                            professor.setFormacao(Entrada.lerString("Digite a nova formacao"));
                        } else if (pessoa instanceof Monitor monitor) {
                            monitor.setCurso(Entrada.lerString("Digite o novo curso"));
                        } else {
                            System.out.println("Opcao invalida!");
                        }
                    }
                    case 0 -> System.out.println("Saiu da atualizacao.");
                    default -> System.out.println("Opcao invalida!");
                }
            } while (opcao != 0);

        } catch (PessoaNaoEncontradaException e) {
            System.out.printf("A pessoa com CPF %s nao foi encontrada.\n", cpf);
        }
        Entrada.esperaEnter();
    }

    private static String getAtributoExtra(Pessoa pessoa) {
        String atributoExtra = "6 - ";

        if (pessoa instanceof Aluno) {
            atributoExtra += "Curso (%s)".formatted(((Aluno) pessoa).getCurso());
        } else if (pessoa instanceof Professor) {
            atributoExtra += "Formacao (%s)".formatted(((Professor) pessoa).getFormacao());
        } else if (pessoa instanceof Monitor) {
            atributoExtra += "Curso (%s)".formatted(((Monitor) pessoa).getCurso());
        } else {
            atributoExtra = "";
        }
        return atributoExtra;
    }

    public static void listarPessoas() {
        for (Pessoa p : Pessoa.getPessoas().values()) {
            imprimirInformacoes(p);
        }

        Entrada.esperaEnter();
    }



}
