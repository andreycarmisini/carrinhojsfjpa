package br.edu.ifsc.carrinhojsfjpa.util;

import javax.persistence.EntityManager;

import br.edu.ifsc.carrinhojsfjpa.modelo.Usuario;

public class PopulaUsuario {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();

		Usuario u = new Usuario("andrey@andrey.com", "Andrey", "andrey");
		Usuario u2 = new Usuario("teste@teste.com", "Teste", "teste");

		manager.persist(u);
		manager.persist(u2);

		manager.getTransaction().commit();

		manager.close();

	}
}
