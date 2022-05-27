package view;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import controller.AtendimentoController;
import model.Atendente;
import model.Atendimento;
import model.Cliente;

public class PrincipalAtendimento {

	public static void main(String[] args) {
		AtendimentoController atendCont = new AtendimentoController();

		Atendente at = new Atendente();
		at.setId(1);
		
		Cliente cli = new Cliente();
		cli.setCpf("12345678900");

		LocalDateTime dataAtend = LocalDateTime.now();
		
		Atendimento atend = new Atendimento();
		atend.setAtendente(at);
		atend.setCliente(cli);
		atend.setDataHoraAtendimento(dataAtend);
		
		try {
//			salvarAtendimento(atendCont, atend);
			modificarAtendimento(atendCont, atend);
//			removerAtendimento(atendCont, atend);
			consultarAtendimento(atendCont, atend);
//			listarAtendimento(atendCont);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void salvarAtendimento(AtendimentoController atendCont, Atendimento atend) throws SQLException {
		atendCont.salvar(atend);
	}

	private static void listarAtendimento(AtendimentoController atendCont)  throws SQLException {
		List<Atendimento> atendimentos = atendCont.listar();
		atendimentos.forEach(atend -> System.out.println(atend));
	}
	
	private static void consultarAtendimento(AtendimentoController atendCont, Atendimento atend)  throws SQLException {
		List<Atendimento> atendimentos = atendCont.consultar(atend);
		atendimentos.forEach(a -> System.out.println(a));
	}

	private static void insereMuitosAtendimentos(AtendimentoController atendCont) throws SQLException {
		
//		atendCont.salvar(at);
//		atendCont.salvar(at1);
//		atendCont.salvar(at2);
		
	}

	private static void removerAtendimento(AtendimentoController atendCont, Atendimento atend) throws SQLException {
		Atendimento atend1 = atend;
		atendCont.remover(atend1);

		
	}
	
	private static void modificarAtendimento(AtendimentoController atendCont, Atendimento atend) throws SQLException {
		LocalDateTime dataAtend = LocalDateTime.now();
		
		Atendimento atend1 = atend;
		atend1.setDataHoraAtendimento(dataAtend);

		atendCont.modificar(atend1);
//		atend1 = atendCont.consultar(atend1);
//		System.out.println(atend1);
	}
}
