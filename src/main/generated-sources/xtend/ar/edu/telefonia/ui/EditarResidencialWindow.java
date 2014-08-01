package ar.edu.telefonia.ui;

import ar.edu.telefonia.domain.Abonado;
import ar.edu.telefonia.ui.EditarAbonadoWindow;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("all")
public class EditarResidencialWindow extends EditarAbonadoWindow {
  public EditarResidencialWindow(final WindowOwner owner, final Abonado model) {
    super(owner, model);
    this.setTitle("Crear un abonado residencial");
  }
  
  public void addFormPanel(final Panel panel) {
  }
}
