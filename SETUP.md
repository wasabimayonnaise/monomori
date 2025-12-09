# Developer Setup Guide

## Getting Started with monomori Development

This guide will help you set up your development environment for contributing to monomori (物守り).

## Prerequisites

### Required Software
1. **Android Studio** (Hedgehog 2023.1.1 or later)
   - Download from: https://developer.android.com/studio
   - Includes Android SDK and build tools

2. **JDK 17 or later**
   - Bundled with Android Studio, or
   - Download from: https://adoptium.net/

3. **Git**
   - For version control
   - Download from: https://git-scm.com/

### System Requirements
- **OS**: Windows 10+, macOS 10.14+, or Linux
- **RAM**: 8GB minimum (16GB recommended)
- **Disk Space**: 10GB+ free space
- **Display**: 1280x800 minimum resolution

## Step-by-Step Setup

### 1. Clone the Repository

```bash
git clone https://github.com/wasabimayonnaise/monomori.git
cd monomori
```

### 2. Open in Android Studio

1. Launch Android Studio
2. Select **File → Open**
3. Navigate to the cloned `monomori` directory
4. Click **OK**
5. Wait for Gradle sync to complete (this may take a few minutes on first run)

### 3. Configure Android SDK

Android Studio should automatically download required SDK components. If not:

1. Go to **Tools → SDK Manager**
2. Ensure the following are installed:
   - Android SDK Platform 34 (Android 14)
   - Android SDK Platform 26 (Android 8.0) - minimum
   - Android SDK Build-Tools 34.0.0 or later
   - Android SDK Platform-Tools
   - Android Emulator (for testing)

### 4. Set Up an Emulator or Device

#### Option A: Android Emulator
1. Go to **Tools → Device Manager**
2. Click **Create Device**
3. Choose a device (e.g., Pixel 6)
4. Select a system image (API 26 or higher, API 34 recommended)
5. Click **Finish**

#### Option B: Physical Device
1. Enable **Developer Options** on your Android device:
   - Go to Settings → About Phone
   - Tap "Build Number" 7 times
2. Enable **USB Debugging** in Developer Options
3. Connect device via USB
4. Accept USB debugging prompt on device

### 5. Build the Project

#### Via Android Studio
1. Click **Build → Make Project** (Ctrl+F9 / Cmd+F9)
2. Wait for build to complete
3. Check for any errors in the Build panel

#### Via Command Line
```bash
# Debug build
./gradlew assembleDebug

# Clean build
./gradlew clean assembleDebug
```

### 6. Run the App

#### Via Android Studio
1. Select your emulator or device from the device dropdown
2. Click the **Run** button (▶️) or press Shift+F10
3. Wait for the app to launch

#### Via Command Line
```bash
# Install on connected device
./gradlew installDebug

# Run on connected device
adb shell am start -n com.monomori/.MainActivity
```

## Project Structure Overview

```
monomori/
├── app/                          # Main application module
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/monomori/
│   │   │   │   ├── data/        # Data layer (entities, DAOs, database)
│   │   │   │   ├── di/          # Dependency injection
│   │   │   │   ├── ui/          # UI layer (screens, theme, navigation)
│   │   │   │   └── util/        # Utilities
│   │   │   ├── res/             # Resources (layouts, strings, icons)
│   │   │   └── AndroidManifest.xml
│   │   └── test/                # Unit tests
│   └── build.gradle.kts         # App module build config
├── gradle/                      # Gradle wrapper
├── build.gradle.kts             # Root build config
└── settings.gradle.kts          # Project settings
```

## Key Files to Know

- **MainActivity.kt** - Entry point, hosts Compose UI
- **MonomoriApplication.kt** - Application class, Hilt setup
- **MonomoriDatabase.kt** - Room database definition
- **MonomoriNavigation.kt** - Navigation graph
- **Theme.kt** - Material 3 theming

## Development Workflow

### 1. Create a Feature Branch
```bash
git checkout -b feature/your-feature-name
```

### 2. Make Changes
- Follow [ARCHITECTURE.md](ARCHITECTURE.md) for structure
- Follow [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines
- Write clean, documented code

### 3. Test Your Changes
```bash
# Run unit tests
./gradlew test

# Run instrumentation tests (requires device/emulator)
./gradlew connectedAndroidTest
```

### 4. Commit Your Changes
```bash
git add .
git commit -m "Add feature: description of your changes"
```

### 5. Push and Create PR
```bash
git push origin feature/your-feature-name
```
Then create a Pull Request on GitHub.

## Common Commands

### Gradle
```bash
# List all tasks
./gradlew tasks

# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK (requires signing config)
./gradlew assembleRelease

# Run tests
./gradlew test

# Generate test coverage report
./gradlew jacocoTestReport
```

### ADB (Android Debug Bridge)
```bash
# List connected devices
adb devices

# Install APK
adb install app/build/outputs/apk/debug/app-debug.apk

# Uninstall app
adb uninstall com.monomori

# View logs
adb logcat

# Clear app data
adb shell pm clear com.monomori
```

## Troubleshooting

### Gradle Sync Failed
**Problem**: Gradle sync fails with dependency errors

**Solution**:
1. Check internet connection
2. Invalidate caches: **File → Invalidate Caches → Invalidate and Restart**
3. Delete `.gradle` folder and sync again
4. Update Gradle wrapper: `./gradlew wrapper --gradle-version 8.2`

### Build Failed - SDK Not Found
**Problem**: Cannot find Android SDK

**Solution**:
1. Open **File → Project Structure**
2. Set Android SDK location
3. Or create/edit `local.properties`:
   ```properties
   sdk.dir=/path/to/Android/Sdk
   ```

### App Crashes on Launch
**Problem**: App crashes immediately after launch

**Solution**:
1. Check logcat for errors: **View → Tool Windows → Logcat**
2. Check if Hilt is properly configured
3. Verify all dependencies are synced
4. Clean and rebuild: `./gradlew clean assembleDebug`

### Emulator Won't Start
**Problem**: Android Emulator fails to start

**Solution**:
1. Check virtualization is enabled in BIOS
2. Update emulator: **Tools → SDK Manager → SDK Tools → Android Emulator**
3. Try creating a new virtual device
4. On Windows: Disable Hyper-V if using Intel HAXM

### Out of Memory Error
**Problem**: Gradle runs out of memory during build

**Solution**:
Edit `gradle.properties`:
```properties
org.gradle.jvmargs=-Xmx4096m -Dfile.encoding=UTF-8
```

## IDE Tips

### Useful Shortcuts (Windows/Linux | macOS)
- **Run app**: Shift+F10 | Ctrl+R
- **Debug app**: Shift+F9 | Ctrl+D
- **Build project**: Ctrl+F9 | Cmd+F9
- **Find file**: Ctrl+Shift+N | Cmd+Shift+O
- **Find in files**: Ctrl+Shift+F | Cmd+Shift+F
- **Format code**: Ctrl+Alt+L | Cmd+Option+L
- **Optimize imports**: Ctrl+Alt+O | Ctrl+Option+O

### Recommended Plugins
- **Rainbow Brackets** - Better bracket matching
- **GitToolBox** - Enhanced Git integration
- **Key Promoter X** - Learn keyboard shortcuts
- **Compose Multipreview** - Preview Compose UI

### Code Style
The project uses the official Kotlin code style:
1. Go to **Settings → Editor → Code Style → Kotlin**
2. Click **Set from...** → **Kotlin style guide**

## Testing

### Run Unit Tests
```bash
./gradlew test
```

### Run Instrumentation Tests
```bash
./gradlew connectedAndroidTest
```

### View Test Reports
Open: `app/build/reports/tests/testDebugUnitTest/index.html`

## Resources

### Official Documentation
- [Android Developer Guide](https://developer.android.com/guide)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Room Database](https://developer.android.com/training/data-storage/room)
- [Hilt Dependency Injection](https://developer.android.com/training/dependency-injection/hilt-android)
- [Material Design 3](https://m3.material.io/)

### Community
- [monomori GitHub Issues](https://github.com/wasabimayonnaise/monomori/issues)
- [Stack Overflow - Android](https://stackoverflow.com/questions/tagged/android)
- [r/androiddev](https://www.reddit.com/r/androiddev/)

## Next Steps

1. Read [ARCHITECTURE.md](ARCHITECTURE.md) to understand the codebase
2. Check [TODO.md](TODO.md) for tasks to work on
3. Review [CONTRIBUTING.md](CONTRIBUTING.md) before making changes
4. Join discussions in GitHub Issues
5. Start with "good first issue" labeled tasks

## Need Help?

- Open an issue on GitHub
- Check existing issues for similar problems
- Review the documentation files
- Ask in pull request comments

Happy coding! 🎉
