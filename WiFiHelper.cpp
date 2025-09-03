#include "WiFiHelper.h"
#include <WiFiManager.h> 

void configurarWiFi(const char* apName, const char* apPassword) {
    WiFiManager wm;

    // Se quiser resetar credenciais salvas:
    wm.resetSettings();

    Serial.println("\n[WiFi] Iniciando configuração...");

    if (!wm.autoConnect(apName, apPassword)) {
        Serial.println("[WiFi] Falha ao conectar. Reiniciando...");
        delay(3000);
        ESP.restart();
    }

    // Espera até estar conectado
    while (WiFi.status() != WL_CONNECTED) {
        Serial.print(".");
        delay(500);
    }

    if (WiFi.status() == WL_CONNECTED) {
        Serial.println("[WiFi] Conectado com sucesso!");
        Serial.print("[WiFi] SSID: ");
        Serial.println(WiFi.SSID());
        Serial.print("[WiFi] IP: ");
        Serial.println(WiFi.localIP());
    } else {
        Serial.println("[WiFi] Não conectado!");
    }
}