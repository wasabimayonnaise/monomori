# monomori Architecture Documentation

## Overview

monomori (物守り) follows a clean MVVM (Model-View-ViewModel) architecture with clear separation of concerns. The app is built using Jetpack Compose for UI, Room for local persistence, and Hilt for dependency injection.

## Architecture Layers

```
┌─────────────────────────────────────────────┐
│              Presentation Layer              │
│  (Compose UI, ViewModels, Navigation)       │
└─────────────────────────────────────────────┘
                     ↓
┌─────────────────────────────────────────────┐
│              Domain Layer                    │
│  (Use Cases, Business Logic)                │
└─────────────────────────────────────────────┘
                     ↓
┌─────────────────────────────────────────────┐
│              Data Layer                      │
│  (Repository, Room Database, DAOs)          │
└─────────────────────────────────────────────┘
```

## Package Structure

### `com.monomori`
Root package containing the Application class and MainActivity.

- **MonomoriApplication.kt** - Application class with Hilt initialization
- **MainActivity.kt** - Single Activity hosting all Compose screens

### `com.monomori.data`
Contains all data-related code.

#### `data.local`
Local data storage using Room database.

##### `data.local.entity`
Room entities representing database tables:
- **BookEntity** - Books collection (fiction, manga, art books, etc.)
- **FigureEntity** - Figures & statues (scale figures, nendoroids, etc.)
- **MusicEntity** - Music collection (vinyl, CD, cassette)
- **MovieEntity** - Movies & TV (DVD, Blu-ray, 4K UHD, VHS)
- **VideoGameEntity** - Video games (games, consoles, accessories)
- **TradingCardEntity** - Trading cards (Pokémon, MTG, Yu-Gi-Oh!, etc.)
- **ModelKitEntity** - Model kits (Gunpla, scale models, miniatures)
- **MagazineEntity** - Magazine collection
- **ArtPrintEntity** - Art & prints (posters, limited editions, doujinshi)
- **CustomItemEntity** - User-defined custom collections

Each entity includes:
- Common base fields (id, category, images, dates, tags, barcode)
- Category-specific fields optimized for that collection type
- Support for custom user-defined fields via JSON storage

##### `data.local.dao`
Data Access Objects providing database operations:
- One DAO per entity type
- Standard CRUD operations (insert, update, delete)
- Queries using Flow for reactive data
- Search functionality
- Count queries for statistics

##### `data.local.database`
Database configuration:
- **MonomoriDatabase** - Room database class with all entities
- **Converters** - Type converters for complex types (dates, lists, enums, custom fields)

#### `data.repository`
Repository pattern implementations (to be implemented):
- Abstract data sources
- Handle data operations and caching
- Provide clean API to ViewModels

#### `data.model`
Domain models and enums:
- **Enums.kt** - All enum types (categories, subcategories, conditions, etc.)
- **CustomField.kt** - Custom field definitions and data structures

### `com.monomori.di`
Dependency injection modules using Hilt.

- **DatabaseModule** - Provides Room database and DAO instances

### `com.monomori.ui`
All UI-related code.

#### `ui.theme`
Material 3 theming system:
- **Color.kt** - Color definitions for all themes (5 theme variants)
- **Type.kt** - Typography system
- **Theme.kt** - Main theme composable with mode and color theme selection

Supported themes:
1. **Monomori Default** - Sophisticated indigo/purple
2. **Sakura** - Soft pinks inspired by cherry blossoms  
3. **Matcha** - Earthy greens and creams
4. **Ocean** - Deep blues and teals
5. **Sunset** - Warm oranges and purples
6. **Dynamic** - Material You (Android 12+)

#### `ui.navigation`
Navigation setup:
- **Screen.kt** - Sealed class defining all navigation routes
- **MonomoriNavigation.kt** - NavHost with navigation graph

#### `ui.screens`
Individual screen composables:
- **home/** - Home screen showing collection overview
- **settings/** - Settings screen for app configuration
- **collection/** - (Future) Collection list view
- **item/** - (Future) Item detail view

#### `ui.components`
Reusable UI components (to be implemented):
- Common cards, buttons, dialogs
- Custom collection-specific components

### `com.monomori.util`
Utility classes and extensions (to be implemented).

## Data Flow

### Reading Data
```
UI Screen → ViewModel → Repository → DAO → Room Database
                ↓
            Flow<Data>
                ↓
        StateFlow/State
                ↓
            UI Updates
```

### Writing Data
```
UI Action → ViewModel → Repository → DAO → Room Database
              ↓
         Update State
              ↓
          UI Reflects
```

## Key Technologies

### Room Database
- **Version**: 1 (initial schema)
- **Strategy**: Fallback to destructive migration (development phase)
- **Future**: Implement proper migrations for production

### Type Converters
Support for:
- Date ↔ Long (timestamp)
- List<String> ↔ JSON string
- Enums ↔ String
- CustomFieldsData ↔ JSON string

### Custom Fields System
Flexible field storage allowing users to add:
- Text fields (single/multi-line)
- Numbers and decimals
- Dates
- Booleans
- Ratings
- Select options (single/multi)
- URLs
- Images

## Design Principles

### Japanese-Inspired Design (和)
- **Ma (間)** - Emphasis on negative space and clean layouts
- **Kanso (簡素)** - Simplicity and elimination of clutter
- **Shizen (自然)** - Natural, unforced design

### Material Design 3
- Dynamic color theming
- Adaptive layouts
- Smooth animations
- Consistent elevation and surfaces

## Future Architecture Enhancements

### Planned Improvements
1. **Use Cases Layer** - Separate business logic from ViewModels
2. **Repository Implementations** - Complete repository pattern
3. **ViewModel Layer** - Add ViewModels for all screens
4. **DataStore** - Preferences management for theme and settings
5. **Coroutines** - Enhanced async operations
6. **Unit Tests** - Comprehensive test coverage
7. **Cloud Sync** - Optional backend integration

### Scalability Considerations
- Modularization (feature modules)
- Multi-module architecture
- Shared UI components library
- Common utilities module

## Dependencies

### Core
- Kotlin 1.9.20
- Gradle 8.2
- Android Gradle Plugin 8.1.4

### AndroidX
- Core KTX 1.12.0
- Lifecycle Runtime 2.6.2
- Activity Compose 1.8.1

### Compose
- Compose BOM 2023.10.01
- Material 3
- Navigation Compose 2.7.5

### Data
- Room 2.6.1
- DataStore 1.0.0
- Gson 2.10.1

### DI
- Hilt 2.48.1

### Image Loading
- Coil 2.5.0

## Testing Strategy

### Unit Tests
- DAO tests with in-memory database
- ViewModel tests with fake repositories
- Use case tests

### Integration Tests
- Database migration tests
- End-to-end repository tests

### UI Tests
- Compose UI tests
- Navigation tests
- Accessibility tests

## Performance Considerations

### Database
- Indexed columns for frequent queries
- Pagination for large collections
- Background thread for all DB operations

### UI
- LazyColumn/LazyGrid for efficient lists
- State hoisting
- Remember and derivedStateOf for performance
- Avoid unnecessary recompositions

### Memory
- Image loading with Coil (automatic caching)
- Proper lifecycle management
- No memory leaks in ViewModels

## Accessibility

- Content descriptions for all images
- Semantic meaning for UI elements
- Support for TalkBack
- Proper focus management
- Sufficient color contrast

## Localization

Currently:
- English strings

Future:
- Japanese (日本語)
- Additional languages as contributed

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines on:
- Code style
- Architecture patterns
- Pull request process
- Testing requirements
