package br.com.xyinc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Classe responsável por representar a entidade POI(Ponto de Interesse)
 */

@Entity
public class Poi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private Integer coordenadaX;

	private Integer coordenadaY;

	public Poi() {

	}

	public Poi(String nome, Integer coordenadaX, Integer coordenadaY) {
		super();
		this.nome = nome;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
	}

	public Poi(Integer id, String nome, Integer coordenadaX, Integer coordenadaY) {
		super();
		this.id = id;
		this.nome = nome;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCoordenadaX() {
		return coordenadaX;
	}

	public void setCoordenadaX(Integer coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public Integer getCoordenadaY() {
		return coordenadaY;
	}

	public void setCoordenadaY(Integer coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	public boolean isEmpty() {
		if (this.nome == null || this.coordenadaX == null || this.coordenadaY == null) {
			return true;
		}
		return false;
	}
	
	public boolean isValid() {
		if (this.getCoordenadaX() >= 0 && this.getCoordenadaY() >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Poi [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", coordenadaX=");
		builder.append(coordenadaX);
		builder.append(", coordenadaY=");
		builder.append(coordenadaY);
		builder.append("]");
		return builder.toString();
	}

}
