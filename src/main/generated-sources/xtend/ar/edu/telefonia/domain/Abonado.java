package ar.edu.telefonia.domain;

import ar.edu.telefonia.domain.Factura;
import ar.edu.telefonia.domain.Llamada;
import com.google.common.base.Objects;
import java.beans.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

@Observable
@SuppressWarnings("all")
public abstract class Abonado implements Cloneable {
  private Long _id;
  
  public Long getId() {
    return this._id;
  }
  
  public void setId(final Long id) {
    this._id = id;
  }
  
  private String _nombre;
  
  public String getNombre() {
    return this._nombre;
  }
  
  public void setNombre(final String nombre) {
    this._nombre = nombre;
  }
  
  private String _numero;
  
  public String getNumero() {
    return this._numero;
  }
  
  public void setNumero(final String numero) {
    this._numero = numero;
  }
  
  private List<Factura> _facturas;
  
  public List<Factura> getFacturas() {
    return this._facturas;
  }
  
  public void setFacturas(final List<Factura> facturas) {
    this._facturas = facturas;
  }
  
  private List<Llamada> _llamadas;
  
  public List<Llamada> getLlamadas() {
    return this._llamadas;
  }
  
  public void setLlamadas(final List<Llamada> llamadas) {
    this._llamadas = llamadas;
  }
  
  public Abonado() {
    ArrayList<Factura> _arrayList = new ArrayList<Factura>();
    this.setFacturas(_arrayList);
    ArrayList<Llamada> _arrayList_1 = new ArrayList<Llamada>();
    this.setLlamadas(_arrayList_1);
  }
  
  public Abonado copy() {
    try {
      Object _clone = super.clone();
      return ((Abonado) _clone);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public abstract float costo(final Llamada llamada);
  
  public boolean esMoroso() {
    Double _deuda = this.deuda();
    return ((_deuda).doubleValue() > 0);
  }
  
  public Double deuda() {
    List<Factura> _facturas = this.getFacturas();
    final Function2<Double, Factura, Double> _function = new Function2<Double, Factura, Double>() {
      public Double apply(final Double acum, final Factura factura) {
        BigDecimal _saldo = factura.saldo();
        float _floatValue = _saldo.floatValue();
        return Double.valueOf(((acum).doubleValue() + _floatValue));
      }
    };
    return IterableExtensions.<Factura, Double>fold(_facturas, Double.valueOf(0.0), _function);
  }
  
  public boolean agregarLlamada(final Llamada llamada) {
    List<Llamada> _llamadas = this.getLlamadas();
    return _llamadas.add(llamada);
  }
  
  public boolean agregarFactura(final Factura factura) {
    List<Factura> _facturas = this.getFacturas();
    return _facturas.add(factura);
  }
  
  @Transient
  public abstract String getDatosEspecificos();
  
  public void validar() {
    String _numero = this.getNumero();
    boolean _equals = Objects.equal(_numero, null);
    if (_equals) {
      throw new UserException("Debe ingresar número");
    }
    String _nombre = this.getNombre();
    boolean _equals_1 = Objects.equal(_nombre, null);
    if (_equals_1) {
      throw new UserException("Debe ingresar nombre");
    }
  }
  
  /**
   * EXTENSION METHODS
   */
  public Integer max(final Integer integer, final Integer integer2) {
    Integer _xifexpression = null;
    boolean _greaterThan = (integer.compareTo(integer2) > 0);
    if (_greaterThan) {
      _xifexpression = integer;
    } else {
      _xifexpression = integer2;
    }
    return _xifexpression;
  }
  
  public Integer min(final Integer integer, final Integer integer2) {
    return this.max(integer2, integer);
  }
}
