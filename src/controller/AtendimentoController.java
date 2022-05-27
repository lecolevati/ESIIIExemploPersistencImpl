package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import model.Atendimento;
import persistence.AtendimentoDao;
import util.HibernateUtil;

public class AtendimentoController implements OperacoesAtendimento<Atendimento> {

	@Override
	public void salvar(Atendimento atend) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		AtendimentoDao atendDao = new AtendimentoDao(sessionFactory);
		atendDao.insert(atend);
	}

	@Override
	public void modificar(Atendimento atend) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		AtendimentoDao atendDao = new AtendimentoDao(sessionFactory);
		atend = atendDao.selectOne(atend);
		atendDao.update(atend);
	}

	@Override
	public void remover(Atendimento atend) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		AtendimentoDao atendDao = new AtendimentoDao(sessionFactory);
		atendDao.delete(atend);
	}

	@Override
	public List<Atendimento> listar() throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		AtendimentoDao atendDao = new AtendimentoDao(sessionFactory);
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		atendimentos = atendDao.selectAll();
		return atendimentos;
	}

	@Override
	public List<Atendimento> consultar(Atendimento atend) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		AtendimentoDao atendDao = new AtendimentoDao(sessionFactory);
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		if (atend.getAtendente().getId() > 0) {
			atendimentos = atendDao.selectOneAtendente(atend);
		} else {
			if (atend.getCliente().getCpf() != null) {
				atendimentos = atendDao.selectOneCliente(atend);
			}
		}
		return atendimentos;
	}

}
