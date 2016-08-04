package ar.edu.telefonia.ui;

import ar.edu.telefonia.domain.Abonado;
import ar.edu.telefonia.ui.EditarAbonadoWindow;
import org.uqbar.arena.bindings.ObservableValue;
import org.uqbar.arena.widgets.Control;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.xtend.ArenaXtendExtensions;
import org.uqbar.lacar.ui.model.ControlBuilder;

@SuppressWarnings("all")
public class EditarRuralWindow extends EditarAbonadoWindow {
  public EditarRuralWindow(final WindowOwner owner, final Abonado model) {
    super(owner, model);
    this.setTitle("Crear un abonado rural");
  }
  
  @Override
  public void addFormPanel(final Panel panel) {
    Label _label = new Label(panel);
    _label.setText("Cantidad de hectáreas");
    NumericField _numericField = new NumericField(panel);
    ObservableValue<Control, ControlBuilder> _value = _numericField.<ControlBuilder>value();
    ArenaXtendExtensions.operator_spaceship(_value, "cantidadHectareas");
  }
}
