
#ifndef TEMPERATURA_H
#define TEMPERATURA_H

#include <Arduino.h>

// Função para ler a temperatura do termistor
double lerTemperatura(const int pinTermistor, 
                    const double beta, 
                    const double rx, 
                    const double vcc, 
                    const double R, 
                    const int nAmostras);

#endif
