package ar.edu.telefonia.runnable;

import ar.edu.telefonia.appModel.BuscarAbonadoAppModel;
import ar.edu.telefonia.ui.AbonadoWindow;
import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

@SuppressWarnings("all")
public class AbonadoApplication extends Application {
  public static void main(final String[] args) {
    AbonadoApplication _abonadoApplication = new AbonadoApplication();
    _abonadoApplication.start();
  }
  
  @Override
  protected Window<?> createMainWindow() {
    BuscarAbonadoAppModel _buscarAbonadoAppModel = new BuscarAbonadoAppModel();
    return new AbonadoWindow(this, _buscarAbonadoAppModel);
  }
}
