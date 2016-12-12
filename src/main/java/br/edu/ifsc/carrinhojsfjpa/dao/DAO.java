package br.edu.ifsc.carrinhojsfjpa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifsc.carrinhojsfjpa.modelo.BaseEntity;

public abstract class DAO<T extends BaseEntity> {
	protected final Map<Long, T> LISTA;

	public DAO(Class<T> classe) {
		this.LISTA = new HashMap<>();
	}

	public void adiciona(T t) {
		geraIdEAdiciona(t);
	}

	public void atualiza(T t) {
		LISTA.put(t.getId(), t);
	}

	public T buscaPorId(Long id) {
		return LISTA.get(id);
	}

	public int contaTodos() {
		return LISTA.size();
	}

	abstract void geraDados();

	protected void geraIdEAdiciona(T t) {
		long id = LISTA.size();
		t.setId(id);
		LISTA.put(id, t);
	}

	public List<T> listaTodos() {
		return new ArrayList<T>(LISTA.values());
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		List<T> lista = new ArrayList<>();
		for (int i = firstResult + 1; i < firstResult + 1 + maxResults; i++) {
			lista.add(LISTA.get(i));
		}
		return lista;
	}

	public void remove(Long id) {
		LISTA.remove(id);
	}

	public void remove(T t) {
		remove(t.getId());
	}
}
