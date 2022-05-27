package controller;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;

import model.Atendente;
import persistence.AtendenteDao;
import util.HibernateUtil;

public class AtendenteController implements OperacoesController<Atendente> {

	@Override
	public void salvar(Atendente at) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		AtendenteDao atDao = new AtendenteDao(sessionFactory);
		atDao.insert(at);
	}

	@Override
	public void modificar(Atendente at) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		AtendenteDao atDao = new AtendenteDao(sessionFactory);
		atDao.update(at);
	}

	@Override
	public void remover(Atendente at) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		AtendenteDao atDao = new AtendenteDao(sessionFactory);
		atDao.delete(at);
	}

	@Override
	public Atendente consultar(Atendente at) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		AtendenteDao atDao = new AtendenteDao(sessionFactory);
		at = atDao.selectOne(at);
		return at;
	}

	@Override
	public List<Atendente> listar() throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		AtendenteDao atDao = new AtendenteDao(sessionFactory);
		List<Atendente> atendentes = atDao.selectAll();
		return atendentes;
	}
}
