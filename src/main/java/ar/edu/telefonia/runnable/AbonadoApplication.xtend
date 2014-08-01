package ar.edu.telefonia.runnable

import ar.edu.telefonia.appModel.BuscarAbonadoAppModel
import ar.edu.telefonia.ui.AbonadoWindow
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window

class AbonadoApplication extends Application {
	
	static def void main(String[] args) { 
		new AbonadoApplication().start()
	}

	override protected Window<?> createMainWindow() {
		return new AbonadoWindow(this, new BuscarAbonadoAppModel)
	}
	
}