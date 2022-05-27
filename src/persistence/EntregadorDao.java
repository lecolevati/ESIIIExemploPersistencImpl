package persistence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Entregador;

public class EntregadorDao implements Dao<Entregador> {

	private SessionFactory sf;

	public EntregadorDao(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void insert(Entregador ent) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(ent);
		transaction.commit();
	}

	@Override
	public void update(Entregador ent) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(ent);
		transaction.commit();
	}

	@Override
	public void delete(Entregador ent) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(ent);
		transaction.commit();
	}

	@Override
	public Entregador selectOne(Entregador ent) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		ent = entityManager.find(Entregador.class, ent.getId());
		return ent;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entregador> selectAll() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT f.id, f.nome, f.data_nascimento, f.salario, ent.cnh, ent.categoria_cnh ");
		sql.append("FROM funcionario f, entregador ent ");
		sql.append("WHERE f.id = ent.id");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> atendentesResultSet = query.getResultList();
		List<Entregador> entregadores = new ArrayList<Entregador>();
		for (Object[] o : atendentesResultSet) {
			Entregador ent = new Entregador();
			ent.setId(Integer.parseInt(o[0].toString()));
			ent.setNome(o[1].toString());
			ent.setDataNascimento(LocalDate.parse(o[2].toString()));
			ent.setSalario(Float.parseFloat(o[3].toString()));
			ent.setCnh(Long.parseLong(o[4].toString()));
			ent.setCategoriaCnh(o[5].toString());

			entregadores.add(ent);
		}

		return entregadores;
	}

}
