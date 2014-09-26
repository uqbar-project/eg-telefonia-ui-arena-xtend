package ar.edu.telefonia.appModel;

import ar.edu.telefonia.appModel.BusquedaAbonados;
import ar.edu.telefonia.domain.Abonado;
import ar.edu.telefonia.home.HomeTelefonia;
import java.util.ArrayList;
import java.util.List;
import org.uqbar.commons.utils.Observable;

@Observable
@SuppressWarnings("all")
public class BuscarAbonadoAppModel {
  private BusquedaAbonados _busquedaAbonados;
  
  public BusquedaAbonados getBusquedaAbonados() {
    return this._busquedaAbonados;
  }
  
  public void setBusquedaAbonados(final BusquedaAbonados busquedaAbonados) {
    this._busquedaAbonados = busquedaAbonados;
  }
  
  private List<Abonado> _abonados;
  
  public List<Abonado> getAbonados() {
    return this._abonados;
  }
  
  public void setAbonados(final List<Abonado> abonados) {
    this._abonados = abonados;
  }
  
  private Abonado _abonadoSeleccionado;
  
  public Abonado getAbonadoSeleccionado() {
    return this._abonadoSeleccionado;
  }
  
  public void setAbonadoSeleccionado(final Abonado abonadoSeleccionado) {
    this._abonadoSeleccionado = abonadoSeleccionado;
  }
  
  public BuscarAbonadoAppModel() {
    BusquedaAbonados _busquedaAbonados = new BusquedaAbonados();
    this.setBusquedaAbonados(_busquedaAbonados);
    ArrayList<Abonado> _arrayList = new ArrayList<Abonado>();
    this.setAbonados(_arrayList);
  }
  
  public void buscar() {
    HomeTelefonia _instance = HomeTelefonia.getInstance();
    BusquedaAbonados _busquedaAbonados = this.getBusquedaAbonados();
    List<Abonado> _abonados = _instance.getAbonados(_busquedaAbonados);
    this.setAbonados(_abonados);
  }
  
  public void limpiar() {
    BusquedaAbonados _busquedaAbonados = this.getBusquedaAbonados();
    _busquedaAbonados.clear();
    List<Abonado> _abonados = this.getAbonados();
    _abonados.clear();
    this.setAbonadoSeleccionado(null);
  }
  
  public void eliminarAbonado() {
    HomeTelefonia _instance = HomeTelefonia.getInstance();
    Abonado _abonadoSeleccionado = this.getAbonadoSeleccionado();
    _instance.eliminarAbonado(_abonadoSeleccionado);
    this.buscar();
  }
}
