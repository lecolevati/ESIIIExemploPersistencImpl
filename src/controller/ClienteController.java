package controller;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;

import model.Cliente;
import persistence.ClienteDao;
import util.HibernateUtil;

public class ClienteController implements OperacoesController<Cliente> {

	@Override
	public void salvar(Cliente cli) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ClienteDao cDao = new ClienteDao(sessionFactory);
		cDao.insert(cli);
	}

	@Override
	public void modificar(Cliente cli) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ClienteDao cDao = new ClienteDao(sessionFactory);
		cDao.update(cli);
	}

	@Override
	public void remover(Cliente cli) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ClienteDao cDao = new ClienteDao(sessionFactory);
		cDao.delete(cli);
	}

	@Override
	public Cliente consultar(Cliente cli) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ClienteDao cDao = new ClienteDao(sessionFactory);
		cli = cDao.selectOne(cli);
		return cli;
	}

	@Override
	public List<Cliente> listar() throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ClienteDao cDao = new ClienteDao(sessionFactory);
		List<Cliente> clientes = cDao.selectAll();
		return clientes;
	}

}
