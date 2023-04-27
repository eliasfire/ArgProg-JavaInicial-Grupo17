package com.argpro.pronostico.model;

import com.argpro.pronostico.utils.ResultadoEnum;
import com.opencsv.bean.CsvBindByPosition;

public class Resultados {
	@CsvBindByPosition(position = 0)
	private Integer ronda;
	
	@CsvBindByPosition(position = 1)
	private String equipo1;
	
	@CsvBindByPosition(position = 2)
	private int golesEquipo1;
	
	@CsvBindByPosition(position = 3)
	private int golesEquipo2;
	
	@CsvBindByPosition(position = 4)
	private String equipo2;

	public Integer getRonda() {
		return ronda;
	}

	public void setRonda(Integer ronda) {
		this.ronda = ronda;
	}

	public String getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(String equipo1) {
		this.equipo1 = equipo1;
	}

	public int getGolesEquipo1() {
		return golesEquipo1;
	}

	public void setGolesEquipo1(int golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}

	public int getGolesEquipo2() {
		return golesEquipo2;
	}

	public void setGolesEquipo2(int golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}

	public String getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(String equipo2) {
		this.equipo2 = equipo2;
	}
	
public ResultadoEnum resultadoEquipo1() {
		
		if(this.golesEquipo1> this.golesEquipo2)
			return ResultadoEnum.GANA;
		
		if(this.golesEquipo1< this.golesEquipo2)
			return ResultadoEnum.PIERDE;
		
		return ResultadoEnum.EMPATA;
	}
	
}
