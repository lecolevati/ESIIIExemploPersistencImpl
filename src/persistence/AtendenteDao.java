package persistence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Atendente;

public class AtendenteDao implements Dao<Atendente> {

	private SessionFactory sf;
	
	public AtendenteDao(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void insert(Atendente at) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(at);
		transaction.commit();			
	}

	@Override
	public void update(Atendente at) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(at);
		transaction.commit();			
	}

	@Override
	public void delete(Atendente at) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(at);
		transaction.commit();			
	}

	@Override
	public Atendente selectOne(Atendente at) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		at = entityManager.find(Atendente.class, at.getId());
		return at;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Atendente> selectAll() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT f.id, f.nome, f.data_nascimento, f.salario, at.horario_entrada, at.horario_saida, at.email ");
		sql.append("FROM atendente at, funcionario f ");
		sql.append("WHERE at.id = f.id ");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> atendentesResultSet = query.getResultList();
		List<Atendente> atendentes = new ArrayList<Atendente>();
		for (Object[] o : atendentesResultSet) {
			Atendente at = new Atendente();
			at.setId(Integer.parseInt(o[0].toString()));
			at.setNome(o[1].toString());
			at.setDataNascimento(LocalDate.parse(o[2].toString()));
			at.setSalario(Float.parseFloat(o[3].toString()));
			at.setHorarioEntrada(Integer.parseInt(o[4].toString()));
			at.setHorarioSaida(Integer.parseInt(o[5].toString()));
			at.setEmail(o[6].toString());
			
			atendentes.add(at);
		}
		
		return atendentes;
	}

}
