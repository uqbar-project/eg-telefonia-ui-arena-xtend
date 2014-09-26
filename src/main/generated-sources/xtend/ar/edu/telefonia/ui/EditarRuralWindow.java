package ar.edu.telefonia.ui;

import ar.edu.telefonia.domain.Abonado;
import ar.edu.telefonia.ui.EditarAbonadoWindow;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ControlBuilder;

@SuppressWarnings("all")
public class EditarRuralWindow extends EditarAbonadoWindow {
  public EditarRuralWindow(final WindowOwner owner, final Abonado model) {
    super(owner, model);
    this.setTitle("Crear un abonado rural");
  }
  
  public void addFormPanel(final Panel panel) {
    Label _label = new Label(panel);
    _label.setText("Cantidad de hectáreas");
    TextBox _textBox = new TextBox(panel);
    _textBox.<Object, ControlBuilder>bindValueToProperty("cantidadHectareas");
  }
}
