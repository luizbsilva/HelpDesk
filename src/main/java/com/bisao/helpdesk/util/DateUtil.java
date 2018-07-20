package com.bisao.helpdesk.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.bisao.helpdesk.api.enums.MonthEnum;

public class DateUtil {
	private static final int LIMITE_ANO_VALIDO = 2099;

	/**
	 * Converte String para Date
	 * 
	 * @param date
	 * @param format
	 * @return {@link Date}
	 */
	public static Date stringToDate(final String date, final String format) {
		try {
			final SimpleDateFormat df = new SimpleDateFormat(format);
			return df.parse(date);
		} catch (final ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String dateToString(final Date date, final String format) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(format).format(date);
	}

	public static String dateToString(final LocalDate date, final String format) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(format).format(converteLocalDateParaDate(date));
	}

	public static String localDateTimeToString(final LocalDateTime localDateTime, final String format) {
		if (localDateTime == null) {
			return null;
		}

		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return localDateTime.format(formatter);
	}

	public static java.sql.Date stringToSqlDate(final String date) {
		return new java.sql.Date(stringToDate(date, "dd/MM/yyyy").getTime());
	}

	public static Date dateParaFormato(final Date date, final String formato) {
		final Date data = new Date(date.getTime());
		final String strData = dateToString(data, formato);
		final Date dataConvertida = stringToDate(strData, formato);
		return dataConvertida;
	}

	public static Date converteLocalDateParaDate(final LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date converteLocalDateTimeParaDate(final LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDateTime converteDateParaLocalDateTime(final Date date) {
		if (date == null) {
			return null;
		}
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static LocalDate converteDateParaLocalDate(final Date date) {
		if (date == null) {
			return null;
		}
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static boolean isDataAtualEIgualMesEAnoLogado(final LocalDate dataAtual, final Integer mesLogado,
			final Integer anoLogado) {
		if (dataAtual.getYear() == anoLogado.intValue() && dataAtual.getMonthValue() == mesLogado) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isDataValida(final String data, final String dateFromat) {
		if (data == null) {
			return false;
		}

		final SimpleDateFormat format = new SimpleDateFormat(dateFromat);
		format.setLenient(false);

		try {
			format.parse(data);
		} catch (final ParseException e) {
			return false;
		}
		return true;
	}

	public static boolean isHorarioValido(final String horario) {
		if (horario != null) {
			try {
				LocalTime.parse(horario);
			} catch (final DateTimeParseException e) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	public static Date truncarDiaMesAno(final Date data) {
		return DateUtils.truncate(data, Calendar.DAY_OF_MONTH);
	}

	public static Date retornarUltimoDiaDoMes() {
		final Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		cl.set(Calendar.DAY_OF_MONTH, cl.getActualMaximum(Calendar.DAY_OF_MONTH));

		final Date data = cl.getTime();

		return data;
	}

	public static Date retornarUltimoDiaDoMes(final Date dt) {
		final Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		cl.set(Calendar.DAY_OF_MONTH, cl.getActualMaximum(Calendar.DAY_OF_MONTH));

		final Date data = cl.getTime();

		return data;
	}

	public static Date retornarPrimeiroDiaDoMes() {
		final Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		cl.set(Calendar.DAY_OF_MONTH, cl.getActualMinimum(Calendar.DAY_OF_MONTH));

		final Date data = cl.getTime();

		return data;
	}

	public static Date retornarPrimeiroDiaDoMes(final Date date) {
		final Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(Calendar.DAY_OF_MONTH, cl.getActualMinimum(Calendar.DAY_OF_MONTH));

		final Date data = cl.getTime();

		return data;
	}

	public static LocalDate retornarDataInicialDoMesDoAtual() {
		final LocalDate dataAtual = LocalDate.now();
		return LocalDate.of(dataAtual.getYear(), dataAtual.getMonth(), 1);
	}

	public static LocalDate retornarDataInicialDoMes(final Integer mes, final Integer ano) {
		return LocalDate.of(ano, mes, 1);
	}

	public static LocalDate retornarDataFinalDoMes(final Integer mes, final Integer ano) {
		final LocalDate dataInicial = LocalDate.of(ano, mes, 1);
		return dataInicial.withDayOfMonth(dataInicial.lengthOfMonth());
	}

	public static Date retornarDataInicialDoMesEmDate(final Integer mes, final Integer ano) {
		return DateUtil.converteLocalDateParaDate(retornarDataInicialDoMes(mes, ano));
	}

	public static Date retornarDataFinalDoMesEmDate(final Integer mes, final Integer ano) {
		return DateUtil.converteLocalDateParaDate(retornarDataFinalDoMes(mes, ano));
	}

	public static long converterLocalDateTimeParaTime(final LocalDateTime localDateTime) {
		final Date data = converteLocalDateTimeParaDate(localDateTime);
		return data.getTime();
	}

	public static LocalDateTime converterTimeParaLocalDateTime(final long time) {
		final Date data = new Date(time);
		return converteDateParaLocalDateTime(data);
	}

	public static String dataPorExtenso(final Date data) {
		final DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt", "BR"));
		return df.format(data).toString();
	}

	public static String dataComHora(final Date data) {
		final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM,
				new Locale("pt", "BR"));
		return df.format(data).toString();
	}

	public static String mesPorExtenso(final Integer mes) {
		if (Objects.nonNull(mes)) {
			return Month.of(mes).getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pt-br")).toUpperCase();
		}

		return "";
	}

	public static String dataPorExtenso(final LocalDate data) {
		final DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt", "BR"));
		return df.format(converteLocalDateParaDate(data)).toString();
	}

	public static String conveterMesParaStringAdicionandoZero(final Integer mes) {
		if (mes < 10) {
			return "0" + mes + "";
		} else {
			return mes + "";
		}
	}

	public static String conveterDiaParaStringAdicionandoZero(final Integer dia) {
		if (dia < 10) {
			return "0" + dia + "";
		} else {
			return dia + "";
		}
	}

	public static String diaEmStringComDoisCaracteres(String dia) {
		if (dia == null) {
			return null;
		}

		final Integer quantidadeDeCaracteres = 2;

		dia = dia.trim();

		if (dia.length() > quantidadeDeCaracteres) {
			dia = dia.substring(dia.length() - quantidadeDeCaracteres, dia.length());
		}

		return StringUtils.leftPad(dia, quantidadeDeCaracteres, "0");
	}

	/**
	 * Retorna 0 se as datas forem iguais. Retorna 1 se a dtMaior for a maior.
	 * Retorna -1 se a dtMaior for menor.
	 * 
	 * @param dtMaior
	 * @param dtQue
	 * @return
	 */
	public static Integer compararDatas(final Date dtMaior, final Date dtQue) {
		Integer retorno = null;

		try {
			if (dtMaior != null && dtQue != null) {
				final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

				final Calendar c1 = Calendar.getInstance();
				final Calendar c2 = Calendar.getInstance();

				c1.setTime(sdf.parse(dateToString(dtMaior, "dd/MM/yyyy HH:mm:ss")));
				c2.setTime(sdf.parse(dateToString(dtQue, "dd/MM/yyyy HH:mm:ss")));
				retorno = c1.compareTo(c2);

			}
		} catch (final ParseException e) {
			return retorno;
		}

		return retorno;
	}

	public static long calculaDiferencaDeMesesEntreDatas(final LocalDate dataInicial, final LocalDate dataFinal) {
		return dataInicial.until(dataFinal, ChronoUnit.MONTHS);
	}

	public static long calculaDiferencaDeDiasEntreDatas(final LocalDate dataInicial, final LocalDate dataFinal) {
		return dataInicial.until(dataFinal, ChronoUnit.DAYS);
	}

	public static boolean isAnoInvalido(final LocalDate data) {
		boolean dataValida = false;
		if (data != null) {
			dataValida = (data.getYear() > LIMITE_ANO_VALIDO);
		}

		return dataValida;
	}

	public static String retornaMesEAnoFormatado(final String mes, final String ano) {
		String dataMesAno = "";
		if (mes != null && ano != null) {
			dataMesAno = mes + "/" + ano;
		}

		return dataMesAno;
	}

	public static boolean validarDataMinima(final LocalDate data) {
		return data.isBefore(LocalDate.of(1900, 01, 01));
	}

	public static boolean validarDataMaxima(final LocalDate data) {
		return data.isAfter(LocalDate.of(2099, 12, 31));
	}

	public static boolean validarDataMinima(final Date data) {
		if (data == null) {
			return false;
		}

		return data.before(new GregorianCalendar(1900, 0, 1).getTime());
	}

	public static boolean validarDataMaxima(final Date data) {
		if (data == null) {
			return false;
		}

		return data.after(new GregorianCalendar(2099, 11, 31).getTime());
	}

	public static Integer retornarMesAtual() {
		final Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.MONTH);
	}

	public static Integer retornarAnoAtual() {
		final Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.YEAR);
	}

	public static boolean isDatasNoMesmoMesAno(final LocalDate data1, final LocalDate data2) {
		if (data1 != null && data2 != null) {
			return data1.getMonthValue() == data2.getMonthValue() && data1.getYear() == data2.getYear();
		}

		return false;
	}

	public static boolean isDatasNoMesmoMesAno(final Date data1, final Date data2) {
		return isDatasNoMesmoMesAno(converteDateParaLocalDate(data1), converteDateParaLocalDate(data2));
	}

	public static String retornarDiaDaSemana(final Integer ano, final Integer mes, final Integer dia) {

		final Calendar calendario = new GregorianCalendar(ano, mes - 1, dia);
		final int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

		String diaSem = null;

		switch (diaSemana) {
		case 1:
			diaSem = "Domingo";
			break;
		case 2:
			diaSem = "Segunda";
			break;
		case 3:
			diaSem = "Terça";
			break;
		case 4:
			diaSem = "Quarta";
			break;
		case 5:
			diaSem = "Quinta";
			break;
		case 6:
			diaSem = "Sexta";
			break;
		case 7:
			diaSem = "Sábado";
			break;
		default:
			break;
		}
		return diaSem;
	}

	public static String retornarDiaDaSemanaAbrev(final Integer ano, final Integer mes, final Integer dia) {

		final Calendar calendario = new GregorianCalendar(ano, mes - 1, dia);
		final int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

		String diaSem = null;

		switch (diaSemana) {
		case 1:
			diaSem = "Dom";
			break;
		case 2:
			diaSem = "Seg";
			break;
		case 3:
			diaSem = "Ter";
			break;
		case 4:
			diaSem = "Qua";
			break;
		case 5:
			diaSem = "Qui";
			break;
		case 6:
			diaSem = "Sex";
			break;
		case 7:
			diaSem = "Sáb";
			break;
		default:
			break;
		}
		return diaSem;
	}

	public static Integer retornarDiaDaSemanaPosicao(final Integer ano, final Integer mes, final Integer dia) {

		final Calendar calendario = new GregorianCalendar(ano, mes - 1, dia);
		return calendario.get(Calendar.DAY_OF_WEEK);
	}

	public static String montarAnoMesDiaDaDiferenca(final LocalDate primeiro, final LocalDate ultimo) {
		final Period tempoLiqAnoMesDia = Period.between(primeiro, ultimo);
		final String anos = StringUtils.leftPad(tempoLiqAnoMesDia.getYears() + "", 2, "0");
		final String meses = StringUtils.leftPad(tempoLiqAnoMesDia.getMonths() + "", 2, "0");
		final String dias = StringUtils.leftPad(tempoLiqAnoMesDia.getDays() + "", 2, "0");

		return anos + "/" + meses + "/" + dias;
	}

	public static void main(final String[] args) {
		System.out.print(dataComHora(new Date()));
	}

	public static String buscarQuadrimestrepeloMes(final Integer mesLogado) {
		if (mesLogado < 4) {
			return (MonthEnum.JANEIRO.getCodigo());

		} else {
			final Integer mesQuadrimestre = mesLogado - 3;
			return MonthEnum.lookup(mesQuadrimestre).getCodigo();

		}

	}

	public static String buscaBimestrePeloMes(final Integer mesLogado) {
		Integer mesBimestre = mesLogado;

		if (mesLogado.intValue() != 1) {
			mesBimestre = mesLogado - 1;
		}

		return MonthEnum.lookup(mesBimestre).getCodigo();
	}

	public static LocalDate retornaDataComProximoMes(final LocalDate dataAtual) {
		int proxMes = dataAtual.getMonthValue();
		int proxAno = dataAtual.getYear();
		final int dia = dataAtual.getDayOfMonth();

		if (proxMes == 12) {
			proxMes = 1;
			proxAno++;
		} else {
			proxMes++;
		}

		LocalDate dtTemp = retornarDataFinalDoMes(proxMes, proxAno);
		final int ultimoDiaDoMes = dtTemp.getDayOfMonth();

		if (dia > ultimoDiaDoMes) {
			proxMes++;
			return dtTemp = retornarDataInicialDoMes(proxMes, proxAno);
		} else {
			return LocalDate.of(proxAno, proxMes, dia);
		}
	}

	public static Date DtInicio(final Date date) {
		if (date != null) {
			final Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.HOUR, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			return c.getTime();
		} else {
			return null;
		}
	}

	public static Date DtFim(final Date date) {
		if (date != null) {
			final Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.HOUR, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			c.set(Calendar.MILLISECOND, 0);

			return c.getTime();
		} else {
			return null;
		}
	}

}
