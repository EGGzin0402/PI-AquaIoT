#include "Temp.h"

double lerTemperatura(const int pinTermistor, const double beta, const double rx, const double vcc, const double R, const int nAmostras){

  // Le o sensor algumas vezes
  int soma = 0;
  for (int i = 0; i < nAmostras; i++) {
    soma += analogRead(pinTermistor);
    delay (100);
  }
 
  // Determina a resistência do termistor
  double v = (vcc*soma)/(nAmostras*1024.0);
  double rt = (vcc*R)/v - R;
 
  // Calcula a temperatura
  double t = beta / log(rt/rx);
  Serial.println(t-273.0);
  return (t-273.0);

}