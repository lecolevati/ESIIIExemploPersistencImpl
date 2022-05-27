package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Atendimento;

public interface IAtendimentoDao {

	public Atendimento selectOne(Atendimento atend) throws SQLException;
	public List<Atendimento> selectOneCliente(Atendimento atend) throws SQLException;
	public List<Atendimento> selectOneAtendente(Atendimento atend) throws SQLException;
	public List<Atendimento> selectAll() throws SQLException;
}
