package view;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import controller.EntregadorController;
import model.Entregador;

public class PrincipalEntregador {

	public static void main(String[] args) {
		EntregadorController entCont = new EntregadorController();

		Entregador ent = new Entregador();
		ent.setId(101);
		ent.setNome("João Mourinho");
		ent.setDataNascimento(LocalDate.of(1982, 9, 22));
		ent.setSalario(6000.00f);
		ent.setCnh(123456789);
		ent.setCategoriaCnh("D");
		
		
		try {
//			salvarEntregador(entCont, ent);
//			modificarEntregador(entCont, ent);
//			removerEntregador(entCont, ent);
			listarEntregador(entCont);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void salvarEntregador(EntregadorController entCont, Entregador ent) throws SQLException {
		entCont.salvar(ent);

		Entregador ent1 = new Entregador();
		ent1.setId(101);

		ent1 = entCont.consultar(ent1);
		System.out.println(ent1);
	}

	private static void listarEntregador(EntregadorController entCont)  throws SQLException {
		insereMuitosEntregadors(entCont);
		List<Entregador> Entregadors = entCont.listar();
		Entregadors.forEach(ent -> System.out.println(ent));
		
	}

	private static void insereMuitosEntregadors(EntregadorController entCont) throws SQLException {
		Entregador ent = new Entregador();
		ent.setId(101);
		ent.setNome("João Mourinho");
		ent.setDataNascimento(LocalDate.of(1982, 9, 22));
		ent.setSalario(6000.00f);
		ent.setCnh(123456789);
		ent.setCategoriaCnh("D");
		
		Entregador ent1 = new Entregador();
		ent1.setId(102);
		ent1.setNome("Alex Ferguson");
		ent1.setDataNascimento(LocalDate.of(1980, 6, 14));
		ent1.setSalario(5000.00f);
		ent1.setCnh(123456780);
		ent1.setCategoriaCnh("E");
		
		Entregador ent2 = new Entregador();
		ent2.setId(103);
		ent2.setNome("Steven Gerrard");
		ent2.setDataNascimento(LocalDate.of(1984, 7, 19));
		ent2.setSalario(6500.00f);
		ent2.setCnh(123456711);
		ent2.setCategoriaCnh("C");
		
		entCont.salvar(ent);
		entCont.salvar(ent1);
		entCont.salvar(ent2);
		
	}

	private static void removerEntregador(EntregadorController entCont, Entregador ent) throws SQLException {
		Entregador ent1 = ent;

		entCont.remover(ent1);
		ent1 = entCont.consultar(ent1);
		System.out.println(ent1);
	}
	
	private static void modificarEntregador(EntregadorController entCont, Entregador ent) throws SQLException {
		Entregador ent1 = ent;
		ent1.setCnh(123456789);
		ent1.setCategoriaCnh("E");

		entCont.modificar(ent1);
		ent1 = entCont.consultar(ent1);
		System.out.println(ent1);
	}
}
