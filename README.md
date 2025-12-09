# 物守り monomori

**Guardian of Things** - A universal collection management app for Android

物守り (monomori) is a comprehensive mobile application designed to help collectors catalog, organize, and cherish their treasured possessions. From books and manga to figures, vinyl records, trading cards, and beyond - monomori serves as the digital guardian of your physical collections.

<div align="center">

[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
[![Min SDK](https://img.shields.io/badge/Min%20SDK-26-orange.svg)](https://developer.android.com/about/versions/oreo)
[![Target SDK](https://img.shields.io/badge/Target%20SDK-34-blue.svg)](https://developer.android.com/about/versions/14)
[![License](https://img.shields.io/badge/License-MIT-purple.svg)](LICENSE)

</div>

## ✨ Features

### Current Features (v1.0.0)
- 📚 **10 Collection Categories** with specialized fields
  - Books (Fiction, Non-Fiction, Manga, Art Books, Comics, Graphic Novels)
  - Figures & Statues (Scale Figures, Nendoroids, Prize Figures, Figma, Funko Pop)
  - Music (Vinyl Records, CDs, Cassettes)
  - Movies & TV (DVD, Blu-ray, 4K UHD, VHS)
  - Video Games (Games, Consoles, Accessories, Limited Editions)
  - Trading Cards (Pokémon, Magic: The Gathering, Yu-Gi-Oh!, Sports Cards, Other TCG)
  - Model Kits (Gunpla, Scale Models, Miniatures)
  - Magazines
  - Art & Prints (Posters, Limited Editions, Original Art, Doujinshi)
  - Custom (fully user-defined categories)

- 🎨 **Beautiful Theming System**
  - Dark mode (default)
  - Light mode
  - System default
  - 5 curated color themes: Monomori Default, Sakura, Matcha, Ocean, Sunset
  - Material You dynamic color support (Android 12+)

- 🏗️ **Robust Architecture**
  - MVVM (Model-View-ViewModel) pattern
  - Room database for local storage
  - Hilt dependency injection
  - Jetpack Compose UI with Material 3

- 🌸 **Japanese-Inspired Design**
  - Clean whitespace (Ma - 間)
  - Smooth, intentional animations
  - Typography optimized for both English and Japanese

### Planned Features
- 🔍 Advanced search and filtering
- 📊 Statistics and collection analytics
- 📸 Barcode scanning for easy item entry
- 🌐 Cloud backup and sync
- 📤 Export/Import (JSON, CSV)
- 🏷️ Custom tags and labels
- 📈 Price tracking and valuation
- 🎯 Wishlist management
- 🔔 Release date reminders
- 🤝 Collection sharing

## 🎨 Collection Categories

Each category comes with extensive default fields tailored to that collection type:

### Books
Title, Author(s), Publisher, ISBN, Page count, Genre, Series, Volume #, Language, Japanese title, Release date, Purchase info, Condition, Synopsis, and more.

### Figures & Statues
Character, Series/Anime, Manufacturer, Scale, Sculptor, Release date, Retailer, Box/Figure condition, Display location, MFC URL, and more.

### Music
Album title, Artist(s), Label, Catalog number, Format details, Genre, Country of release, Media/Sleeve condition, Discogs URL, Tracklist, and more.

### And 7 more specialized categories...

Plus a **Custom** category where you can define your own fields for any collection type!

## 🏗️ Tech Stack

- **Language:** Kotlin
- **Min SDK:** 26 (Android 8.0 Oreo)
- **Target SDK:** 34 (Android 14)
- **Architecture:** MVVM (Model-View-ViewModel)
- **Database:** Room (SQLite)
- **Dependency Injection:** Hilt
- **UI Framework:** Jetpack Compose
- **Design System:** Material 3 (Material You)
- **Build System:** Gradle with Kotlin DSL

### Key Libraries
- **AndroidX Core KTX** - Kotlin extensions for Android
- **Jetpack Compose** - Modern declarative UI
- **Material 3** - Latest Material Design components
- **Room** - Local database with type-safe SQL
- **Hilt** - Dependency injection
- **Navigation Compose** - Type-safe navigation
- **Coil** - Image loading
- **DataStore** - Preferences storage
- **Gson** - JSON serialization for custom fields

## 📁 Project Structure

```
com.monomori/
├── data/
│   ├── local/
│   │   ├── dao/              # Data Access Objects
│   │   ├── database/         # Room database
│   │   └── entity/           # Database entities (10 collection types)
│   ├── repository/           # Repository pattern
│   └── model/                # Domain models and enums
├── di/                       # Hilt dependency injection modules
├── ui/
│   ├── theme/               # Material 3 theming (5 color schemes)
│   ├── navigation/          # Navigation graph
│   ├── screens/             # Screen composables
│   │   ├── home/
│   │   ├── collection/
│   │   ├── item/
│   │   └── settings/
│   └── components/          # Reusable UI components
├── util/                    # Utility classes
└── MonomoriApplication.kt   # Application class
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or later
- Android SDK with minimum API 26

### Building the Project

1. **Clone the repository**
   ```bash
   git clone https://github.com/wasabimayonnaise/monomori.git
   cd monomori
   ```

2. **Open in Android Studio**
   - File → Open → Select the `monomori` directory
   - Wait for Gradle sync to complete

3. **Run the app**
   - Connect an Android device or start an emulator (API 26+)
   - Click Run (▶️) or press `Shift + F10`

### Building from Command Line

```bash
# Debug build
./gradlew assembleDebug

# Release build (requires signing configuration)
./gradlew assembleRelease

# Run tests
./gradlew test

# Install on connected device
./gradlew installDebug
```

## 🗄️ Database Schema

monomori uses Room for local data persistence with these key features:

- **10 Entity types** - One for each collection category
- **Type converters** - For dates, lists, enums, and custom fields
- **Migration support** - Schema versioning for future updates
- **Flexible custom fields** - JSON storage for user-defined fields

### Custom Fields System

Users can add custom fields to any item with support for:
- Text (single/multi-line)
- Numbers & Decimals
- Dates
- Booleans
- Ratings
- Single/Multi Select
- URLs
- Images

## 🎨 Theming

monomori features a sophisticated theming system with:

### Color Themes
1. **Monomori Default** - Sophisticated indigo/purple (default)
2. **Sakura** - Soft pinks inspired by cherry blossoms
3. **Matcha** - Earthy greens and creams
4. **Ocean** - Deep blues and teals
5. **Sunset** - Warm oranges and purples

### Theme Modes
- **Dark** - Easy on the eyes (default)
- **Light** - Bright and clean
- **System** - Follows device preference

### Material You
On Android 12+, monomori supports dynamic color theming that adapts to your wallpaper.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

### Development Guidelines
1. Follow the existing code style and architecture
2. Write clear commit messages
3. Add comments for complex logic
4. Test your changes thoroughly
5. Update documentation as needed

### Areas for Contribution
- 🐛 Bug fixes
- ✨ New features
- 🎨 UI/UX improvements
- 🌍 Translations
- 📝 Documentation
- 🧪 Tests

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Inspired by collection management apps like MyFigureCollection, Discogs, and Goodreads
- Material Design 3 guidelines by Google
- Japanese design principles: Ma (間), Kanso (簡素), Shizen (自然)

## 📧 Contact

For questions, suggestions, or feedback, please open an issue on GitHub.

---

<div align="center">

Made with ❤️ for collectors everywhere

**物守り** - *Protecting what matters*

</div>
