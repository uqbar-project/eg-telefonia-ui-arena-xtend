package ar.edu.telefonia.ui

import ar.edu.telefonia.domain.Abonado
import ar.edu.telefonia.repo.RepoTelefonia
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.NumericField
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

import static extension org.uqbar.arena.xtend.ArenaXtendExtensions.*

abstract class EditarAbonadoWindow extends Dialog<Abonado> {

	new(WindowOwner owner, Abonado model) {
		super(owner, model)
		this.delegate.setErrorViewer(this)
	}

	override protected createFormPanel(Panel mainPanel) {
		val form = new Panel(mainPanel)
		form.layout = new ColumnLayout(2)
		
		new Label(form).text = "Número"
		new TextBox(form).value <=> "numero"

		new Label(form).text = "Nombre"
		new TextBox(form) => [
			width = 200
			value <=> "nombre"
		]

		this.addFormPanel(form)
	}

	abstract def void addFormPanel(Panel panel)

	override protected void addActions(Panel actions) {
		new Button(actions)
			.setCaption("Aceptar")
			.onClick [ | this.accept ]
			.setAsDefault
			.disableOnError

		new Button(actions) //
			.setCaption("Cancelar")
			.onClick[|this.cancel]
	}

	/** Importante overridear el accept para que dispare eventos al volver */
	override accept() {
		/** MUY IMPORTANTE, primero hay que actualizar el abonado y luego, hacer super.accept
		 * para que capture los errores y refresque la grilla
		 */
		RepoTelefonia.instance.actualizarAbonado(this.modelObject)
		super.accept
	}

}

class EditarResidencialWindow extends EditarAbonadoWindow {

	new(WindowOwner owner, Abonado model) {
		super(owner, model)
		this.title = "Crear un abonado residencial"
	}

	override addFormPanel(Panel panel) {
	}

}

class EditarRuralWindow extends EditarAbonadoWindow {

	new(WindowOwner owner, Abonado model) {
		super(owner, model)
		this.title = "Crear un abonado rural"
	}

	override addFormPanel(Panel panel) {
		new Label(panel).text = "Cantidad de hectáreas"
		new NumericField(panel).value <=> "cantidadHectareas"
	}

}

class EditarEmpresaWindow extends EditarAbonadoWindow {

	new(WindowOwner owner, Abonado model) {
		super(owner, model)
		this.title = "Crear un abonado empresa"
	}

	override addFormPanel(Panel panel) {
		new Label(panel).text = "CUIT"
		new TextBox(panel).value <=> "cuit"
	}

}
