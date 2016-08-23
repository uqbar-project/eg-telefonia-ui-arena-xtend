package ar.edu.telefonia.ui

import ar.edu.telefonia.appModel.BuscarAbonadoAppModel
import ar.edu.telefonia.domain.Abonado
import ar.edu.telefonia.domain.Empresa
import ar.edu.telefonia.domain.Residencial
import ar.edu.telefonia.domain.Rural
import java.util.HashMap
import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.CheckBox
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import static extension org.uqbar.arena.xtend.ArenaXtendExtensions.*

class AbonadoWindow extends SimpleWindow<BuscarAbonadoAppModel> {
	
	new(WindowOwner parent, BuscarAbonadoAppModel model) {
		super(parent, model)
		title = "Busqueda de abonados"
		taskDescription = "Seleccione el criterio de búsqueda"
		modelObject.buscar
	}

	override def createMainTemplate(Panel mainPanel) {
		super.createMainTemplate(mainPanel)
		this.createResultsGrid(mainPanel)
		this.createGridActions(mainPanel)
	}

	/** El panel permite buscar por rango desde/hasta nombre y morosos */	
	override def void createFormPanel(Panel mainPanel) {
		var searchFormPanel = new Panel(mainPanel)
		searchFormPanel.layout = new ColumnLayout(2)

		crearTextBox(searchFormPanel, "Nombre desde", "busquedaAbonados.nombreDesde")
		crearTextBox(searchFormPanel, "Nombre hasta", "busquedaAbonados.nombreHasta")
		new Label(searchFormPanel).text = "Sólo morosos"
		new CheckBox(searchFormPanel) => [
			value <=> "busquedaAbonados.soloMorosos"
		]
	}

	def crearTextBox(Panel searchFormPanel, String label, String binding) {
		new Label(searchFormPanel).text = label
		new TextBox(searchFormPanel) => [
			value <=> binding
		]
	}
	
	override protected addActions(Panel actionsPanel) {
		new Button(actionsPanel)
			.setCaption("Buscar")
			.onClick [ | modelObject.buscar ] 
			.setAsDefault
			.disableOnError

		new Button(actionsPanel) //
			.setCaption("Limpiar")
			.onClick [ | modelObject.limpiar ]

		new Button(actionsPanel) //
			.setCaption("Nuevo Abonado Empresa")
			.onClick [ | this.openDialog(new EditarEmpresaWindow(this, new Empresa)) ]

		new Button(actionsPanel) //
			.setCaption("Nuevo Abonado Residencial")
			.onClick [ | this.openDialog(new EditarResidencialWindow(this, new Residencial)) ]

		new Button(actionsPanel) //
			.setCaption("Nuevo Abonado Rural")
			.onClick [ | this.openDialog(new EditarRuralWindow(this, new Rural)) ]

	}

	def protected createResultsGrid(Panel mainPanel) {
		this.describeResultsGrid(new Table<Abonado>(mainPanel, Abonado) => [
			numberVisibleRows = 10
			items <=> "abonados"
			value <=> "abonadoSeleccionado"
		])
	}

	/**
	 * Define las columnas de la grilla Cada columna se puede bindear 1) contra una propiedad del model, como
	 * en el caso del número o el nombre 2) contra un transformer que recibe el model y devuelve un tipo
	 * (generalmente String), como en el caso de Recibe Resumen de Cuenta
	 *
	 * @param table
	 */
	def void describeResultsGrid(Table<Abonado> table) {
		new Column<Abonado>(table) => [
			title = "Nombre"
			fixedSize = 250
			bindContentsToProperty("nombre")
		]
	
		new Column<Abonado>(table) => [
			title = "Número"
			fixedSize = 100
			bindContentsToProperty("numero")
		]

		new Column<Abonado>(table) => [
			title = "Deuda $"
			fixedSize = 100
			bindContentsToProperty("deuda")
		]

		new Column<Abonado>(table) => [
			title = "Datos específicos"
			fixedSize = 200
			bindContentsToProperty("datosEspecificos")			
		]

	}

	def void createGridActions(Panel mainPanel) {
		var actionsPanel = new Panel(mainPanel)
		actionsPanel.layout = new HorizontalLayout

		var edit = new Button(actionsPanel)
			.setCaption("Editar")
			.onClick [ | this.editarAbonado]

		var remove = new Button(actionsPanel)
			.setCaption("Borrar")
			.onClick [ | modelObject.eliminarAbonado]
 
		// Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.
		var elementSelected = new NotNullObservable("abonadoSeleccionado")
		edit.bindEnabled(elementSelected)
		remove.bindEnabled(elementSelected)
	}
	
	def openDialog(Dialog<?> dialog) {
		dialog.onAccept[ |	modelObject.buscar ]
		dialog.open
	}

	/**
	 * Para editar una ventana, construimos un mapa que asocia
	 * clave: clase del objeto seleccionado | valor: un bloque que permite construir la ventana que le corresponde
	 * si bien podríamos resolverlo con reflection, esa solución es mucho más compleja porque requiere buscar el
	 *      constructor adecuado, setearle los parámetros, etc.
	 * esta solución es mucho más simple, sólo necesita que explicitemos los tipos del mapa que estamos generando
	 * (lo importante es el () a EditarAbonadowWindow porque castea los 3 bloques a algo que devuelve 
	 * una subclase de EditarAbonadoWindow 
	 */
	def editarAbonado() {
		//val bloqueQueConstruyeVentana = mapaVentanas.get(modelObject.abonadoSeleccionado.class)
		//this.openDialog(bloqueQueConstruyeVentana.apply)
		this.openDialog(editarAbonado(modelObject.abonadoSeleccionado))
	}
	
	def dispatch editarAbonado(Rural rural) {
		new EditarRuralWindow(this, modelObject.abonadoSeleccionado)
	}

	def dispatch editarAbonado(Residencial residencial) {
		new EditarResidencialWindow(this, modelObject.abonadoSeleccionado)
	}

	def dispatch editarAbonado(Empresa empresa) {
		new EditarEmpresaWindow(this, modelObject.abonadoSeleccionado)
	}
	
//	def getMapaVentanas() {
//		return new HashMap<Class<? extends Abonado>, () => EditarAbonadoWindow> => [
//			put(typeof(Rural), [ | new EditarRuralWindow(this, modelObject.abonadoSeleccionado) ] )
//			put(typeof(Residencial), [ | new EditarResidencialWindow(this, modelObject.abonadoSeleccionado) ] )
//			put(typeof(Empresa), [ | new EditarEmpresaWindow(this, modelObject.abonadoSeleccionado)] )
//		]
//	}

}