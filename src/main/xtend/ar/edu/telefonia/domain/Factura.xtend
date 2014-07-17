package ar.edu.telefonia.domain

import java.math.BigDecimal
import java.util.Date
import org.uqbar.commons.utils.Observable

@Observable
class Factura {
	// los getters y setters que se generan con @Property hay 
	// que sobreescribirlos, por eso faltan las annotations
  	private Long id
	private Date fecha
	private BigDecimal totalPagado
	private BigDecimal total

	/**
	 * ***********************************************************
	 *      INICIO EXTRAS MANUALES QUE NECESITA HIBERNATE        *
	 *************************************************************
	 */
	def getId() { 
		id
	}
	
	def getFecha() { 
		fecha
	}
	
	def getTotalPagado() { 
		totalPagado
	}
	
	def getTotal() { 
		total
	}
	
	def void setId(Long unId) {
		id = unId
	} 
	

	def setFecha(Date unaFecha) { 
		fecha = unaFecha
	}
	
	def setTotalPagado(BigDecimal unTotalPagado) { 
		totalPagado = unTotalPagado
	}
	
	def setTotal(BigDecimal unTotal) { 
		total = unTotal
	}

	/** Constructor que necesita Hibernate */	
	new() {
		
	}
	
	/**
	 * ***********************************************************
	 *        FIN EXTRAS MANUALES QUE NECESITA HIBERNATE         *
	 *************************************************************
	 */
	
	new(Date unaFecha, int elTotalPagado, int elTotal) {
	  fecha = unaFecha
	  totalPagado = new BigDecimal(elTotalPagado)
	  total = new BigDecimal(elTotal)
	}

	def saldo() { 
		totalPagado.subtract(total)
	}
	
}