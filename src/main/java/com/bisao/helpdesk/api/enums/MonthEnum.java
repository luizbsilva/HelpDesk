package com.bisao.helpdesk.api.enums;

import java.util.EnumSet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum MonthEnum {

	JANEIRO("01", "Janeiro"), FEVEREIRO("02", "Fevereiro"), MARCO("03", "Março"), ABRIL("04", "Abril"),
	MAIO("05", "Maio"), JUNHO("06", "Junho"), JULHO("07", "Julho"), AGOSTO("08", "Agosto"), SETEMBRO("09", "Setembro"),
	OUTUBRO("10", "Outubro"), NOVEMBRO("11", "Novembro"), DEZEMBRO("12", "Dezembro");

	private String codigo;

	private String descricao;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(final String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	MonthEnum(final String codigo, final String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static MonthEnum lookup(final String codigo) {
		if (codigo != null) {
			try {
				final Integer valor = Integer.valueOf(codigo.trim());
				return lookup(valor);
			} catch (final NumberFormatException e) {
				return null;
			}
		}

		return null;
	}

	public static MonthEnum lookup(final int codigo) {
		for (final MonthEnum mes : EnumSet.allOf(MonthEnum.class)) {
			if (Integer.valueOf(mes.codigo).equals(codigo)) {
				return mes;
			}
		}

		return null;
	}

}
