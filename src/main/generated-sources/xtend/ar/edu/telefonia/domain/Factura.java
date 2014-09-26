package ar.edu.telefonia.domain;

import java.math.BigDecimal;
import java.util.Date;
import org.uqbar.commons.utils.Observable;

@Observable
@SuppressWarnings("all")
public class Factura {
  private Long id;
  
  private Date fecha;
  
  private BigDecimal totalPagado;
  
  private BigDecimal total;
  
  /**
   * INICIO EXTRAS MANUALES QUE NECESITA HIBERNATE
   */
  public Long getId() {
    return this.id;
  }
  
  public Date getFecha() {
    return this.fecha;
  }
  
  public BigDecimal getTotalPagado() {
    return this.totalPagado;
  }
  
  public BigDecimal getTotal() {
    return this.total;
  }
  
  public void setId(final Long unId) {
    this.id = unId;
  }
  
  public Date setFecha(final Date unaFecha) {
    return this.fecha = unaFecha;
  }
  
  public BigDecimal setTotalPagado(final BigDecimal unTotalPagado) {
    return this.totalPagado = unTotalPagado;
  }
  
  public BigDecimal setTotal(final BigDecimal unTotal) {
    return this.total = unTotal;
  }
  
  /**
   * Constructor que necesita Hibernate
   */
  public Factura() {
  }
  
  /**
   * FIN EXTRAS MANUALES QUE NECESITA HIBERNATE
   */
  public Factura(final Date unaFecha, final int elTotalPagado, final int elTotal) {
    this.fecha = unaFecha;
    BigDecimal _bigDecimal = new BigDecimal(elTotalPagado);
    this.totalPagado = _bigDecimal;
    BigDecimal _bigDecimal_1 = new BigDecimal(elTotal);
    this.total = _bigDecimal_1;
  }
  
  public BigDecimal saldo() {
    return this.totalPagado.subtract(this.total);
  }
}
