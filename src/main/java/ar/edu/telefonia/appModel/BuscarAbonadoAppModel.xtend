package ar.edu.telefonia.appModel

import ar.edu.telefonia.domain.Abonado
import ar.edu.telefonia.home.HomeTelefonia
import java.util.ArrayList
import java.util.List
import org.uqbar.commons.utils.Observable

@Observable
class BuscarAbonadoAppModel {
	@Property BusquedaAbonados busquedaAbonados
	@Property List<Abonado> abonados
	@Property Abonado abonadoSeleccionado
	
	new() {
		busquedaAbonados = new BusquedaAbonados
		abonados = new ArrayList<Abonado>	
	}
	
	def void buscar() {
		abonados = HomeTelefonia.instance.getAbonados(busquedaAbonados)
	}
	
	def void limpiar() {
		busquedaAbonados.clear()
		abonados.clear()
		abonadoSeleccionado = null
	}
	
	def eliminarAbonado() {
		HomeTelefonia.instance.eliminarAbonado(abonadoSeleccionado)
		this.buscar
	}
	
}