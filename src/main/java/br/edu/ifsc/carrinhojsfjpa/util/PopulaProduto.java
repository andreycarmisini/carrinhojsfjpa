package br.edu.ifsc.carrinhojsfjpa.util;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.edu.ifsc.carrinhojsfjpa.modelo.Produto;

public class PopulaProduto {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();

		Produto p = new Produto("Caneta", "http://www.mundoreal.xyz/wp-content/uploads/2015/11/Caneta-Bic.jpg",
				new BigDecimal(5));
		Produto p3 = new Produto("Livro", "http://ponteseditores.com.br/loja/image/data/backgroun/livros-pontes.jpg",
				new BigDecimal(15));
		Produto p4 = new Produto("Geladeira",
				"http://www.pontofrio-imagens.com.br/Eletrodomesticos/GeladeiraeRefrigerador/FrostFree/46706/5244305/Refrigerador-Brastemp-Frost-Free-Duplex-Clean-BRM39ER-352-L-Inox-46705.jpg",
				new BigDecimal(1000));
		Produto p5 = new Produto("Videogame da Microsoft",
				"http://www.extra-imagens.com.br/Games/Xbox360/ConsolesXbox360/5573065/190290430/Console-Xbox-360-4GB-2-Controles-Wireless-5573065.jpg",
				new BigDecimal(200));
		Produto p6 = new Produto("Samsung Novo ",
				"http://2.bp.blogspot.com/_zgAz60kWH2Q/TRWVPUM252I/AAAAAAAAEaA/AuelRbQ2u4Q/s1600/Samsung-GalaxyS-Main.jpg",
				new BigDecimal(500));

		manager.persist(p);
		manager.persist(p3);
		manager.persist(p4);
		manager.persist(p5);
		manager.persist(p6);

		manager.getTransaction().commit();

		manager.close();

	}
}
