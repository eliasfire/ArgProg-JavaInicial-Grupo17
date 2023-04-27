package com.argpro.pronostico.model;

public class Participante {
	private String nombre;
	private int puntos;

	public Participante(String nombre, int puntos) {
		this.nombre = nombre;
		this.puntos = puntos;
	}

	public String get_nombre() {
		return nombre;
	}

	public void set_nombre(String nombre) {
		this.nombre = nombre;
	}

	public int get_puntos() {
		return puntos;
	}

	public void set_puntos(int puntos) {
		this.puntos = puntos;
	}

}