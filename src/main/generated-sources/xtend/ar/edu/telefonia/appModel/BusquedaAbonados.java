package ar.edu.telefonia.appModel;

import ar.edu.telefonia.domain.Abonado;
import com.google.common.base.Objects;
import org.uqbar.commons.utils.Observable;

@Observable
@SuppressWarnings("all")
public class BusquedaAbonados {
  private String _nombreDesde;
  
  public String getNombreDesde() {
    return this._nombreDesde;
  }
  
  public void setNombreDesde(final String nombreDesde) {
    this._nombreDesde = nombreDesde;
  }
  
  private String _nombreHasta;
  
  public String getNombreHasta() {
    return this._nombreHasta;
  }
  
  public void setNombreHasta(final String nombreHasta) {
    this._nombreHasta = nombreHasta;
  }
  
  private boolean _soloMorosos;
  
  public boolean isSoloMorosos() {
    return this._soloMorosos;
  }
  
  public void setSoloMorosos(final boolean soloMorosos) {
    this._soloMorosos = soloMorosos;
  }
  
  public BusquedaAbonados() {
    this.clear();
  }
  
  public boolean cumple(final Abonado abonado) {
    boolean _and = false;
    boolean _and_1 = false;
    boolean _or = false;
    boolean _ingresoNombreDesde = this.ingresoNombreDesde();
    boolean _not = (!_ingresoNombreDesde);
    if (_not) {
      _or = true;
    } else {
      String _nombre = abonado.getNombre();
      String _upperCase = _nombre.toUpperCase();
      String _nombreDesde = this.getNombreDesde();
      String _upperCase_1 = _nombreDesde.toUpperCase();
      boolean _greaterEqualsThan = (_upperCase.compareTo(_upperCase_1) >= 0);
      _or = _greaterEqualsThan;
    }
    if (!_or) {
      _and_1 = false;
    } else {
      boolean _or_1 = false;
      boolean _ingresoNombreHasta = this.ingresoNombreHasta();
      boolean _not_1 = (!_ingresoNombreHasta);
      if (_not_1) {
        _or_1 = true;
      } else {
        String _nombre_1 = abonado.getNombre();
        String _upperCase_2 = _nombre_1.toUpperCase();
        String _nombreHasta = this.getNombreHasta();
        String _upperCase_3 = _nombreHasta.toUpperCase();
        boolean _lessEqualsThan = (_upperCase_2.compareTo(_upperCase_3) <= 0);
        _or_1 = _lessEqualsThan;
      }
      _and_1 = _or_1;
    }
    if (!_and_1) {
      _and = false;
    } else {
      boolean _or_2 = false;
      boolean _isSoloMorosos = this.isSoloMorosos();
      boolean _not_2 = (!_isSoloMorosos);
      if (_not_2) {
        _or_2 = true;
      } else {
        boolean _esMoroso = abonado.esMoroso();
        _or_2 = _esMoroso;
      }
      _and = _or_2;
    }
    return _and;
  }
  
  public void clear() {
    this.setNombreDesde("");
    this.setNombreHasta("");
    this.setSoloMorosos(false);
  }
  
  public boolean ingresoNombreDesde() {
    boolean _and = false;
    String _nombreDesde = this.getNombreDesde();
    boolean _notEquals = (!Objects.equal(_nombreDesde, null));
    if (!_notEquals) {
      _and = false;
    } else {
      String _nombreDesde_1 = this.getNombreDesde();
      boolean _equals = _nombreDesde_1.equals("");
      boolean _not = (!_equals);
      _and = _not;
    }
    return _and;
  }
  
  public boolean ingresoNombreHasta() {
    boolean _and = false;
    String _nombreHasta = this.getNombreHasta();
    boolean _notEquals = (!Objects.equal(_nombreHasta, null));
    if (!_notEquals) {
      _and = false;
    } else {
      String _nombreHasta_1 = this.getNombreHasta();
      boolean _equals = _nombreHasta_1.equals("");
      boolean _not = (!_equals);
      _and = _not;
    }
    return _and;
  }
}
