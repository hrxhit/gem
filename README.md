# Smart Island Reborn (Android Studio Project)

A completely rebuilt, private, fluid, and aesthetically elevated Dynamic Island overlay for Android, inspired by Apple's iOS Dynamic Island and Samsung One UI 7 Now Bar.

## Key Upgrades over SmartIsland v7.6.0:
1. **100% Offline Privacy Guarantee**:
   - `android.permission.INTERNET` completely purged.
   - Zero external tracking, telemetry, or network checks.
   - On-Device Regex OTP Masker: Detects OTPs and credentials, masking raw text and presenting a single-tap haptic [Copy Code] capsule.
   - RAM-only volatile state. No unencrypted SQLite logging.

2. **Apple & Samsung Grade Aesthetics**:
   - iOS G2 Continuous Squircle geometry (`SquircleShape.kt`) blending seamlessly with camera cutouts.
   - Decoupled asymmetric dual-spring physics (`dampingRatio = 0.72f, stiffness = 320f`).
   - Dynamic ambient glow and real-time audio wave visualizers.
   - High-contrast circular action capsules for Phone & VoIP calls.

3. **Multi-Theme Engine**:
   - OLED Obsidian, Neo-Glassmorphism, One UI 7 Now Pill, iOS 18 Titanium, Cyberpunk Dot Matrix.

## How to Import & Run:
1. Extract this zip file into your desired directory.
2. Open Android Studio (Ladybug / Koala or newer).
3. Select **File -> Open** and choose the `SmartIslandReborn` folder.
4. Let Gradle sync.
5. Click **Run ('app')** on your device or emulator.\n