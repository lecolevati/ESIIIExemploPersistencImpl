package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "entregador")
public class Entregador extends Funcionario {
	
	@Column(name = "cnh")
	@NotNull
	private long cnh;

	@Column(name = "categoria_cnh", length = 1)
	@NotNull
	private String categoriaCnh;
	
	public long getCnh() {
		return cnh;
	}
	public void setCnh(long cnh) {
		this.cnh = cnh;
	}
	public String getCategoriaCnh() {
		return categoriaCnh;
	}
	public void setCategoriaCnh(String categoriaCnh) {
		this.categoriaCnh = categoriaCnh;
	}

	@Override
	public String toString() {
		return "Entregador [cnh=" + cnh + ", categoriaCnh=" + categoriaCnh + "]" + ", " + super.toString() + "]";
	}
	
}
