#ifndef FIREBASE_HELPER_H
#define FIREBASE_HELPER_H

#include <Arduino.h>

void initFirebase(const char* apiKey, const char* projectId, const char* userEmail, const char* userPassword);
void sendTemperatureToFirestore(double temperatura);

#endif
