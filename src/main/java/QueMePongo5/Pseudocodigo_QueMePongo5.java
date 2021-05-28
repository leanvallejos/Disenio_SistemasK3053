package QueMePongo5;

public class Pseudocodigo_QueMePongo5 {

  import java.util.ArrayList;
import java.util.List;

  public class Usuario {
    List<Guardarropa> guardarropas;

    public void agregarGuardarropa(Guardarropa guardarropa) {
      guardarropas.add(guardarropa);
    }

  }

  public class Guardarropa {
    CriterioRopa criterio;
    ModificadorGuardarropa modificador;
    List<Prenda> prendas;
    List<Propuesta> propuestas;
    List<Propuesta> propuestasAceptadas;

    public Guardarropa(CriterioRopa criterio){
      this.criterio = criterio;
      this.modificador = new ModificadorGuardarropa();
    }

    public void agregarPrenda(Prenda prenda){
      if(criterio.cumple(prenda))
        prendas.add(prenda);
    }

    public List<Propuesta> verPropuestasDeModificacion(){
      return propuestas();
    }
    public void proponerAgregarPrenda( Prenda prenda){
      Propuesta propuesta = new Propuesta( prenda, Accion.AGREGAR);
      propuestas.add(propuesta);
    }

    public void proponerEliminarPrenda(Prenda prenda){
      Propuesta propuesta = new Propuesta( prenda, Accion.QUITAR);
      propuestas.add(propuesta);
    }

    public void rechazarPropuesta(Propuesta propuesta){
      propuestas.remove(propuesta);
    }

    public void aceptarPropuesta(Propuesta propuesta){
      propuesta.aceptar(prendas);
      propuestasAceptadas.add(propuesta);
      propuestas.remove(propuesta);
    }

    public void deshacerPropuestas(){
      propuestasAceptadas.forEach((propuesta -> propuesta.cambiarAaccionContraria().aceptar(prendas)));
      propuestasAceptadas.removeAll();
    }


  }

  public class Propuesta{
    Prenda prenda;
    Accion accion;

    public Propuesta( Prenda prenda, Accion accion) {
      this.usuario = usuario;
      this.prenda = prenda;
      this.accion = accion;
    }

    public void aceptar(List<Prenda> prendas){
      accion.efecto(prendas, prenda);
    }

    public void cambiarAaccionContraria(){
      this.accion = this.accion.accionContraria();
    }
  }

  public enum Accion{
    AGREGAR{
      public void efecto(List<Prenda> prendas, Prenda prendaPropuesta){
        prendas.add(prendaPropuesta);
      }

      public Accion accionContraria(){
        return Accion.QUITAR;
      }
    },
    QUITAR{
      public void efecto(List<Prenda> prendas, Prenda prendaPropuesta){
        prendas.remove(prendaPropuesta);
      }

      public Accion accionContraria(){
        return Accion.AGREGAR;
      }
    }
  }

}
