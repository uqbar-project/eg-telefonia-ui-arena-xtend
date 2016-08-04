package ar.edu.telefonia.ui;

import ar.edu.telefonia.domain.Abonado;
import ar.edu.telefonia.repo.RepoTelefonia;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.bindings.ObservableValue;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Control;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.xtend.ArenaXtendExtensions;
import org.uqbar.lacar.ui.model.Action;
import org.uqbar.lacar.ui.model.ControlBuilder;
import org.uqbar.lacar.ui.model.WindowBuilder;

@SuppressWarnings("all")
public abstract class EditarAbonadoWindow extends Dialog<Abonado> {
  public EditarAbonadoWindow(final WindowOwner owner, final Abonado model) {
    super(owner, model);
    WindowBuilder _delegate = this.getDelegate();
    _delegate.setErrorViewer(this);
  }
  
  @Override
  protected void createFormPanel(final Panel mainPanel) {
    final Panel form = new Panel(mainPanel);
    ColumnLayout _columnLayout = new ColumnLayout(2);
    form.setLayout(_columnLayout);
    Label _label = new Label(form);
    _label.setText("Número");
    TextBox _textBox = new TextBox(form);
    ObservableValue<Control, ControlBuilder> _value = _textBox.<ControlBuilder>value();
    ArenaXtendExtensions.operator_spaceship(_value, "numero");
    Label _label_1 = new Label(form);
    _label_1.setText("Nombre");
    TextBox _textBox_1 = new TextBox(form);
    final Procedure1<TextBox> _function = new Procedure1<TextBox>() {
      @Override
      public void apply(final TextBox it) {
        it.setWidth(200);
        ObservableValue<Control, ControlBuilder> _value = it.<ControlBuilder>value();
        ArenaXtendExtensions.operator_spaceship(_value, "nombre");
      }
    };
    ObjectExtensions.<TextBox>operator_doubleArrow(_textBox_1, _function);
    this.addFormPanel(form);
  }
  
  public abstract void addFormPanel(final Panel panel);
  
  @Override
  protected void addActions(final Panel actions) {
    Button _button = new Button(actions);
    Button _setCaption = _button.setCaption("Aceptar");
    final Action _function = new Action() {
      @Override
      public void execute() {
        EditarAbonadoWindow.this.accept();
      }
    };
    Button _onClick = _setCaption.onClick(_function);
    Button _setAsDefault = _onClick.setAsDefault();
    _setAsDefault.disableOnError();
    Button _button_1 = new Button(actions);
    Button _setCaption_1 = _button_1.setCaption("Cancelar");
    final Action _function_1 = new Action() {
      @Override
      public void execute() {
        EditarAbonadoWindow.this.cancel();
      }
    };
    _setCaption_1.onClick(_function_1);
  }
  
  /**
   * Importante overridear el accept para que dispare eventos al volver
   */
  @Override
  public void accept() {
    RepoTelefonia _instance = RepoTelefonia.getInstance();
    Abonado _modelObject = this.getModelObject();
    _instance.actualizarAbonado(_modelObject);
    super.accept();
  }
}