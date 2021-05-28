import Pruebas.Mascota;
import org.junit.jupiter.api.Test;

public class Test1 {


  @Test
  void TESTEOCARACTERISTICA{
    Mascota mascota = new Mascota();
    mascota.agregarCaracteristica("lean");
    mascota.agregarCaracteristica(2);


  }



}
