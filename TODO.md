# monomori Development TODO

## Phase 1: Core Functionality (Current)

### Data Layer ✅
- [x] Create all 10 collection type entities
- [x] Implement Room database with DAOs
- [x] Set up type converters
- [x] Configure Hilt dependency injection
- [x] Design custom fields system

### UI Foundation ✅
- [x] Set up Material 3 theming
- [x] Create 5 color theme variants
- [x] Implement dark/light/system modes
- [x] Build home screen with category grid
- [x] Build settings screen structure
- [x] Set up navigation graph

### Project Setup ✅
- [x] Configure Gradle and dependencies
- [x] Create comprehensive README
- [x] Add MIT License
- [x] Write CONTRIBUTING guide
- [x] Document architecture

## Phase 2: Basic CRUD Operations

### ViewModels & State Management
- [ ] Create HomeViewModel
  - [ ] Load collection counts
  - [ ] Aggregate statistics
  - [ ] Handle category navigation
- [ ] Create SettingsViewModel
  - [ ] Theme preference storage with DataStore
  - [ ] Color theme selection
  - [ ] Theme mode selection
- [ ] Create CollectionViewModel
  - [ ] Load items for category
  - [ ] Implement sorting
  - [ ] Implement filtering
- [ ] Create ItemDetailViewModel
  - [ ] Load single item
  - [ ] Handle edit mode
  - [ ] Delete item

### Repository Layer
- [ ] BookRepository
- [ ] FigureRepository
- [ ] MusicRepository
- [ ] MovieRepository
- [ ] VideoGameRepository
- [ ] TradingCardRepository
- [ ] ModelKitRepository
- [ ] MagazineRepository
- [ ] ArtPrintRepository
- [ ] CustomItemRepository

### UI Screens
- [ ] Collection List Screen
  - [ ] List view
  - [ ] Grid view toggle
  - [ ] Sort options
  - [ ] Filter options
  - [ ] Search within category
- [ ] Item Detail Screen
  - [ ] Display all fields
  - [ ] Image gallery
  - [ ] Edit button
  - [ ] Delete button
  - [ ] Share functionality
- [ ] Add/Edit Item Screen
  - [ ] Form for each field
  - [ ] Image picker
  - [ ] Date picker
  - [ ] Input validation
  - [ ] Save/Cancel actions
  - [ ] Category-specific fields

### Settings Implementation
- [ ] DataStore for preferences
- [ ] Theme switcher dialog
- [ ] Color theme selector
- [ ] Data management options

## Phase 3: Enhanced Features

### Search & Filter
- [ ] Global search across all collections
- [ ] Advanced filtering
  - [ ] By category
  - [ ] By date range
  - [ ] By price range
  - [ ] By condition
  - [ ] By tags
- [ ] Search suggestions
- [ ] Recent searches

### Statistics & Analytics
- [ ] Collection value calculator
- [ ] Collection growth chart
- [ ] Category breakdown chart
- [ ] Most expensive items
- [ ] Recent additions timeline
- [ ] Collection completion percentage

### Image Handling
- [ ] Camera integration
- [ ] Gallery picker
- [ ] Image cropping
- [ ] Multiple images per item
- [ ] Image compression
- [ ] Image caching with Coil

### Custom Fields UI
- [ ] Custom field editor
- [ ] Dynamic form generation
- [ ] Field type selection
- [ ] Field reordering
- [ ] Field deletion
- [ ] Template saving

## Phase 4: Advanced Features

### Barcode Scanning
- [ ] Integrate ML Kit Barcode Scanning
- [ ] Auto-populate fields from barcode
- [ ] Support multiple barcode types
- [ ] Manual barcode entry fallback

### Data Import/Export
- [ ] Export to JSON
- [ ] Export to CSV
- [ ] Import from JSON
- [ ] Import from CSV
- [ ] Backup to file
- [ ] Restore from backup

### Cloud Sync (Optional)
- [ ] Firebase integration
- [ ] User authentication
- [ ] Cloud backup
- [ ] Multi-device sync
- [ ] Conflict resolution

### Wishlist
- [ ] Separate wishlist table
- [ ] Wishlist screen
- [ ] Move between collection and wishlist
- [ ] Price tracking
- [ ] Purchase reminders

### Social Features (Optional)
- [ ] Share collection via link
- [ ] Public profile
- [ ] Follow other collectors
- [ ] Like and comment

### Notifications
- [ ] Release date reminders
- [ ] Price drop alerts (if tracking enabled)
- [ ] Backup reminders

## Phase 5: Polish & Optimization

### Testing
- [ ] Unit tests for DAOs
- [ ] Unit tests for ViewModels
- [ ] Unit tests for repositories
- [ ] Integration tests
- [ ] UI tests with Compose Testing
- [ ] Accessibility tests
- [ ] Performance tests

### Optimization
- [ ] Database query optimization
- [ ] Image loading optimization
- [ ] Pagination for large lists
- [ ] ProGuard/R8 rules refinement
- [ ] App size optimization
- [ ] Battery usage optimization

### UI/UX Polish
- [ ] Loading states
- [ ] Empty states with illustrations
- [ ] Error handling UI
- [ ] Smooth animations
- [ ] Haptic feedback
- [ ] Gesture navigation
- [ ] Tablet layout optimization

### Accessibility
- [ ] Content descriptions
- [ ] TalkBack support
- [ ] Screen reader testing
- [ ] Color contrast validation
- [ ] Font scaling support
- [ ] Touch target sizes

### Localization
- [ ] Japanese translations (日本語)
- [ ] Spanish translations
- [ ] French translations
- [ ] German translations
- [ ] Right-to-left (RTL) support

## Phase 6: Release Preparation

### App Store
- [ ] Create app screenshots
- [ ] Write app description
- [ ] Create promotional graphics
- [ ] Set up Google Play Console
- [ ] Configure signing keys
- [ ] Prepare release build

### Documentation
- [ ] User guide
- [ ] FAQ
- [ ] Privacy policy
- [ ] Terms of service
- [ ] Video tutorials

### Marketing
- [ ] Landing page
- [ ] Social media presence
- [ ] Press kit
- [ ] Beta testing program

## Future Ideas (Backlog)

### Advanced Features
- [ ] Price history tracking
- [ ] Market value estimation (API integration)
- [ ] Collection insurance calculator
- [ ] Loan tracking (items lent to friends)
- [ ] Collection location mapping
- [ ] 3D model viewer for figures
- [ ] AR view for display planning

### Integrations
- [ ] MyFigureCollection API
- [ ] Discogs API
- [ ] TMDB/IMDB API
- [ ] Goodreads API
- [ ] eBay/Amazon price checking
- [ ] Barcode lookup services

### Community
- [ ] Trading marketplace
- [ ] Collection showcase
- [ ] Forum/Discussion board
- [ ] Collection challenges
- [ ] Leaderboards

### Widgets
- [ ] Home screen widgets
- [ ] Quick add widget
- [ ] Collection stats widget
- [ ] Random item widget

### Wear OS
- [ ] Wear OS companion app
- [ ] Quick collection view
- [ ] Barcode scanning on watch
- [ ] Voice commands

## Known Issues

### Current
- [ ] None yet (initial setup)

### To Investigate
- [ ] Performance with 10,000+ items
- [ ] Image storage strategy
- [ ] Backup file size limits
- [ ] Network timeouts for API calls

## Questions to Resolve

- [ ] Should we support multiple users per device?
- [ ] Should we implement collection sharing directly or via export?
- [ ] What's the maximum number of images per item?
- [ ] Should custom fields be category-specific or global?
- [ ] Do we need offline mode for cloud sync?

## Notes

- Focus on core functionality before advanced features
- Maintain clean architecture throughout
- Write tests for critical paths
- Keep user privacy as top priority
- Performance matters - test with large datasets
- Accessibility is not optional

---

**Last Updated:** Initial setup
**Current Phase:** Phase 1 (Complete)
**Next Milestone:** Phase 2 - Basic CRUD Operations
