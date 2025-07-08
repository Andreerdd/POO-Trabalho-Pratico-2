package org.teiacoltec.poo.tp2.Escola;

import java.text.SimpleDateFormat; // Classe Date
import java.util.Arrays; // Classe Arrays
import java.util.Date; // Classe SimpleDateFormat (para formatar a data)

import org.teiacoltec.poo.tp2.Excecoes.PessoaJaParticipanteException;
import org.teiacoltec.poo.tp2.Excecoes.PessoaNaoEncontradaException;
import org.teiacoltec.poo.tp2.Excecoes.TurmaJaEstaAssociadaException;
import org.teiacoltec.poo.tp2.Pessoas.Aluno;
import org.teiacoltec.poo.tp2.Pessoas.Pessoa;

public class Turma {
    
    private final int ID;
    private String Nome;
    private String Descricao;
    private Date Inicio;
    private Date Fim;
    private Pessoa[] Participantes;
    private Turma Turma_Pai;
    private Turma[] Turmas_Filhas;

    public Turma(int id, String nome, String descricao, Date inicio, Date fim) {
        this.ID = id;
        this.Nome = nome;
        this.Descricao = descricao;
        this.Inicio = inicio;
        this.Fim = fim;
    }

    private int procurarPessoa(Pessoa pessoa) throws PessoaNaoEncontradaException {
        // Verifica se o vetor está vazio
        if (Participantes == null) throw new PessoaNaoEncontradaException(pessoa.getNome(), this.Nome);

        // Tenta procurar a pessoa no vetor
        for (int pos = 0; pos < Participantes.length; pos++) {
            if (Participantes[pos] == pessoa) { // Aqui, eu poderia verificar pelo CPF também (já que é único)
                return pos;
            }
        }

        // Se chegou até aqui, a pessoa não está na turma
        throw new PessoaNaoEncontradaException(pessoa.getNome(), this.Nome);
    }

    public Pessoa[] obtemListaParticipantes() {
        return this.Participantes;
    }

    // Adiciona um participante a lista de participantes
    public void adicionarParticipante(Pessoa participante) throws PessoaJaParticipanteException {
        // Verifica se o vetor está vazio
        if (this.Participantes == null) {
            // Cria o primeiro participante
            this.Participantes = new Pessoa[1];
            this.Participantes[0] = participante;

            return;
        }

        // Se já participa, lança uma exceção
        if (participa(participante)) {
            throw new PessoaJaParticipanteException(participante.getNome(), this.Nome);
        }

        // Se chegou até aqui, é porque a pessoa não está na turma. Então, adiciona ela

        // Copia o vetor com mais um espaço
        Pessoa[] novoVetor = Arrays.copyOfRange(this.Participantes, 0, this.Participantes.length + 1);

        // Adiciona o novo participante ao vetor
        novoVetor[novoVetor.length - 1] = participante;

        // Atualiza a lista de participantes
        this.Participantes = novoVetor;


    }

    public void removerParticipante(Pessoa participante) throws PessoaNaoEncontradaException {
        int pos;
        try {
            pos = procurarPessoa(participante);     
        } catch (PessoaNaoEncontradaException e) {
            throw new PessoaNaoEncontradaException(participante.getNome(), this.Nome);
        }

        Pessoa[] antes = Arrays.copyOfRange(Participantes, 0, pos);
        Pessoa[] depois = Arrays.copyOfRange(Participantes, pos+1, Participantes.length);
        Pessoa[] novoVetor = new Pessoa[antes.length + depois.length]; // ou Participantes.length - 1
        for (int i = 0; i < antes.length; i++) {
            novoVetor[i] = antes[i];
        }
        for (int i = 0; i < depois.length; i++) {
            novoVetor[antes.length + i] = depois[i];
        }

        this.Participantes = novoVetor;

        // Retira o participante das turmas filhas (se houver turma filha e
        // se o participante estiver em alguma turma filha)
        if (this.Turmas_Filhas != null) {
            for (Turma turma : this.Turmas_Filhas) {
                try {
                    turma.removerParticipante(participante);
                } catch (PessoaNaoEncontradaException e ) {
                    // Ignora, pois está tudo bem a pessoa não estar em nenhuma turma filha
                }
            }
        }
    }

    public Boolean participa(Pessoa pessoa) {
        try {
            // Retorna verdadeiro se encontrar a pessoa nos participantes
            procurarPessoa(pessoa);
            return true;
        } catch (PessoaNaoEncontradaException e) {
            // Retorna falso se não encontrar a pessoa nos participantes
            return false;
        }
    }
  
    public void associaSubturma(Turma turma) throws TurmaJaEstaAssociadaException {
        // Verifica se o vetor está vazio
        if (this.Turmas_Filhas == null) {
            this.Turmas_Filhas = new Turma[1];
            this.Turmas_Filhas[0] = turma;

            // Coloca a turma pai da subturma como a turma atual
            turma.setTurmaPai(this);

            return;
        }

        // Verifica se a turma já está associada a outra turma
        for (Turma turmaFilha : this.Turmas_Filhas) {
            if (turmaFilha == turma) {
                throw new TurmaJaEstaAssociadaException(this.Nome, turma.getNome());
            }
        }

        // Copia o vetor com mais um espaço
        Turma[] novoVetor = Arrays.copyOfRange(this.Turmas_Filhas, 0, this.Turmas_Filhas.length + 1);

        // Adiciona a nova turma ao vetor
        novoVetor[novoVetor.length - 1] = turma;

        // Coloca a turma pai da subturma como a turma atual
        turma.setTurmaPai(this);

        // Atualiza a lista de turmas filhas
        this.Turmas_Filhas = novoVetor;
    }

    public Turma getTurmaPai() {
        return this.Turma_Pai;
    }

    // Obtém as informações da turma
    public String ObterInformacoes() {
        String resultado = "";

        // Formata a data de nascimento
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String inicioFormatado = formato.format(this.Inicio);
        String fimFormatado = formato.format(this.Fim);

        // Obtém as informações básicas da turma
        resultado +=   "|| Turma: " + this.Nome 
                   + "\n|| ID: " + this.ID
                   + "\n|| Descricao:\n\t" + this.Descricao
                   + "\n|| Data de Inicio: " + inicioFormatado 
                   + "\n|| Data de Fim: " + fimFormatado;

        // Obtém algumas informações da turma pai (se existir)
        if (this.Turma_Pai != null) {
            resultado += "\n--------------------" 
                       + "\n|| Nome da turma pai: " + this.Turma_Pai.getNome() 
                       + "\n|| ID da turma pai: " + this.Turma_Pai.getId();
            
        } else {
            resultado += "\n--------------------" 
                       + "\n|| Nao possui turma pai";
        }

        // Obtém algumas informações das turmas filhas (se houver)
        if (this.Turmas_Filhas != null) {
            String turmasFilhas = "";

            for (Turma turmaFilha : this.Turmas_Filhas) turmasFilhas += "\n||\t" + turmaFilha.getNome() + " (ID: " + turmaFilha.getId() + ")";

            resultado += "\n--------------------"
                       + "\n|| Turmas filhas:" + turmasFilhas;
        } else {
            resultado += "\n--------------------"
                       + "\n|| Nao possui turmas filhas.";
        }

        // Obtém os nomes dos alunos da turma (se tiver)
        if (this.Participantes != null) {
            String alunos = "";
            String professores = "";

            // Obtém os alunos e os professores
            for (Pessoa participante : this.Participantes) {
                // Verifica se o participante é um aluno
                if (participante instanceof Aluno) {
                    alunos += "\n||\t" + participante.getNome() + " (Email: " + participante.getEmail() + ")"; 
                } else { // Se não for aluno, é professor
                    professores += "\n||\t" + participante.getNome() + " (Email: " + participante.getEmail() + ")";
                }
            } 

            // Se tiver algum aluno, adiciona ele(s)
            if (!alunos.isEmpty()) {
                resultado += "\n--------------------"
                + "\n|| Alunos da turma:" + alunos;
            } else {
                resultado += "\n--------------------"
                + "\n|| Nao possui alunos.";
            }

            // Se tiver algum professor, adiciona ele(s)
            if (!professores.isEmpty()) {
                resultado += "\n--------------------"
                + "\n|| Professores da turma:" + professores;
            } else {
                resultado += "\n--------------------"
                + "\n|| Nao possui professores.";
            }
 
        } else {
            resultado += "\n--------------------"
                       + "\n|| Nao possui alunos ou professores.";
        }


        return resultado;
    }

    // Sets
    public void setNome(String nome) {
        this.Nome = nome;
    }

    public void setDescricao(String descricao) {
        this.Descricao = descricao;
    }

    public void setInicio(Date inicio) {
        this.Inicio = inicio;
    }

    public void setFim(Date fim) {
        this.Fim = fim;
    }

    public void setTurmaPai(Turma turma) {
        this.Turma_Pai = turma;
    }


    // Gets
    public String getNome() {
        return this.Nome;
    }

    public int getId() {
        return this.ID;
    }

    public String getDescricao() {
        return this.Descricao;
    }

    public Date getInicio() {
        return this.Inicio;
    }

    public Date getFim() {
        return this.Fim;
    }

    public Turma[] getTurmasFilhas() {
        return this.Turmas_Filhas;
    }
    
}
