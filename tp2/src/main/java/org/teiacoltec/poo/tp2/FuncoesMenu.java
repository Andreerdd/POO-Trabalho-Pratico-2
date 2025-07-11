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

    }

    public static void listarPessoas() {
        for (Pessoa p : Pessoa.getPessoas().values()) {
            imprimirInformacoes(p);
        }

        Entrada.esperaEnter();
    }

}
