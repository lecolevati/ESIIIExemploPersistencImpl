package persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Cliente;

public class ClienteDao implements Dao<Cliente> {

	private SessionFactory sf;
	
	public ClienteDao(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void insert(Cliente cli) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(cli);
		transaction.commit();		
	}

	@Override
	public void update(Cliente cli) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(cli);
		transaction.commit();			
	}

	@Override
	public void delete(Cliente cli) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(cli);
		transaction.commit();			
	}

	@Override
	public Cliente selectOne(Cliente cli) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		cli = entityManager.find(Cliente.class, cli.getCpf());
		return cli;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> selectAll() throws SQLException {
		String sql = "SELECT cpf, nome, celular, email, pronome_tratamento FROM cliente";
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> clientesResultSet = query.getResultList();
		List<Cliente> clientes = new ArrayList<Cliente>();
		for (Object[] o : clientesResultSet) {
			Cliente cli = new Cliente();
			cli.setCpf(o[0].toString());
			cli.setNome(o[1].toString());
			cli.setCelular(o[2].toString());
			cli.setEmail(o[3].toString());
			cli.setPronomeTratamento(o[4].toString());
			
			clientes.add(cli);
		}
		
		return clientes;
	}

}
