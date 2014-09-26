package ar.edu.telefonia.home;

import ar.edu.telefonia.appModel.BusquedaAbonados;
import ar.edu.telefonia.domain.Abonado;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class HomeTelefonia {
  private List<Abonado> abonados;
  
  private static HomeTelefonia instance = null;
  
  private HomeTelefonia() {
    ArrayList<Abonado> _arrayList = new ArrayList<Abonado>();
    this.abonados = _arrayList;
  }
  
  public static HomeTelefonia getInstance() {
    HomeTelefonia _xblockexpression = null;
    {
      boolean _equals = Objects.equal(HomeTelefonia.instance, null);
      if (_equals) {
        HomeTelefonia _homeTelefonia = new HomeTelefonia();
        HomeTelefonia.instance = _homeTelefonia;
      }
      _xblockexpression = HomeTelefonia.instance;
    }
    return _xblockexpression;
  }
  
  public Abonado doGetAbonado(final Abonado abonado) {
    final Function1<Abonado, Boolean> _function = new Function1<Abonado, Boolean>() {
      public Boolean apply(final Abonado it) {
        String _nombre = it.getNombre();
        String _nombre_1 = abonado.getNombre();
        return Boolean.valueOf(_nombre.equalsIgnoreCase(_nombre_1));
      }
    };
    return IterableExtensions.<Abonado>findFirst(this.abonados, _function);
  }
  
  /**
   * Genero una copia del objeto para no actualizar el que referencia el home
   */
  public Abonado getAbonado(final Abonado abonado) {
    Abonado _xblockexpression = null;
    {
      final Abonado result = this.doGetAbonado(abonado);
      Abonado _xifexpression = null;
      boolean _equals = Objects.equal(result, null);
      if (_equals) {
        _xifexpression = null;
      } else {
        _xifexpression = result.copy();
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  /**
   * Genero una copia de los objetos para no actualizar el que referencia el home
   */
  public List<Abonado> getAbonados(final BusquedaAbonados busquedaAbonados) {
    List<Abonado> _xblockexpression = null;
    {
      final Function1<Abonado, Abonado> _function = new Function1<Abonado, Abonado>() {
        public Abonado apply(final Abonado it) {
          return it.copy();
        }
      };
      final List<Abonado> copiaDeAbonados = ListExtensions.<Abonado, Abonado>map(this.abonados, _function);
      InputOutput.<String>println(("Abonados:" + this.abonados));
      InputOutput.<String>println(("Copia de abonados: " + copiaDeAbonados));
      final Function1<Abonado, Boolean> _function_1 = new Function1<Abonado, Boolean>() {
        public Boolean apply(final Abonado it) {
          return Boolean.valueOf(busquedaAbonados.cumple(it));
        }
      };
      final Iterable<Abonado> abonadosFiltrados = IterableExtensions.<Abonado>filter(copiaDeAbonados, _function_1);
      InputOutput.<String>println(("Abonados filtrados: " + abonadosFiltrados));
      _xblockexpression = IterableExtensions.<Abonado>toList(abonadosFiltrados);
    }
    return _xblockexpression;
  }
  
  public String actualizarAbonado(final Abonado abonado) {
    String _xblockexpression = null;
    {
      abonado.validar();
      Long _id = abonado.getId();
      boolean _equals = Objects.equal(_id, null);
      if (_equals) {
        final Function2<Long, Abonado, Long> _function = new Function2<Long, Abonado, Long>() {
          public Long apply(final Long max, final Abonado unAbonado) {
            Long _id = unAbonado.getId();
            return Long.valueOf(Math.max((max).longValue(), (_id).longValue()));
          }
        };
        Long _fold = IterableExtensions.<Abonado, Long>fold(this.abonados, Long.valueOf(0l), _function);
        long _plus = ((_fold).longValue() + 1);
        abonado.setId(Long.valueOf(_plus));
        this.abonados.add(abonado);
      } else {
        Abonado abonadoPosta = this.doGetAbonado(abonado);
        abonadoPosta = abonado;
      }
      _xblockexpression = InputOutput.<String>println(("Ahora abonados tiene:" + this.abonados));
    }
    return _xblockexpression;
  }
  
  public boolean eliminarAbonado(final Abonado abonado) {
    Abonado _doGetAbonado = this.doGetAbonado(abonado);
    return this.abonados.remove(_doGetAbonado);
  }
}
