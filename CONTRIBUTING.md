# Contributing to monomori (物守り)

Thank you for your interest in contributing to monomori! We welcome contributions from the community.

## How to Contribute

### Reporting Bugs

If you find a bug, please open an issue with:
- A clear description of the problem
- Steps to reproduce the issue
- Expected vs actual behavior
- Screenshots if applicable
- Device/Android version information

### Suggesting Features

We love hearing new ideas! Please open an issue with:
- A clear description of the feature
- Use cases and benefits
- Any relevant examples or mockups

### Pull Requests

1. **Fork the repository** and create your branch from `main`
2. **Make your changes** following our coding standards
3. **Test thoroughly** on multiple device configurations if possible
4. **Update documentation** if you've changed APIs or added features
5. **Write clear commit messages** describing what and why
6. **Submit a pull request** with a clear description of your changes

## Development Setup

See the [README.md](README.md) for detailed setup instructions.

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or later
- Android SDK with minimum API 26

### Building
```bash
./gradlew assembleDebug
```

### Running Tests
```bash
./gradlew test
```

## Coding Standards

### Kotlin Style
- Follow the [official Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Keep functions small and focused
- Add comments for complex logic

### Architecture
- Follow MVVM pattern
- Use Hilt for dependency injection
- Keep UI logic in ViewModels
- Use Compose best practices

### Commit Messages
- Use present tense ("Add feature" not "Added feature")
- Use imperative mood ("Move cursor to..." not "Moves cursor to...")
- Keep first line under 50 characters
- Provide detailed description if needed

### Code Review
All submissions require review before merging. We aim to:
- Review PRs within a few days
- Provide constructive feedback
- Maintain code quality and consistency

## Areas to Contribute

- 🐛 **Bug Fixes** - Help squash bugs
- ✨ **Features** - Implement new collection types or functionality
- 🎨 **UI/UX** - Improve design and user experience
- 🌍 **Translations** - Add support for more languages
- 📝 **Documentation** - Improve README, comments, or guides
- 🧪 **Testing** - Add or improve tests

## Code of Conduct

### Our Standards
- Be respectful and inclusive
- Welcome newcomers
- Accept constructive criticism gracefully
- Focus on what's best for the community

### Unacceptable Behavior
- Harassment or discriminatory language
- Trolling or insulting comments
- Personal or political attacks
- Publishing private information

## Questions?

Feel free to open an issue for any questions about contributing!

## License

By contributing, you agree that your contributions will be licensed under the MIT License.
