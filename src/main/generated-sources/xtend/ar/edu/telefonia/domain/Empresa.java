package ar.edu.telefonia.domain;

import ar.edu.telefonia.domain.Abonado;
import ar.edu.telefonia.domain.Factura;
import ar.edu.telefonia.domain.Llamada;
import java.beans.Transient;
import java.util.List;

@SuppressWarnings("all")
public class Empresa extends Abonado {
  private String cuit;
  
  /**
   * INICIO EXTRAS MANUALES QUE NECESITA HIBERNATE
   */
  public String getCuit() {
    return this.cuit;
  }
  
  public void setCuit(final String unCuit) {
    this.cuit = unCuit;
  }
  
  /**
   * Constructor que necesita Hibernate
   */
  public Empresa() {
  }
  
  /**
   * FIN EXTRAS MANUALES QUE NECESITA HIBERNATE
   */
  @Transient
  public String getDatosEspecificos() {
    return (("Empresa (" + this.cuit) + ")");
  }
  
  public Empresa(final String unCuit) {
    this.cuit = unCuit;
  }
  
  public float costo(final Llamada llamada) {
    Integer _duracion = llamada.getDuracion();
    Integer _min = this.min(_duracion, Integer.valueOf(3));
    return (1 * (_min).intValue());
  }
  
  public boolean esMoroso() {
    List<Factura> _facturas = this.getFacturas();
    int _size = _facturas.size();
    return (_size > 3);
  }
}
