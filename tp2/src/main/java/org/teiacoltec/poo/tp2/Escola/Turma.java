/*
 * Classe que representa uma turma.
 */

package org.teiacoltec.poo.tp2.Escola;

import java.text.SimpleDateFormat; // Classe SimpleDateFormat (para formatar a data)
import java.util.ArrayList; // Classe ArrayList (para armazenar atividades)
import java.util.Date; // Classe Date
import java.util.HashMap; // Classe HashMap (para armazenar atividades)
import java.util.LinkedList; // Classe LinkedList (para armazenar pessoas)

import org.teiacoltec.poo.tp2.Entrada;
import org.teiacoltec.poo.tp2.Excecoes.*;
import org.teiacoltec.poo.tp2.InterfaceDoUsuario;
import org.teiacoltec.poo.tp2.Pessoas.Aluno;
import org.teiacoltec.poo.tp2.Pessoas.Monitor;
import org.teiacoltec.poo.tp2.Pessoas.Pessoa;
import org.teiacoltec.poo.tp2.Pessoas.Professor;
import org.teiacoltec.poo.tp2.Utils;

public class Turma {

    // o "0" não pode ser usado pois pode
    // ser usado para sair, por exemplo
    private static final int ID_PROIBIDO = 0;

    // Todas as turmas existentes
    private static final HashMap<Integer, Turma> Turmas = new HashMap<>(); // Lista de turmas filhas

    private final int ID;
    private String Nome;
    private String Descricao;
    private Date Inicio;
    private Date Fim;
    private final ArrayList<Pessoa> Participantes = new ArrayList<>();
    private Turma Turma_Pai;
    private final ArrayList<Turma> Turmas_Filhas = new ArrayList<>();
    private final HashMap<Integer, Atividade> Atividades = new HashMap<>();

    // Construtor com ID
    public Turma(int id, String nome, String descricao, Date inicio, Date fim) {
        this.ID = id;
        this.Nome = nome;
        this.Descricao = descricao;
        this.Inicio = inicio;
        this.Fim = fim;

        // Adiciona a turma ao hashmap de turmas
        Turmas.put(id, this);
    }

    // Construtor sem ID
    public Turma( String nome, String descricao, Date inicio, Date fim) {
        int id = Turmas.size() + 1;

        this.ID = id;
        this.Nome = nome;
        this.Descricao = descricao;
        this.Inicio = inicio;
        this.Fim = fim;

        // Adiciona a turma ao hashmap de turmas
        Turmas.put(id, this);
    }

    /**
     * Obtém uma lista dos professores da turma
     *
     * @param completa se a lista deve considerar os professores de turmas filhas
     * @return lista dos professores da turma
     */
    public LinkedList<Professor> obtemListaProfessores(boolean completa) {
        LinkedList<Professor> professores = new LinkedList<>();

        // Passa por todas as pessoas, vendo se é professor
        for (Pessoa participante : this.Participantes) {
            if (participante instanceof Professor professor) {
                professores.add(professor);
            }
        }

        // Se não for completa, retorna apenas os professores da turma atual
        if (!completa) return professores;

        // Se for completa, adiciona os professores das turmas filhas
        for (Turma turmaFilha : this.Turmas_Filhas) {
            LinkedList<Professor> professoresFilha = turmaFilha.obtemListaProfessores(true);
            // Olha os professores da turma filha
            for (Professor professor : professoresFilha) {
                // Verifica se já não adicionou
                if (!professores.contains(professor)) {
                    professores.add(professor);
                }
            }
        }

        return professores;
    }

    /**
     * Obtém uma lista dos alunos da turma
     *
     * @param completa se a lista deve considerar os alunos de turmas filhas
     * @return lista dos alunos da turma
     */
    public LinkedList<Aluno> obtemListaAlunos(boolean completa) {
        LinkedList<Aluno> alunos = new LinkedList<>();

        // Passa por todas as pessoas, vendo se é aluno
        for (Pessoa pessoa : this.Participantes) {
            if (pessoa instanceof Aluno aluno) {
                alunos.add(aluno);
            }
        }

        // Se não for completa, retorna apenas os alunos da turma atual
        if (!completa) return alunos;

        // Se for completa, adiciona os alunos das turmas filhas
        for (Turma turmaFilha : this.Turmas_Filhas) {
            LinkedList<Aluno> alunosFilha = turmaFilha.obtemListaAlunos(true);
            // Olha os alunos da turma filha
            for (Aluno aluno : alunosFilha) {
                // Verifica se já não adicionou
                if (!alunos.contains(aluno)) {
                    alunos.add(aluno);
                }
            }
        }

        return alunos;
    }

    /**
     * Obtém uma lista dos monitores da turma
     *
     * @param completa se a lista deve considerar os monitores de turmas filhas
     * @return lista dos monitores da turma
     */
    public LinkedList<Monitor> obtemListaMonitor(boolean completa) {
        LinkedList<Monitor> monitores = new LinkedList<>();

        // Passa por todas as pessoas, vendo se é monitor
        for (Pessoa pessoa : this.Participantes) {
            if (pessoa instanceof Monitor monitor) {
                monitores.add(monitor);
            }
        }

        // Se não for completa, retorna apenas os monitores da turma atual
        if (!completa) return monitores;

        // Se for completa, adiciona os monitores das turmas filhas
        for (Turma turmaFilha : this.Turmas_Filhas) {
            LinkedList<Monitor> monitoresFilha = turmaFilha.obtemListaMonitor(true);
            // Olha os monitores da turma filha
            for (Monitor monitor : monitoresFilha) {
                // Verifica se já não adicionou
                if (!monitores.contains(monitor)) {
                    monitores.add(monitor);
                }
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
     * @throws AtividadeNaoAssociadaException se a atividade não está associada
     */
    public void desassociaAtividade(Atividade atividade) throws AtividadeNaoAssociadaException {
        // Verifica se já está desassociada
        if (!Atividades.containsKey(atividade.getId())) {
            throw new AtividadeNaoAssociadaException(atividade.getNome(), this.getNome());
        }

        // Se chegou até aqui, a atividade está associada, então desassociada
        Atividades.remove(atividade.getId());
    }

    /**
     * Obtém as atividades da turma
     *
     * @param completa se deve retornar as atividades de turmas filhas também
     * @return lista de atividades da turma
     */
    public LinkedList<Atividade> obtemAtividadesDaTurma(boolean completa) {
        LinkedList<Atividade> atividades = new LinkedList<>();

        // Adiciona todas as atividades da turma atual
        atividades.addAll(Atividades.values());

        // Se não for completa, retorna apenas as atividades da turma atual
        if (!completa) return atividades;

        // Se for completa, retorna as atividades das turmas filhas
        for (Turma turmaFilha : this.Turmas_Filhas) {
            LinkedList<Atividade> atividadesFilha = turmaFilha.obtemAtividadesDaTurma(true);
            // Adiciona as atividades da turma filha
            for (Atividade atividade : atividadesFilha) {
                // Verifica se já não adicionou
                if (!atividades.contains(atividade)) {
                    atividades.add(atividade);
                }
            }
        }

        return atividades;
    }

    /**
     * Obtém as atividades da turma
     *
     * @param completa se deve retornar as atividades de turmas filhas também
     * @param inicio data de início para filtrar as atividades
     * @param fim data de fim para filtrar as atividades
     * @return lista de atividades da turma
     */
    public LinkedList<Atividade> obtemAtividadesDaTurma(boolean completa, Date inicio, Date fim) {
        LinkedList<Atividade> atividades = new LinkedList<>();

        // Adiciona as atividades da turma atual
        for (Atividade atividade : Atividades.values()) {
            // Verifica se a atividade está no período especificado
            if (Utils.dataNoPeriodo(atividade.getInicio(), atividade.getFim(), inicio, fim)) {
                atividades.add(atividade);
            }
        }

        // Se não for completa, retorna apenas as atividades da turma atual
        if (!completa) return atividades;

        // Se for completa, retorna as atividades das turmas filhas
        for (Turma turmaFilha : this.Turmas_Filhas) {
            LinkedList<Atividade> atividadesFilha = turmaFilha.obtemAtividadesDaTurma(true, inicio, fim);
            // Adiciona as atividades da turma filha
            for (Atividade atividade : atividadesFilha) {
                // Verifica se já não adicionou. Note que não precisamos
                // verificar o período novamente, pois já foi verificado na turma filha
                if (!atividades.contains(atividade)) {
                    atividades.add(atividade);
                }
            }
        }

        return atividades;
    }

    /**
     * Obtém uma turma pelo ID
     *
     * @param id ID da turma a ser obtida
     * @return Turma correspondente ao ID
     * @throws TurmaNaoEncontradaException se a turma não for encontrada
     */
    public static Turma obtemTurmaPorId(int id) throws TurmaNaoEncontradaException {
        // Verifica se o ID é válido
        if (id < 0) throw new TurmaNaoEncontradaException("Digite um ID valido.");

        // Procura nas turmas filhas
        for (Turma turma : Turmas.values()) {
            if (turma.getId() == id) {
                // Retorna a turma se o id for o mesmo
                return turma;
            }
        }

        // Se chegou até aqui, não encontrou a turma
        throw new TurmaNaoEncontradaException(id);
    }


    /**
     * Obtém uma lista de turmas onde a pessoa participa
     *
     * @param pessoa Pessoa a ser verificada
     * @return Lista de turmas onde a pessoa participa
     */
    public static LinkedList<Turma> obtemTurmasDaPessoa(Pessoa pessoa) {
        LinkedList<Turma> turmas = new LinkedList<>();

        // Percorre todas as turmas procurando onde a pessoa participa
        for (Turma turma : Turmas.values()) {
            // Verifica se a pessoa participa da turma
            if (turma.participa(pessoa)) {
                turmas.add(turma);
            }
        }

        return turmas;
    }

    /**
     * Cria uma tarefa para um aluno específico pela matrícula
     *
     * @param matriculaAluno Matrícula do aluno para quem a tarefa será criada
     * @param atividade Atividade que será atribuída ao aluno
     */
    public void criarTarefaParaAlunoPorMatricula(String matriculaAluno, Atividade atividade)
            throws AtividadeNaoPertenceATurmaException, AlunoNaoEncontradoException, TarefaJaCriadaException {

        // Obtém o aluno pelo ID
        Aluno aluno = Aluno.obterAlunoPorMatricula(matriculaAluno);

        // Verifica se a atividade pertence à turma
        if (!this.Atividades.containsKey(atividade.getId())) {
            throw new AtividadeNaoPertenceATurmaException(atividade.getNome(), this.Nome);
        }

        Tarefa.criarTarefa(aluno, this, atividade);
    }

    /**
     * Cria uma turma com as informações fornecidas pelo usuário
     *
     * @return a nova turma criada
     */
    public static Turma criarTurma() {

        // Pede as informações da turma
        String nome = Entrada.lerString("Digite o nome da turma");
        String descricao = Entrada.lerString("Digite a descricao da turma");
        Date inicio = Entrada.lerData("Digite a data de inicio da turma");
        Date fim = Entrada.lerData("Digite a data de fim da turma");

        // Pergunta se o usuário quer criar a turma com ID ou sem ID
        int id;

        String resposta = Entrada.lerString("Deseja definir o ID da turma? (S/N)", new String[]{"S", "N"}).toUpperCase();
        if (resposta.equals("S")) {
            // Se sim, pede o ID
            int[] ids = Turma.obterTodosIdsTurmas();
            id = Entrada.lerInteiroExceto("Digite o ID da turma", ids);
        } else {
            // Se não, define o ID automaticamente
            id = 0;

            // Incrementa o ID até encontrar um que não esteja em uso
            do {
                id++;
            } while (Turmas.containsKey(id)); // Verifica se o ID já está em uso
        }

        return new Turma(id, nome, descricao, inicio, fim);
    }

    /**
     * Obtém os nomes de todas as turmas
     *
     * @return um array de strings contendo os nomes das turmas
     */
    public static String[] obterTodosNomesTurmas() {
        // Cria uma lista para armazenar os nomes das turmas
        LinkedList<String> nomesTurmas = new LinkedList<>();

        // Adiciona o nome de cada turma à lista
        for (Turma turma : Turmas.values()) {
            nomesTurmas.add(turma.getNome());
        }

        // Converte a lista para um array e retorna
        return nomesTurmas.toArray(new String[0]);
    }

    /**
     * Obtém os nomes de todas as turmas, exceto a turma com o nome especificado
     *
     * @param excecao o nome da turma que deve ser ignorado
     * @return um array de strings contendo os nomes das turmas, exceto a exceção
     */
    public static String[] obterTodosNomesTurmas(String excecao) {
        // Cria uma lista para armazenar os nomes das turmas
        LinkedList<String> nomesTurmas = new LinkedList<>();

        // Adiciona o nome de cada turma à lista
        for (Turma turma : Turmas.values()) {
            // Verifica se o nome da turma é diferente da exceção
            if (turma.getNome().equals(excecao)) continue; // Se for igual, pula
            nomesTurmas.add(turma.getNome());
        }

        // Converte a lista para um array e retorna
        return nomesTurmas.toArray(new String[0]);
    }

    /**
     * Obtém os IDs de todas as turmas
     *
     * @return um array de inteiros contendo os IDs das turmas
     */
    public static int[] obterTodosIdsTurmas() {
        // Cria uma lista para armazenar os IDs das turmas
        LinkedList<Integer> idsTurmas = new LinkedList<>();

        // Adiciona o ID proibido
        idsTurmas.add(ID_PROIBIDO);

        // Adiciona o ID de cada turma à lista
        for (Turma turma : Turmas.values()) {
            idsTurmas.add(turma.getId());
        }

        // Converte a lista para um array e retorna
        // Obs.: poderia ter feito um for, por exemplo,
        //       mas assim é mais elegante :)
        return idsTurmas.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Obtém os IDs de todas as turmas
     *
     * @param excecao o ID da turma que deve ser ignorado
     * @return um array de inteiros contendo os IDs das turmas
     */
    public static int[] obterTodosIdsTurmas(int excecao) {
        // Cria uma lista para armazenar os IDs das turmas
        LinkedList<Integer> idsTurmas = new LinkedList<>();

        // Adiciona o ID proibido
        idsTurmas.add(ID_PROIBIDO);

        // Adiciona o ID de cada turma à lista
        for (Turma turma : Turmas.values()) {
            // Verifica se o ID da turma é diferente da exceção
            if (turma.getId() == excecao) continue; // Se for igual, pula
            idsTurmas.add(turma.getId());
        }

        // Converte a lista para um array e retorna
        return idsTurmas.stream().mapToInt(Integer::intValue).toArray();
    }

    private void desassociarMinhasFilhas() {
        // Desassocia as turmas filhas
        for (Turma turmaFilha : this.Turmas_Filhas) {
            turmaFilha.setTurmaPai(null); // Remove a referência à turma pai
        }
        this.Turmas_Filhas.clear(); // Limpa a lista de turmas filhas
    }

    public static void apagarTurmaDaLista(Turma turma) throws TurmaNaoEncontradaException {
        // Verifica se a turma existe
        if (Turmas.containsKey(turma.getId())) {
            // Desassocia as filhas
            turma.desassociarMinhasFilhas();
            // Remove a turma
            Turmas.remove(turma.getId());
        } else {
            // Se não existir, lança uma exceção
            throw new TurmaNaoEncontradaException(turma.getId());
        }
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


    // Setters //
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


    // Getters //
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

    public static HashMap<Integer, Turma> getTurmas() {
        return Turmas;
    }

}
