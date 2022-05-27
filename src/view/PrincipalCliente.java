package view;

import java.sql.SQLException;
import java.util.List;

import controller.ClienteController;
import model.Cliente;

public class PrincipalCliente {

	public static void main(String[] args) {
		ClienteController cCont = new ClienteController();

		Cliente cli = new Cliente();
		cli.setCpf("12345678900");
		cli.setNome("Fulano de Tal");
		cli.setCelular("11922222222");
		cli.setEmail("fulano@email.com");
		cli.setPronomeTratamento("Sr.");

		try {
//			salvarCliente(cCont, cli);
//			modificarCliente(cCont, cli);
//			removerCliente(cCont, cli);
			listarCliente(cCont);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void listarCliente(ClienteController cCont)  throws SQLException {
		insereMuitosCliente(cCont);
		List<Cliente> clientes = cCont.listar();
		clientes.forEach(cli -> System.out.println(cli));
		
	}

	private static void insereMuitosCliente(ClienteController cCont) throws SQLException {
		Cliente cli = new Cliente();
		cli.setCpf("12345678900");
		cli.setNome("Fulano de Tal");
		cli.setCelular("11922222222");
		cli.setEmail("fulano@email.com");
		cli.setPronomeTratamento("Sr.");
		
		Cliente cli1 = new Cliente();
		cli1.setCpf("23456789122");
		cli1.setNome("Beltrano de Tal");
		cli1.setCelular("11922223333");
		cli1.setEmail("beltrano@email.com");
		cli1.setPronomeTratamento("Sr.");
		
		Cliente cli2 = new Cliente();
		cli2.setCpf("34567891555");
		cli2.setNome("Cicrana de Tal");
		cli2.setCelular("11922224444");
		cli2.setEmail("cicrana@email.com");
		cli2.setPronomeTratamento("Sra.");
		
		cCont.salvar(cli);
		cCont.salvar(cli1);
		cCont.salvar(cli2);
		
	}

	private static void removerCliente(ClienteController cCont, Cliente cli) throws SQLException {
		Cliente cli1 = cli;
		cli1.setCelular("11922222223");

		cCont.remover(cli1);
		cli1 = cCont.consultar(cli1);
		System.out.println(cli1);
	}
	
	private static void modificarCliente(ClienteController cCont, Cliente cli) throws SQLException {
		Cliente cli1 = cli;
		cli1.setCelular("11922222223");

		cCont.modificar(cli1);
		cli1 = cCont.consultar(cli1);
		System.out.println(cli1);
	}

	private static void salvarCliente(ClienteController cCont, Cliente cli) throws SQLException {
		cCont.salvar(cli);

		Cliente cli1 = new Cliente();
		cli1.setCpf("12345678900");

		cli1 = cCont.consultar(cli1);
		System.out.println(cli1);
	}

}
