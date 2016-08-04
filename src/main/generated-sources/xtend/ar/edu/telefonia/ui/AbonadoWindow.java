package ar.edu.telefonia.ui;

import ar.edu.telefonia.appModel.BuscarAbonadoAppModel;
import ar.edu.telefonia.domain.Abonado;
import ar.edu.telefonia.domain.Empresa;
import ar.edu.telefonia.domain.Residencial;
import ar.edu.telefonia.domain.Rural;
import ar.edu.telefonia.ui.EditarAbonadoWindow;
import ar.edu.telefonia.ui.EditarEmpresaWindow;
import ar.edu.telefonia.ui.EditarResidencialWindow;
import ar.edu.telefonia.ui.EditarRuralWindow;
import java.util.HashMap;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.bindings.ObservableValue;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Control;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.xtend.ArenaXtendExtensions;
import org.uqbar.lacar.ui.model.Action;
import org.uqbar.lacar.ui.model.ControlBuilder;
import org.uqbar.lacar.ui.model.TableBuilder;
import org.uqbar.lacar.ui.model.bindings.ViewObservable;

@SuppressWarnings("all")
public class AbonadoWindow extends SimpleWindow<BuscarAbonadoAppModel> {
  public AbonadoWindow(final WindowOwner parent, final BuscarAbonadoAppModel model) {
    super(parent, model);
    this.setTitle("Busqueda de abonados");
    this.setTaskDescription("Seleccione el criterio de búsqueda");
    BuscarAbonadoAppModel _modelObject = this.getModelObject();
    _modelObject.buscar();
  }
  
  @Override
  public void createMainTemplate(final Panel mainPanel) {
    super.createMainTemplate(mainPanel);
    this.createResultsGrid(mainPanel);
    this.createGridActions(mainPanel);
  }
  
  /**
   * El panel permite buscar por rango desde/hasta nombre y morosos
   */
  @Override
  public void createFormPanel(final Panel mainPanel) {
    Panel searchFormPanel = new Panel(mainPanel);
    ColumnLayout _columnLayout = new ColumnLayout(2);
    searchFormPanel.setLayout(_columnLayout);
    this.crearTextBox(searchFormPanel, "Nombre desde", "busquedaAbonados.nombreDesde");
    this.crearTextBox(searchFormPanel, "Nombre hasta", "busquedaAbonados.nombreHasta");
    Label _label = new Label(searchFormPanel);
    _label.setText("Sólo morosos");
    CheckBox _checkBox = new CheckBox(searchFormPanel);
    final Procedure1<CheckBox> _function = new Procedure1<CheckBox>() {
      @Override
      public void apply(final CheckBox it) {
        ObservableValue<Control, ControlBuilder> _value = it.<ControlBuilder>value();
        ArenaXtendExtensions.operator_spaceship(_value, "busquedaAbonados.soloMorosos");
      }
    };
    ObjectExtensions.<CheckBox>operator_doubleArrow(_checkBox, _function);
  }
  
  public TextBox crearTextBox(final Panel searchFormPanel, final String label, final String binding) {
    TextBox _xblockexpression = null;
    {
      Label _label = new Label(searchFormPanel);
      _label.setText(label);
      TextBox _textBox = new TextBox(searchFormPanel);
      final Procedure1<TextBox> _function = new Procedure1<TextBox>() {
        @Override
        public void apply(final TextBox it) {
          ObservableValue<Control, ControlBuilder> _value = it.<ControlBuilder>value();
          ArenaXtendExtensions.operator_spaceship(_value, binding);
        }
      };
      _xblockexpression = ObjectExtensions.<TextBox>operator_doubleArrow(_textBox, _function);
    }
    return _xblockexpression;
  }
  
  @Override
  protected void addActions(final Panel actionsPanel) {
    Button _button = new Button(actionsPanel);
    Button _setCaption = _button.setCaption("Buscar");
    final Action _function = new Action() {
      @Override
      public void execute() {
        BuscarAbonadoAppModel _modelObject = AbonadoWindow.this.getModelObject();
        _modelObject.buscar();
      }
    };
    Button _onClick = _setCaption.onClick(_function);
    Button _setAsDefault = _onClick.setAsDefault();
    _setAsDefault.disableOnError();
    Button _button_1 = new Button(actionsPanel);
    Button _setCaption_1 = _button_1.setCaption("Limpiar");
    final Action _function_1 = new Action() {
      @Override
      public void execute() {
        BuscarAbonadoAppModel _modelObject = AbonadoWindow.this.getModelObject();
        _modelObject.limpiar();
      }
    };
    _setCaption_1.onClick(_function_1);
    Button _button_2 = new Button(actionsPanel);
    Button _setCaption_2 = _button_2.setCaption("Nuevo Abonado Empresa");
    final Action _function_2 = new Action() {
      @Override
      public void execute() {
        Empresa _empresa = new Empresa();
        EditarEmpresaWindow _editarEmpresaWindow = new EditarEmpresaWindow(AbonadoWindow.this, _empresa);
        AbonadoWindow.this.openDialog(_editarEmpresaWindow);
      }
    };
    _setCaption_2.onClick(_function_2);
    Button _button_3 = new Button(actionsPanel);
    Button _setCaption_3 = _button_3.setCaption("Nuevo Abonado Residencial");
    final Action _function_3 = new Action() {
      @Override
      public void execute() {
        Residencial _residencial = new Residencial();
        EditarResidencialWindow _editarResidencialWindow = new EditarResidencialWindow(AbonadoWindow.this, _residencial);
        AbonadoWindow.this.openDialog(_editarResidencialWindow);
      }
    };
    _setCaption_3.onClick(_function_3);
    Button _button_4 = new Button(actionsPanel);
    Button _setCaption_4 = _button_4.setCaption("Nuevo Abonado Rural");
    final Action _function_4 = new Action() {
      @Override
      public void execute() {
        Rural _rural = new Rural();
        EditarRuralWindow _editarRuralWindow = new EditarRuralWindow(AbonadoWindow.this, _rural);
        AbonadoWindow.this.openDialog(_editarRuralWindow);
      }
    };
    _setCaption_4.onClick(_function_4);
  }
  
  protected void createResultsGrid(final Panel mainPanel) {
    Table<Abonado> _table = new Table<Abonado>(mainPanel, Abonado.class);
    final Procedure1<Table<Abonado>> _function = new Procedure1<Table<Abonado>>() {
      @Override
      public void apply(final Table<Abonado> it) {
        it.setHeight(200);
        it.setWidth(550);
        ViewObservable<Table<Abonado>, TableBuilder<Abonado>> _items = it.items();
        ArenaXtendExtensions.operator_spaceship(_items, "abonados");
        ObservableValue<Control, ControlBuilder> _value = it.<ControlBuilder>value();
        ArenaXtendExtensions.operator_spaceship(_value, "abonadoSeleccionado");
      }
    };
    Table<Abonado> _doubleArrow = ObjectExtensions.<Table<Abonado>>operator_doubleArrow(_table, _function);
    this.describeResultsGrid(_doubleArrow);
  }
  
  /**
   * Define las columnas de la grilla Cada columna se puede bindear 1) contra una propiedad del model, como
   * en el caso del número o el nombre 2) contra un transformer que recibe el model y devuelve un tipo
   * (generalmente String), como en el caso de Recibe Resumen de Cuenta
   * 
   * @param table
   */
  public void describeResultsGrid(final Table<Abonado> table) {
    Column<Abonado> _column = new Column<Abonado>(table);
    final Procedure1<Column<Abonado>> _function = new Procedure1<Column<Abonado>>() {
      @Override
      public void apply(final Column<Abonado> it) {
        it.setTitle("Nombre");
        it.setFixedSize(250);
        it.bindContentsToProperty("nombre");
      }
    };
    ObjectExtensions.<Column<Abonado>>operator_doubleArrow(_column, _function);
    Column<Abonado> _column_1 = new Column<Abonado>(table);
    final Procedure1<Column<Abonado>> _function_1 = new Procedure1<Column<Abonado>>() {
      @Override
      public void apply(final Column<Abonado> it) {
        it.setTitle("Número");
        it.setFixedSize(100);
        it.bindContentsToProperty("numero");
      }
    };
    ObjectExtensions.<Column<Abonado>>operator_doubleArrow(_column_1, _function_1);
    Column<Abonado> _column_2 = new Column<Abonado>(table);
    final Procedure1<Column<Abonado>> _function_2 = new Procedure1<Column<Abonado>>() {
      @Override
      public void apply(final Column<Abonado> it) {
        it.setTitle("Deuda $");
        it.setFixedSize(100);
        it.bindContentsToProperty("deuda");
      }
    };
    ObjectExtensions.<Column<Abonado>>operator_doubleArrow(_column_2, _function_2);
    Column<Abonado> _column_3 = new Column<Abonado>(table);
    final Procedure1<Column<Abonado>> _function_3 = new Procedure1<Column<Abonado>>() {
      @Override
      public void apply(final Column<Abonado> it) {
        it.setTitle("Datos específicos");
        it.setFixedSize(200);
        it.bindContentsToProperty("datosEspecificos");
      }
    };
    ObjectExtensions.<Column<Abonado>>operator_doubleArrow(_column_3, _function_3);
  }
  
  public void createGridActions(final Panel mainPanel) {
    Panel actionsPanel = new Panel(mainPanel);
    HorizontalLayout _horizontalLayout = new HorizontalLayout();
    actionsPanel.setLayout(_horizontalLayout);
    Button _button = new Button(actionsPanel);
    Button _setCaption = _button.setCaption("Editar");
    final Action _function = new Action() {
      @Override
      public void execute() {
        AbonadoWindow.this.editarAbonado();
      }
    };
    Button edit = _setCaption.onClick(_function);
    Button _button_1 = new Button(actionsPanel);
    Button _setCaption_1 = _button_1.setCaption("Borrar");
    final Action _function_1 = new Action() {
      @Override
      public void execute() {
        BuscarAbonadoAppModel _modelObject = AbonadoWindow.this.getModelObject();
        _modelObject.eliminarAbonado();
      }
    };
    Button remove = _setCaption_1.onClick(_function_1);
    NotNullObservable elementSelected = new NotNullObservable("abonadoSeleccionado");
    edit.<Object, ControlBuilder>bindEnabled(elementSelected);
    remove.<Object, ControlBuilder>bindEnabled(elementSelected);
  }
  
  public void openDialog(final Dialog<?> dialog) {
    final Action _function = new Action() {
      @Override
      public void execute() {
        BuscarAbonadoAppModel _modelObject = AbonadoWindow.this.getModelObject();
        _modelObject.buscar();
      }
    };
    dialog.onAccept(_function);
    dialog.open();
  }
  
  /**
   * Para editar una ventana, construimos un mapa que asocia
   * clave: clase del objeto seleccionado | valor: un bloque que permite construir la ventana que le corresponde
   * si bien podríamos resolverlo con reflection, esa solución es mucho más compleja porque requiere buscar el
   *      constructor adecuado, setearle los parámetros, etc.
   * esta solución es mucho más simple, sólo necesita que explicitemos los tipos del mapa que estamos generando
   * (lo importante es el () a EditarAbonadowWindow porque castea los 3 bloques a algo que devuelve
   * una sublcase de EditarAbonadoWindow
   */
  public void editarAbonado() {
    HashMap<Class<? extends Abonado>, Function0<? extends EditarAbonadoWindow>> _mapaVentanas = this.getMapaVentanas();
    BuscarAbonadoAppModel _modelObject = this.getModelObject();
    Abonado _abonadoSeleccionado = _modelObject.getAbonadoSeleccionado();
    Class<? extends Abonado> _class = _abonadoSeleccionado.getClass();
    final Function0<? extends EditarAbonadoWindow> bloqueQueConstruyeVentana = _mapaVentanas.get(_class);
    EditarAbonadoWindow _apply = bloqueQueConstruyeVentana.apply();
    this.openDialog(_apply);
  }
  
  public HashMap<Class<? extends Abonado>, Function0<? extends EditarAbonadoWindow>> getMapaVentanas() {
    HashMap<Class<? extends Abonado>, Function0<? extends EditarAbonadoWindow>> _hashMap = new HashMap<Class<? extends Abonado>, Function0<? extends EditarAbonadoWindow>>();
    final Procedure1<HashMap<Class<? extends Abonado>, Function0<? extends EditarAbonadoWindow>>> _function = new Procedure1<HashMap<Class<? extends Abonado>, Function0<? extends EditarAbonadoWindow>>>() {
      @Override
      public void apply(final HashMap<Class<? extends Abonado>, Function0<? extends EditarAbonadoWindow>> it) {
        final Function0<EditarAbonadoWindow> _function = new Function0<EditarAbonadoWindow>() {
          @Override
          public EditarAbonadoWindow apply() {
            BuscarAbonadoAppModel _modelObject = AbonadoWindow.this.getModelObject();
            Abonado _abonadoSeleccionado = _modelObject.getAbonadoSeleccionado();
            return new EditarRuralWindow(AbonadoWindow.this, _abonadoSeleccionado);
          }
        };
        it.put(Rural.class, _function);
        final Function0<EditarAbonadoWindow> _function_1 = new Function0<EditarAbonadoWindow>() {
          @Override
          public EditarAbonadoWindow apply() {
            BuscarAbonadoAppModel _modelObject = AbonadoWindow.this.getModelObject();
            Abonado _abonadoSeleccionado = _modelObject.getAbonadoSeleccionado();
            return new EditarResidencialWindow(AbonadoWindow.this, _abonadoSeleccionado);
          }
        };
        it.put(Residencial.class, _function_1);
        final Function0<EditarAbonadoWindow> _function_2 = new Function0<EditarAbonadoWindow>() {
          @Override
          public EditarAbonadoWindow apply() {
            BuscarAbonadoAppModel _modelObject = AbonadoWindow.this.getModelObject();
            Abonado _abonadoSeleccionado = _modelObject.getAbonadoSeleccionado();
            return new EditarEmpresaWindow(AbonadoWindow.this, _abonadoSeleccionado);
          }
        };
        it.put(Empresa.class, _function_2);
      }
    };
    return ObjectExtensions.<HashMap<Class<? extends Abonado>, Function0<? extends EditarAbonadoWindow>>>operator_doubleArrow(_hashMap, _function);
  }
}
