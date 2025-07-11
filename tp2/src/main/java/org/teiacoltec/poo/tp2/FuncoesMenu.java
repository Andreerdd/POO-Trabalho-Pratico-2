package org.teiacoltec.poo.tp2;

// Imports
import org.teiacoltec.poo.tp2.Escola.*;
import org.teiacoltec.poo.tp2.Excecoes.*;
import org.teiacoltec.poo.tp2.Pessoas.*;

import java.util.Date;


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
            System.out.printf("A pessoa com CPF %s nao foi encontrada.\nCertifique-se de estar usando o CPF ao inves da matricula, por exemplo.", cpf);
        }
        Entrada.esperaEnter();
    }

    public static void listarPessoas() {
        for (Pessoa p : Pessoa.getPessoas().values()) {
            imprimirInformacoes(p);
        }

        Entrada.esperaEnter();
    }

    // //


    public static void criarTurma() {
        Turma turma = Turma.criarTurma();
        System.out.println("Turma criada com as informacoes:");
        imprimirInformacoes(turma);

        Entrada.esperaEnter();
    }

    public static void removerTurma() {
        int[] idsTurma = Turma.obterTodosIdsTurmas();
        int id = Entrada.lerInteiro("Digite o id da turma que deseja apagar", idsTurma);
        try {
            Turma turma = Turma.obtemTurmaPorId(id);
            Turma.apagarTurmaDaLista(turma);
            System.out.printf("Turma com id %d apagada com sucesso.\n", id);
        } catch (TurmaNaoEncontradaException e) {
            System.out.printf("A turma com id %d nao foi encontrada.\n", id);
        }
        Entrada.esperaEnter();
    }

    public static void atualizarTurma() {
        int[] idsTurma = Turma.obterTodosIdsTurmas();
        int id = Entrada.lerInteiro("Digite o id da turma que deseja atualizar", idsTurma);
        try {
            Turma turma = Turma.obtemTurmaPorId(id);
            String nome, descricao, inicio, fim, turma_Pai;


            int opcao;
            do {
                // Obtém os atributos novamente

                nome = turma.getNome();
                descricao = turma.getDescricao();
                inicio = Utils.data_ddMMaaaa(turma.getInicio());
                fim = Utils.data_ddMMaaaa(turma.getFim());
                turma_Pai = turma.getTurmaPai() != null ? turma.getTurmaPai().getNome() : "Sem Turma Pai";

                // Exibe o menu de opções
                System.out.println("""
                        Qual atributo deseja alterar?
                        0 - Sair 
                        1 - Nome (%s)
                        2 - Descricao (%s)
                        3 - Data de Inicio (%s)
                        4 - Data de Fim (%s)
                        5 - Turma Pai (%s)
                        """.formatted(nome, descricao, inicio, fim, turma_Pai));


                opcao = Entrada.lerInteiro("Opcao");

                switch (opcao) {
                    case 1 -> turma.setNome(Entrada.lerString("Digite o novo nome"));
                    case 2 -> turma.setDescricao(Entrada.lerString("Digite a nova descricao"));
                    case 3 -> turma.setInicio(Entrada.lerData("Digite a nova data de inicio"));
                    case 4 -> turma.setFim(Entrada.lerData("Digite a nova data de fim"));
                    case 5 -> {
                        int[] idsTurmas = Turma.obterTodosIdsTurmas(turma.getId());
                        // Verifica se existem turmas cadastradas
                        if (idsTurmas.length == 0) {
                            System.out.println("Nao existem turmas cadastradas para serem selecionadas como turma pai.");
                            break;
                        }

                        // Se chegou aqui, existem turmas cadastradas
                        int entrada = Entrada.lerInteiro("Digite o id da turma pai", idsTurmas);
                        Turma pai = Turma.obtemTurmaPorId(entrada);
                        try {
                            pai.associaSubturma(turma);
                        } catch (TurmaJaEstaAssociadaException e) {
                            System.out.printf("A turma %s ja esta associada a turma %s.\n", turma.getNome(), pai.getNome());
                        }
                    }
                    case 0 -> {
                        System.out.println("Saiu da atualizacao.");
                    }
                    default -> System.out.println("Opcao invalida!");
                }
            } while (opcao != 0);

        } catch (TurmaNaoEncontradaException e) {
            System.out.printf("A turma com id %d nao foi encontrada.\n", id);
        }
        Entrada.esperaEnter();
    }

    public static void listarTurmas() {
        for (Turma t : Turma.getTurmas().values()) {
            imprimirInformacoes(t);
        }

        Entrada.esperaEnter();
    }

    // //

    public static void criarAtividade() {
        Atividade atividade = Atividade.criarAtividade();
        System.out.println("Atividade criada com as informacoes:");
        imprimirInformacoes(atividade);

        Entrada.esperaEnter();
    }

    public static void removerAtividade() {
        int[] idsAtividade = Atividade.obterTodosIdsAtividades();
        int id = Entrada.lerInteiro("Digite o id da atividade que deseja apagar", idsAtividade);
        try {
            Atividade atividade = Atividade.obtemAtividadePorId(id);
            Atividade.apagarAtividadeDaLista(atividade);
            System.out.printf("Atividade com id %d apagada com sucesso.\n", id);
        } catch (AtividadeNaoEncontradaException e) {
            System.out.printf("A atividade com id %d nao foi encontrada.\n", id);
        }
        Entrada.esperaEnter();
    }

    public static void atualizarAtividade() {
        int[] idsAtividade = Atividade.obterTodosIdsAtividades();
        int id = Entrada.lerInteiro("Digite o id da atividade que deseja atualizar", idsAtividade);

        try {
            Atividade atividade = Atividade.obtemAtividadePorId(id);
            String nome, descricao, inicio, fim;
            Float valor;

            int opcao;
            do {
                nome = atividade.getNome();
                descricao = atividade.getDescricao();
                inicio = Utils.data_ddMMaaaa(atividade.getInicio());
                fim = Utils.data_ddMMaaaa(atividade.getFim());
                valor = atividade.getValor();

                System.out.println("""
                        Qual atributo deseja alterar?
                        0 - Sair 
                        1 - Nome (%s)
                        2 - Descricao (%s)
                        3 - Data de Inicio (%s)
                        4 - Data de Fim (%s)
                        5 - Valor (%.2f)
                        """.formatted(nome, descricao, inicio, fim, valor));

                opcao = Entrada.lerInteiro("Opcao");

                switch (opcao) {
                    case 1 -> atividade.setNome(Entrada.lerString("Digite o novo nome"));
                    case 2 -> atividade.setDescricao(Entrada.lerString("Digite a nova descricao"));
                    case 3 -> atividade.setInicio(Entrada.lerData("Digite a nova data de inicio"));
                    case 4 -> atividade.setFim(Entrada.lerData("Digite a nova data de fim"));
                    case 5 -> atividade.setValor(Entrada.lerFloat("Digite o novo valor"));
                    case 0 -> System.out.println("Saiu da atualizacao.");
                    default -> System.out.println("Opcao invalida!");
                }
            } while (opcao != 0);

        } catch (AtividadeNaoEncontradaException e) {
            System.out.printf("A atividade com id %d nao foi encontrada.\n", id);
        }
        Entrada.esperaEnter();
    }

    public static void listarAtividades() {
        for (Atividade a : Atividade.getAtividades().values()) {
            imprimirInformacoes(a);
        }

        Entrada.esperaEnter();
    }
    
    // //

    // Métodos privados //
    
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


}
