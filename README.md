# Vaishu-assistant-
Voice assistant 
# Vaishu Assistant (Root + Xposed)

This project is a fully offline, voice-controlled assistant app with **root access** and **Xposed integration**. It supports:

- Wake word detection using `Porcupine`
- Offline voice commands using `Vosk`
- Root-level device control (reboot, toggle WiFi, etc.)
- Xposed module to hook into any system-level event

---

## 📁 Project Structure

```
vaishu-assistant/
├── app/
│   ├── build.gradle
│   └── src/main/
│       ├── AndroidManifest.xml
│       └── java/com/vaishu/assistant/
│           ├── MainActivity.kt
│           ├── VoiceCommandService.kt
│           └── RootCommandExecutor.kt
├── xposedmodule/
│   ├── build.gradle
│   └── src/main/java/com/vaishu/xposed/
│       ├── XposedInit.java
│       └── AssistantHook.java
├── build.gradle
├── settings.gradle
└── README.md
```

---

## 🚀 How to Build

### 1. Requirements
- Android Studio Hedgehog or later
- JDK 11 or newer
- Android SDK 34

### 2. Clone the Repo
```bash
git clone https://github.com/YOUR_USERNAME/vaishu-assistant.git
cd vaishu-assistant
```

### 3. Open in Android Studio
- File → Open → select this folder
- Wait for Gradle sync to finish

### 4. Build APK and Xposed ZIP
- `Build > Build APK` → install on your rooted phone
- Xposed module is in `xposedmodule/build/outputs/aar/`
  - You’ll need to convert `.aar` to `.zip` (instructions coming later)

---

## 🗣️ Voice Commands
- "Vaishu reboot phone"
- "Vaishu turn on WiFi"
- "Vaishu open camera"

---

## 🔒 Permissions Required
This app uses:
- `RECEIVE_BOOT_COMPLETED`
- `FOREGROUND_SERVICE`
- `INTERNET` (for local model loading)
- Full root access (via `su`)

---

## ⚙️ LSPosed Setup
1. Flash LSPosed via Magisk
2. Enable this module in LSPosed manager
3. Reboot your device

---

## 🔚 Disclaimer
This project is for **educational purposes only**. Use responsibly.

---
