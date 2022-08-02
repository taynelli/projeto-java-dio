 package br.com.dio.desafio;

import br.com.dio.desafio.dominio.Bootcamp;
import br.com.dio.desafio.dominio.Conteudo;
import br.com.dio.desafio.dominio.Curso;
import br.com.dio.desafio.dominio.Dev;

public class Main {
	public static void main(String[] args) {
		//CRIANDO CURSO DE JAVA
		Curso cursoOrientacaoAObjetos = new Curso();
		cursoOrientacaoAObjetos.setTitulo("P.O.O.");
		cursoOrientacaoAObjetos.setDescricao("Curso de Programação Orientada a Objetos");
		cursoOrientacaoAObjetos.setCargaHoraria(16);

		//CRIANDO CURSO DE ORIENTACAO A OBJETOS
		Curso cursoJava = new Curso();
		cursoJava.setTitulo("Desmistificando Java");
		cursoJava.setDescricao("Aprenda Java com os especialistas da DIO");
		cursoJava.setCargaHoraria(40);

		//CRIANDO O BOOTCAMP
		Bootcamp bootCampDIO = new Bootcamp();
		bootCampDIO.setNome("BootCamp - DIO - Dev Java");
		//ADICIONANDO OS CURSOS AO BOOTCAMP
		bootCampDIO.addConteudo(cursoOrientacaoAObjetos);
		bootCampDIO.addConteudo(cursoJava);
		
		bootCampDIO.reportTodosCursosPorDev(null);

		// CRIANDO DEV CAMILA
		Dev devCamila = new Dev();
		devCamila.setNome("Camila");

		// CRIANDO DEV TAYNELLI
		Dev devTaynelli = new Dev();
		devTaynelli.setNome("Taynelli K");

		// DEVS SE INSCREVENDO NO BOOTCAMP
		devCamila.inscreverBootcamp(bootCampDIO);
		devTaynelli.inscreverBootcamp(bootCampDIO);
		
		//REPORT DE CURSOS - TAYNELLI 
		System.out.println("Dev " + devTaynelli.getNome() + " antes de realizar algum curso");
		bootCampDIO.reportTodosCursosPorDev(devTaynelli);
		devTaynelli.progredir();
		System.out.println("Dev " + devTaynelli.getNome() + " depois de realizar um curso");
		bootCampDIO.reportTodosCursosPorDev(devTaynelli);

		// REPORT DE CURSOS - CAMILLA
		System.out.println("Dev " + devCamila.getNome() + " antes de realizar algum curso");
		bootCampDIO.reportTodosCursosPorDev(devCamila);
		for(Conteudo cont : bootCampDIO.getConteudos()) {
			System.out.println("Dev " + devCamila.getNome() + " iniciando curso de " + cont.getTitulo());
			devCamila.progredir();
		}

		System.out.println("\nDev " + devCamila.getNome() + " depois de realizar todos os cursos");
		bootCampDIO.reportTodosCursosPorDev(devCamila);
		
		bootCampDIO.reportTodosCursosPorDev(null);
	}
}