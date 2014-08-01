package ar.edu.telefonia.domain;

import ar.edu.telefonia.domain.Abonado;
import ar.edu.telefonia.domain.Llamada;
import java.beans.Transient;

@SuppressWarnings("all")
public class Rural extends Abonado {
  private Integer cantidadHectareas;
  
  /**
   * INICIO EXTRAS MANUALES QUE NECESITA HIBERNATE
   */
  public Integer getCantidadHectareas() {
    return this.cantidadHectareas;
  }
  
  public void setCantidadHectareas(final Integer unaCantidadHectareas) {
    this.cantidadHectareas = unaCantidadHectareas;
  }
  
  /**
   * Constructor que necesita Hibernate
   */
  public Rural() {
  }
  
  /**
   * FIN EXTRAS MANUALES QUE NECESITA HIBERNATE
   */
  public Rural(final Integer unaCantidadHectareas) {
    this.cantidadHectareas = unaCantidadHectareas;
  }
  
  public float costo(final Llamada llamada) {
    Integer _duracion = llamada.getDuracion();
    Integer _integer = new Integer(5);
    Integer _max = this.max(_duracion, _integer);
    return (3 * (_max).intValue());
  }
  
  @Transient
  public String getDatosEspecificos() {
    return (("Rural (" + this.cantidadHectareas) + " has)");
  }
}
