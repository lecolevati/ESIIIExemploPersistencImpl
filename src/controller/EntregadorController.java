package controller;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;

import model.Entregador;
import persistence.EntregadorDao;
import util.HibernateUtil;

public class EntregadorController implements OperacoesController<Entregador> {

	@Override
	public void salvar(Entregador ent) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		EntregadorDao entDao = new EntregadorDao(sessionFactory);
		entDao.insert(ent);
	}

	@Override
	public void modificar(Entregador ent) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		EntregadorDao entDao = new EntregadorDao(sessionFactory);
		entDao.update(ent);
	}

	@Override
	public void remover(Entregador ent) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		EntregadorDao entDao = new EntregadorDao(sessionFactory);
		entDao.delete(ent);
	}

	@Override
	public Entregador consultar(Entregador ent) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		EntregadorDao entDao = new EntregadorDao(sessionFactory);
		ent = entDao.selectOne(ent);
		return ent;
	}

	@Override
	public List<Entregador> listar() throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		EntregadorDao entDao = new EntregadorDao(sessionFactory);
		List<Entregador> entregadores = entDao.selectAll();
		return entregadores;
	}
}
