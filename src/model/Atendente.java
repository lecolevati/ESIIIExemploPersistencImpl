package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "atendente")
public class Atendente extends Funcionario {
	
	@Column(name = "horario_entrada")
	@NotNull
	private int horarioEntrada;

	@Column(name = "horario_saida")
	@NotNull
	private int horarioSaida;

	@Column(name = "email", length = 40)
	@NotNull
	private String email;
	
	public int getHorarioEntrada() {
		return horarioEntrada;
	}
	public void setHorarioEntrada(int horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}
	public int getHorarioSaida() {
		return horarioSaida;
	}
	public void setHorarioSaida(int horarioSaida) {
		this.horarioSaida = horarioSaida;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Atendente [horarioEntrada=" + horarioEntrada + ", horarioSaida=" + horarioSaida + ", email=" + email
				+ ", " + super.toString() + "]";
	}


}
