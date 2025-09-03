#include <Arduino.h>
#include <ESP8266WiFi.h>
#include <Firebase_ESP_Client.h>
#include "WiFiHelper.h"
#include "Temp.h"
#include "FirebaseHelper.h"

// Credenciais Firebase
#define API_KEY "AIzaSyDFWtQr8L8Ma68s5-q1OEYXG5O3ynn99m8"
#define PROJECT_ID "teste-aquaiot"
#define USER_EMAIL "aquaiotpi@gmail.com"   //
#define USER_PASSWORD "Aqua10T#PI"       // ou pode ser anônimo

// Conexão do termistor
const int pinTermistor = A0;
 
// Parâmetros do termistor
const double beta = 4000.0;
const double r0 = 10000.0;
const double t0 = 273.0 + 25.0;
const double rx = r0 * exp(-beta/t0);

// Parâmetros do circuito
const double vcc = 3.3;
const double R = 10000.0;
 
// Numero de amostras na leitura
const int nAmostras = 5;


void setup() {
  Serial.begin(9600);

  // Chama o WiFiManager modularizado
  configurarWiFi("ESP8266-Config");

  // Garante conexão antes do Firebase
  Serial.println("[WiFi] Aguardando conexão...");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(1000);
  }
  
  initFirebase(API_KEY, PROJECT_ID, USER_EMAIL, USER_PASSWORD);

}

void loop() {

  if (WiFi.status() != WL_CONNECTED) {
    Serial.println("[WiFi] Reconectando...");
    configurarWiFi("ESP8266-Config");
  }

  if (Firebase.ready()) {
    double temp = lerTemperatura(
      pinTermistor,
      beta,
      rx,
      vcc,
      R,
      nAmostras
    );
    sendTemperatureToFirestore(temp);
  } else {
    Serial.println("[Firebase] Não está pronto, aguardando WiFi...");
  }

  delay(10000);

}
