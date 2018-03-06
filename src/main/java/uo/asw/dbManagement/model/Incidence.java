package uo.asw.dbManagement.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Incidence {
	
	/*
	 *	Cada incidencia puede contener los siguientes campos: nombre de usuario y password, 
	 *	nombre de la incidencia, descripción, localización (se obtendrá automáticamente del dispositivo si es posible), 
	 *	etiquetas (lista de palabras separadas por comas que permitirán categorizar las incidencias), 
	 *	información adicional (fotos, vídeos, etc.). 
	 *	Algunas incidencias podrán también contener una lista de campos con la forma "propiedad/valor", 
	 *	donde el campo propiedad indica un nombre de propiedad, y el campo valor, indica el valor de dicha propiedad.
	 *
	 *	Las incidencias adquirirán un estado (abierta, en proceso, cerrada, anulada) 
	 *	así como otra información generada por el sistema (persona/entidad asignada para resolver la incidencia), 
	 *	comentarios sobre la incidencia realizados por los operarios, etc. 
	 *	Las incidencias también pueden tener una caducidad, por ejemplo, en el caso de las incidencias 
	 *	que pueda enviar un sensor de temperatura, si se envían cada hora, tendrán una hora de caducidad).
	 *
	 * 	Si hay valores de alguna propiedad que los filtros consideran peligroso, se marcará dicha incidencia como peligrosa.
	 */
	

	
	//TODO - Completar
	
	
	@ManyToOne
	@JoinColumn(name="operator_id")
	private Operator operator;
	
}
