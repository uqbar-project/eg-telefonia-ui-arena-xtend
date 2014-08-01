package ar.edu.telefonia.domain;

import ar.edu.telefonia.domain.Abonado;
import ar.edu.telefonia.domain.Llamada;
import java.beans.Transient;

@SuppressWarnings("all")
public class Residencial extends Abonado {
  public float costo(final Llamada llamada) {
    Integer _duracion = llamada.getDuracion();
    return (2 * (_duracion).intValue());
  }
  
  @Transient
  public String getDatosEspecificos() {
    return "Residencial";
  }
}
