package br.com.dio.desafio.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Bootcamp {
	private String descricao;
	private String nome;
	private final LocalDate dataInicial = LocalDate.now();
	private final LocalDate dataFinal = dataInicial.plusDays(45);
	private Set<Dev> devsInscritos = new HashSet<>();
	private Set<Conteudo> conteudos = new LinkedHashSet<>();
	
	//CRIAR CONSTRUTOR QUE RECEBE DATA INICIAL E FINAL DO BOOTCAMP
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public Set<Dev> getDevsInscritos() {
		return devsInscritos;
	}

	public void setDevsInscritos(Set<Dev> devsInscritos) {
		this.devsInscritos = devsInscritos;
	}

	public Set<Conteudo> getConteudos() {
		return conteudos;
	}

	public void setConteudos(Set<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}
	
	public void addConteudo(Conteudo conteudo) {
		this.conteudos.add(conteudo);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Bootcamp bootcamp = (Bootcamp) o;
		return Objects.equals(nome, bootcamp.nome) && Objects.equals(descricao, bootcamp.descricao)
				&& Objects.equals(dataInicial, bootcamp.dataInicial) && Objects.equals(dataFinal, bootcamp.dataFinal)
				&& Objects.equals(devsInscritos, bootcamp.devsInscritos)
				&& Objects.equals(conteudos, bootcamp.conteudos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, descricao, dataInicial, dataFinal, devsInscritos, conteudos);
	}

	/**
	 * Método que gera pequeno relatório com nome do desenvolvedor e os cursos, inscritos ou concluidos, variando com o parametro passado
	 * @param devFiltro - Filtro por desenvolvedor. Caso seja omitido, verifica todos os desenvolvedores
	 */
	private void reportCursosPorDevsETipoCurso(Optional<Dev> devFiltro) {

		List<Dev> lstDevShow = (devFiltro.isEmpty() ? new ArrayList<>(this.devsInscritos) : 
			this.devsInscritos
			.stream()
			.filter(obj -> obj.getNome().equalsIgnoreCase(devFiltro.get().getNome())).toList());

		if(devFiltro.isEmpty()) {
			System.out.println("=========================================");
			System.out.println("= TODOS OS DEVS CADASTRADOS NO BOOTCAMP =");
			System.out.println("=========================================");
		}
			

		if(!lstDevShow.isEmpty()) {
			for(Dev dev : lstDevShow) {
				System.out.println("=========================================");
				System.out.println("========== Dev " + dev.getNome() + " ==========");
				this.reportDetailCursosPorDevETipo(false, dev);
				this.reportDetailCursosPorDevETipo(true, dev);
				System.out.println("Total XP: " + dev.calcularTotalXp());
				System.out.println("=========================================\n\n");
			}
		}
		else {
			System.out.println("== Nao ha desenvolvedores cadastrados ==");
			System.out.println("=========================================\n\n");
		}
		
	}

	private void reportDetailCursosPorDevETipo(boolean isFiltroCursosConcluidos, Dev dev) {
		Set<Conteudo> conteudos = (isFiltroCursosConcluidos) ? dev.getConteudosConcluidos() : dev.getConteudosInscritos();
		String msgInicial = (isFiltroCursosConcluidos) ? "Cursos Concluidos" : "Cursos Inscritos";
		System.out.println("========== " + msgInicial + " ==========");
		if(!conteudos.isEmpty()) {
			for(Conteudo conteudo : conteudos) {
				if(conteudo instanceof Curso) System.out.println("Curso: " + conteudo.getTitulo() + " - Carga Horaria: " + ((Curso) conteudo).getCargaHoraria());	//USO DO INSTANCEOF E CAST
				else {
					System.out.println("Conteudo Titulo: " + conteudo.getTitulo() + " - Descricao: " + conteudo.getDescricao());
				}
			}
		}
		else {
			String msgNoCourse = "O desenvolvedor nao " + (isFiltroCursosConcluidos ? "concluiu" : "se inscreveu em") + " nenhum curso";
			System.out.println(msgNoCourse);
		}
		
	}
	
	public void reportTodosCursosPorDev(Dev devPesquisa) {
		this.reportCursosPorDevsETipoCurso(devPesquisa == null ? Optional.empty() : Optional.of(devPesquisa));
	}
}
