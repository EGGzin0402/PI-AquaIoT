#include "FirebaseHelper.h"
#include <Firebase_ESP_Client.h>

// Objetos globais
FirebaseData fbdo;
FirebaseAuth auth;
FirebaseConfig config;

String uid;
String projectId;
String documentPath;  // ID fixo do documento

// Inicializa Firebase
void initFirebase(const char* apiKey, const char* projId, const char* userEmail, const char* userPassword) {
    config.api_key = apiKey;
    projectId = projId;

    auth.user.email = userEmail;
    auth.user.password = userPassword;

    Firebase.begin(&config, &auth);
    Firebase.reconnectWiFi(true);

    // Espera autenticação para obter UID
    while (strlen(auth.token.uid.c_str()) == 0) {
        Serial.println("[Firebase] Aguardando UID...");
        delay(500);
    }
    uid = auth.token.uid.c_str();
    documentPath = "Users/" + uid + "/dispositivos/sensor1";

    Serial.println("[Firebase] Inicializado");
}

// Atualiza temperatura no Firestore
void sendTemperatureToFirestore(double temperatura) {
    if (Firebase.ready()) {
        String content = 
          "{ \"fields\": { \"temperatura\": { \"doubleValue\": " + String(temperatura) + "} } }";

        // Cria ou atualiza SEMPRE no mesmo documento
        if (Firebase.Firestore.patchDocument(&fbdo, projectId.c_str(), "", documentPath.c_str(), content.c_str(), "temperatura")) {
            Serial.println("[Firebase] Documento atualizado com sucesso!");
        } else {
            Serial.print("[Firebase] Erro: ");
            Serial.println(fbdo.errorReason());
        }
    } else {
        Serial.println("[Firebase] Firebase não está pronto!");
    }
}
