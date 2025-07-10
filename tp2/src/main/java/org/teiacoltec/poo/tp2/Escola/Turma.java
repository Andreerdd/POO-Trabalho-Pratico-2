/*
 * Classe que representa uma turma.
 */

package org.teiacoltec.poo.tp2.Escola;

import java.text.SimpleDateFormat; // Classe SimpleDateFormat (para formatar a data)
import java.util.ArrayList; // Classe ArrayList (para armazenar atividades)
import java.util.Date; // Classe Date
import java.util.HashMap; // Classe HashMap (para armazenar atividades)
import java.util.LinkedList; // Classe LinkedList (para armazenar pessoas)

import org.teiacoltec.poo.tp2.Excecoes.AtividadeJaAssociadaException;
import org.teiacoltec.poo.tp2.Excecoes.AtividadeNaoEncontradaException;
import org.teiacoltec.poo.tp2.Excecoes.PessoaJaParticipanteException;
import org.teiacoltec.poo.tp2.Excecoes.PessoaNaoEncontradaException;
import org.teiacoltec.poo.tp2.Excecoes.TurmaJaEstaAssociadaException;
import org.teiacoltec.poo.tp2.InterfaceDoUsuario;
import org.teiacoltec.poo.tp2.Pessoas.Aluno;
import org.teiacoltec.poo.tp2.Pessoas.Monitor;
import org.teiacoltec.poo.tp2.Pessoas.Pessoa;
import org.teiacoltec.poo.tp2.Pessoas.Professor;

public class Turma {

    private final int ID;
    private String Nome;
    private String Descricao;
    private Date Inicio;
    private Date Fim;
    private final ArrayList<Pessoa> Participantes = new ArrayList<>();
    private Turma Turma_Pai;
    private final ArrayList<Turma> Turmas_Filhas = new ArrayList<>();
    private final HashMap<Integer, Atividade> Atividades = new HashMap<>();

    public Turma(int id, String nome, String descricao, Date inicio, Date fim) {
        this.ID = id;
        this.Nome = nome;
        this.Descricao = descricao;
        this.Inicio = inicio;
        this.Fim = fim;
    }

    /**
     * Obtém uma lista dos professores da turma
     * 
     * @return lista dos professores da turma
     */
    public LinkedList<Professor> obtemListaProfessores() {
        LinkedList<Professor> professores = new LinkedList<>();

        // Passa por todas as pessoas, vendo se é professor
        for (Pessoa participante : this.Participantes) {
            if (participante instanceof Professor professor) {
                professores.add(professor);
            }
        }

        return professores;
    }

    /**
     * Obtém uma lista dos alunos da turma
     * 
     * @return lista dos alunos da turma
     */
    public LinkedList<Aluno> obtemListaAlunos() {
        LinkedList<Aluno> alunos = new LinkedList<>();

        // Passa por todas as pessoas, vendo se é aluno
        for (Pessoa pessoa : this.Participantes) {
            if (pessoa instanceof Aluno aluno) {
                alunos.add(aluno);
            }
        }

        return alunos;
    }

    /**
     * Obtém uma lista dos monitores da turma
     * 
     * @return lista dos monitores da turma
     */
    public LinkedList<Monitor> obtemListaMonitor() {
        LinkedList<Monitor> monitores = new LinkedList<>();

        // Passa por todas as pessoas, vendo se é monitor
        for (Pessoa pessoa : this.Participantes) {
            if (pessoa instanceof Monitor monitor) {
                monitores.add(monitor);
            }
        }

        return monitores;
    }

    /**
     * Associa a atividade à turma
     * 
     * @param atividade atividade que será associada
     * @throws AtividadeJaAssociadaException se a atividade já está associada à turma
     */
    public void associaAtividade(Atividade atividade) throws AtividadeJaAssociadaException {
        // Verifica se já está associada
        if (Atividades.containsKey(atividade.getId())) {
            throw new AtividadeJaAssociadaException(atividade.getNome(), this.getNome());
        }

        // Se chegou até aqui, a atividade não está associada
        Atividades.put(atividade.getId(), atividade);
    }

    /**
     * Desassocia a atividade à turma
     * 
     * @param atividade atividade que será desassociada
     * @throws AtividadeNaoEncontradaException se a atividade não está desassociada 
     */
    public void desassociaAtividade(Atividade atividade) throws AtividadeNaoEncontradaException {
        // Verifica se já está desassociada
        if (!Atividades.containsKey(atividade.getId())) {
            throw new AtividadeNaoEncontradaException(atividade.getNome(), this.getNome());
        }

        // Se chegou até aqui, a atividade está associada, então desassociada
        Atividades.remove(atividade.getId());
    }

    private int procurarPessoa(Pessoa pessoa) throws PessoaNaoEncontradaException {
        // Verifica se o vetor está vazio
        if (Participantes.isEmpty()) throw new PessoaNaoEncontradaException(pessoa.getNome(), this.Nome);

        // Tenta procurar a pessoa no vetor
        int pos = Participantes.indexOf(pessoa);
        if (pos != -1) return pos; // Encontrou a pessoa

        // Se chegou até aqui, a pessoa não está na turma
        throw new PessoaNaoEncontradaException(pessoa.getNome(), this.Nome);
    }

    public ArrayList<Pessoa> obtemListaParticipantes() {
        return this.Participantes;
    }

    // Adiciona um participante a lista de participantes
    public void adicionarParticipante(Pessoa participante) throws PessoaJaParticipanteException {
        // Verifica se está vazio
        if (this.Participantes.isEmpty()) {
            // Cria o primeiro participante
            this.Participantes.add(participante);
            return;
        }

        // Se já participa, lança uma exceção
        if (participa(participante)) {
            throw new PessoaJaParticipanteException(participante.getNome(), this.Nome);
        }

        // Se chegou até aqui, é porque a pessoa não está na turma. Então, adiciona ela
        this.Participantes.add(participante);
    }

    public void removerParticipante(Pessoa participante) throws PessoaNaoEncontradaException {
        // Verifica se a pessoa está na turma
        int pos;
        try {
            pos = procurarPessoa(participante);     
        } catch (PessoaNaoEncontradaException e) {
            throw new PessoaNaoEncontradaException(participante.getNome(), this.Nome);
        }

        // Remove o participante da lista de participantes
        this.Participantes.remove(pos);

        // Retira o participante das turmas filhas (se houver turma filha e
        // se o participante estiver em alguma turma filha)
        if (!this.Turmas_Filhas.isEmpty()) {
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
        if (this.Participantes.isEmpty()) {
            this.Turmas_Filhas.add(turma);

            // Coloca a turma pai da sub turma como a turma atual
            turma.setTurmaPai(this);

            // Para o código aqui, pois não há mais o que fazer
            return;
        }

        // Verifica se a turma já está associada a outra turma
        for (Turma turmaFilha : this.Turmas_Filhas) {
            if (turmaFilha == turma) {
                throw new TurmaJaEstaAssociadaException(this.Nome, turma.getNome());
            }
        }

        // Adiciona a nova turma
        this.Turmas_Filhas.add(turma);

        // Coloca a turma pai da sub turma como a turma atual
        turma.setTurmaPai(this);
    }


    // Obtém as informações da turma
    public String ObterInformacoes(boolean informacaoCompleta) {
        // Formata a data de nascimento
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String inicioFormatado = formato.format(this.Inicio);
        String fimFormatado = formato.format(this.Fim);

        String resultado = """
                || Turma: %s
                || ID: %d
                || Descricao:
                	%s
                || Data de Inicio: %s
                || Data de Fim: %s""".formatted(this.Nome, this.ID, this.Descricao, inicioFormatado, fimFormatado);

        
        // Verifica se a pessoa quer a informação completa
        if (informacaoCompleta) {
            // Obtém algumas informações da turma pai (se existir)
            if (this.Turma_Pai != null) {
                resultado += """
                        
                        --------------------
                        || Nome da turma pai: %s
                        || ID da turma pai: %d""".formatted(this.Turma_Pai.getNome(), this.Turma_Pai.getId());

            } else {
                resultado += """
                        
                        --------------------
                        || Nao possui turma pai""";
            }

            // Obtém algumas informações das turmas filhas (se houver)
            if (!this.Turmas_Filhas.isEmpty()) {
                String turmasFilhas = "";

                for (Turma turmaFilha : this.Turmas_Filhas)
                    turmasFilhas += "\n||\t" + turmaFilha.getNome() + " (ID: " + turmaFilha.getId() + ")";

                resultado += """
                        
                        --------------------
                        || Turmas filhas:%s""".formatted(turmasFilhas);
            } else {
                resultado += """
                        
                        --------------------
                        || Nao possui turmas filhas.""";
            }
            
            // Obtém os nomes dos alunos da turma (se tiver)
            if (!this.Turmas_Filhas.isEmpty()) {
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
                    resultado += """
                            
                            --------------------
                            || Alunos da turma:%s""".formatted(alunos);
                } else {
                    resultado += """
                            
                            --------------------
                            || Nao possui alunos.""";
                }

                // Se tiver algum professor, adiciona ele(s)
                if (!professores.isEmpty()) {
                    resultado += """
                            
                            --------------------
                            || Professores da turma:%s""".formatted(professores);
                } else {
                    resultado += """
                            
                            --------------------
                            || Nao possui professores.""";
                }

            } else {
                resultado += """
                        
                        --------------------
                        || Nao possui alunos ou professores.""";
            }
        }
        
        InterfaceDoUsuario.imprimirLinha();

        // Mostra as atividades
        for (Atividade att : Atividades.values()) {
            System.out.println(att.ObterInformacoes());
            InterfaceDoUsuario.imprimirLinha();
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

    public ArrayList<Turma> getTurmasFilhas() {
        return this.Turmas_Filhas;
    }

    public Turma getTurmaPai() {
        return this.Turma_Pai;
    }

}
