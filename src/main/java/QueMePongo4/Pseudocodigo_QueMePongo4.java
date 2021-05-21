interface GeneradorSugerencias{
  public List<Sugerencia> generarSugerenciasDesde(List<Prenda> prendasAptas)
}


class Guardarropa implements ObtencionDeTemperatura{
  AccuWeatherAPI apiClima = new AccuWeatherAPI();
  List<Prenda> prendas;

  public List<Atuendo> generarSugerenciasAcordesAlClima(){
    return generarSugerenciasDesde(prendasAcordesATemperaturaActual());
  }

  public List<Atuendo> prendasAcordesATemperaturaActual(){
    return prendas.filter{prenda -> prenda.esAcordeATemperatura(obtenerTemperaturaActual(api))};
  }

  public int obtenerTemperaturaActual (){
    int temperaturaEnFahrenheit = apiClima.getWeather(“Buenos Aires, Argentina”).get(0).get("Temperature").get("value");
    return conversionFahrenheitaCelsus(temperaturaEnFahrenheit);
  }

  public int conversionFahrenheitaCelsus(int f){
    return (f-32)*(5/9);
  }
}

class Prenda{

  Integer temperaturaMaxima;

  Boolean esAcordeATemperatura(Integer temperatura){
    return temperatura < temperaturaMaxima;
  }
}