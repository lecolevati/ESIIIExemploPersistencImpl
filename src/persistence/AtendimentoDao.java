package persistence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Atendente;
import model.Atendimento;
import model.Cliente;

public class AtendimentoDao implements IIUDDao<Atendimento>, IAtendimentoDao {

	private SessionFactory sf;

	public AtendimentoDao(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void insert(Atendimento atend) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(atend);
		transaction.commit();
	}

	@Override
	public void update(Atendimento atend) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(atend);
		transaction.commit();
	}

	@Override
	public void delete(Atendimento atend) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(atend);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Atendimento> selectOneCliente(Atendimento atend) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT cli.cpf, cli.nome, cli.email, cli.celular, cli.pronome_tratamento, ");
		sql.append("f.id, f.nome, f.data_nascimento, f.salario, at.horario_entrada, at.horario_saida, at.email, ");
		sql.append("atend.data_hora_atendimento ");
		sql.append("FROM cliente cli, atendimento atend, atendente at, funcionario f ");
		sql.append("WHERE cli.cpf  = atend.cpf_cliente ");
		sql.append("AND at.id = f.id ");
		sql.append("AND at.id = atend.id_atendente ");
		sql.append("AND cli.cpf = ?1 ");

		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, atend.getCliente().getCpf());

		List<Object[]> atendimentosResultSet = query.getResultList();
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		for (Object[] o : atendimentosResultSet) {
			Cliente cli = new Cliente();
			cli.setCpf(o[0].toString());
			cli.setNome(o[1].toString());
			cli.setEmail(o[2].toString());
			cli.setCelular(o[3].toString());
			cli.setPronomeTratamento(o[4].toString());

			Atendente at = new Atendente();
			at.setId(Integer.parseInt(o[5].toString()));
			at.setNome(o[6].toString());
			at.setDataNascimento(LocalDate.parse(o[7].toString()));
			at.setSalario(Float.parseFloat(o[8].toString()));
			at.setHorarioEntrada(Integer.parseInt(o[9].toString()));
			at.setHorarioSaida(Integer.parseInt(o[10].toString()));
			at.setEmail(o[11].toString());

			LocalDateTime dataHoraAtend = LocalDateTime.parse(o[12].toString(), formatter);
			atend = new Atendimento();
			atend.setDataHoraAtendimento(dataHoraAtend);
			
			atendimentos.add(atend);
		}
		return atendimentos;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Atendimento> selectAll() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT cli.cpf, cli.nome, cli.email, cli.celular, cli.pronome_tratamento, ");
		sql.append("f.id, f.nome, f.data_nascimento, f.salario, at.horario_entrada, at.horario_saida, at.email, ");
		sql.append("atend.data_hora_atendimento ");
		sql.append("FROM cliente cli, atendimento atend, atendente at, funcionario f ");
		sql.append("WHERE cli.cpf  = atend.cpf_cliente ");
		sql.append("AND at.id = f.id ");
		sql.append("AND at.id = atend.id_atendente ");

		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());

		List<Object[]> atendimentosResultSet = query.getResultList();
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		for (Object[] o : atendimentosResultSet) {
			Cliente cli = new Cliente();
			cli.setCpf(o[0].toString());
			cli.setNome(o[1].toString());
			cli.setEmail(o[2].toString());
			cli.setCelular(o[3].toString());
			cli.setPronomeTratamento(o[4].toString());

			Atendente at = new Atendente();
			at.setId(Integer.parseInt(o[5].toString()));
			at.setNome(o[6].toString());
			at.setDataNascimento(LocalDate.parse(o[7].toString()));
			at.setSalario(Float.parseFloat(o[8].toString()));
			at.setHorarioEntrada(Integer.parseInt(o[9].toString()));
			at.setHorarioSaida(Integer.parseInt(o[10].toString()));
			at.setEmail(o[11].toString());

			LocalDateTime dataHoraAtend = LocalDateTime.parse(o[12].toString(), formatter);
			Atendimento atend = new Atendimento();
			atend.setDataHoraAtendimento(dataHoraAtend);
			
			atendimentos.add(atend);
		}
		return atendimentos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Atendimento> selectOneAtendente(Atendimento atend) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT cli.cpf, cli.nome, cli.email, cli.celular, cli.pronome_tratamento, ");
		sql.append("f.id, f.nome, f.data_nascimento, f.salario, at.horario_entrada, at.horario_saida, at.email, ");
		sql.append("atend.data_hora_atendimento ");
		sql.append("FROM cliente cli, atendimento atend, atendente at, funcionario f ");
		sql.append("WHERE cli.cpf  = atend.cpf_cliente ");
		sql.append("AND at.id = f.id ");
		sql.append("AND at.id = atend.id_atendente ");
		sql.append("AND at.id = ?1 ");

		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, atend.getAtendente().getId());

		List<Object[]> atendimentosResultSet = query.getResultList();
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		for (Object[] o : atendimentosResultSet) {
			Cliente cli = new Cliente();
			cli.setCpf(o[0].toString());
			cli.setNome(o[1].toString());
			cli.setEmail(o[2].toString());
			cli.setCelular(o[3].toString());
			cli.setPronomeTratamento(o[4].toString());

			Atendente at = new Atendente();
			at.setId(Integer.parseInt(o[5].toString()));
			at.setNome(o[6].toString());
			at.setDataNascimento(LocalDate.parse(o[7].toString()));
			at.setSalario(Float.parseFloat(o[8].toString()));
			at.setHorarioEntrada(Integer.parseInt(o[9].toString()));
			at.setHorarioSaida(Integer.parseInt(o[10].toString()));
			at.setEmail(o[11].toString());

			LocalDateTime dataHoraAtend = LocalDateTime.parse(o[12].toString(), formatter);
			atend = new Atendimento();
			atend.setDataHoraAtendimento(dataHoraAtend);
			
			atendimentos.add(atend);
		}
		return atendimentos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Atendimento selectOne(Atendimento atend) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT cli.cpf, cli.nome, cli.email, cli.celular, cli.pronome_tratamento, ");
		sql.append("f.id, f.nome, f.data_nascimento, f.salario, at.horario_entrada, at.horario_saida, at.email, ");
		sql.append("atend.data_hora_atendimento ");
		sql.append("FROM cliente cli, atendimento atend, atendente at, funcionario f ");
		sql.append("WHERE cli.cpf  = atend.cpf_cliente ");
		sql.append("AND at.id = f.id ");
		sql.append("AND at.id = atend.id_atendente ");
		sql.append("AND at.id = ?1 ");
		sql.append("AND cli.cpf = ?2 ");
		sql.append("AND atend.data_hora_atendimento = ?3 ");

		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, atend.getAtendente().getId());
		query.setParameter(2, atend.getCliente().getCpf());
		query.setParameter(3, atend.getDataHoraAtendimento());

		List<Object[]> atendimentosResultSet = query.getResultList();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		for (Object[] o : atendimentosResultSet) {
			Cliente cli = new Cliente();
			cli.setCpf(o[0].toString());
			cli.setNome(o[1].toString());
			cli.setEmail(o[2].toString());
			cli.setCelular(o[3].toString());
			cli.setPronomeTratamento(o[4].toString());

			Atendente at = new Atendente();
			at.setId(Integer.parseInt(o[5].toString()));
			at.setNome(o[6].toString());
			at.setDataNascimento(LocalDate.parse(o[7].toString()));
			at.setSalario(Float.parseFloat(o[8].toString()));
			at.setHorarioEntrada(Integer.parseInt(o[9].toString()));
			at.setHorarioSaida(Integer.parseInt(o[10].toString()));
			at.setEmail(o[11].toString());

			LocalDateTime dataHoraAtend = LocalDateTime.parse(o[12].toString(), formatter);
			atend = new Atendimento();
			atend.setDataHoraAtendimento(dataHoraAtend);
			
		}
		return atend;
	}

}
