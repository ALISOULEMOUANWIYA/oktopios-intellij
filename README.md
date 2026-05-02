# Oktopios Language Support for JetBrains IDEs

Oktopios Language Support adds basic `.okp` language integration to JetBrains IDEs such as IntelliJ IDEA.

It is intended for developers who write or experiment with the Oktopios programming language and want a smoother editing experience inside JetBrains tools.

## Features

- `.okp` file type registration
- Oktopios language registration
- Syntax highlighting
- Line and block comments
- Brace, parenthesis, and bracket matching
- Color settings page
- Basic run action for the current Oktopios file
- Run configuration type for Oktopios files

## Requirements

- IntelliJ IDEA Community or another compatible JetBrains IDE
- Java 17
- Gradle wrapper included in this repository
- Optional: `okp` available in your system PATH to run Oktopios files from the IDE

The plugin currently targets IntelliJ Platform builds starting from `233`.

## Build

From this directory, run:

```bash
./gradlew buildPlugin
```

On Windows:

```powershell
.\gradlew.bat buildPlugin
```

The installable plugin ZIP will be generated at:

```text
build/distributions/oktopios-language-support-1.0.0.zip
```

## Run in a Test IDE

To launch a sandbox IntelliJ IDEA instance with the plugin installed:

```bash
./gradlew runIde
```

On Windows:

```powershell
.\gradlew.bat runIde
```

## Install from Disk

1. Build the plugin with `buildPlugin`.
2. Open IntelliJ IDEA.
3. Go to `Settings` -> `Plugins`.
4. Click the gear icon.
5. Choose `Install Plugin from Disk...`.
6. Select `build/distributions/oktopios-language-support-1.0.0.zip`.
7. Restart the IDE if requested.

## Publish to JetBrains Marketplace

1. Build the plugin:

```powershell
.\gradlew.bat buildPlugin
```

2. Open JetBrains Marketplace:

```text
https://plugins.jetbrains.com/
```

3. Sign in with a JetBrains account.
4. Create or select the vendor profile.
5. Upload the generated ZIP from `build/distributions`.
6. Fill in the plugin description, tags, license, and source code URL.
7. Submit the plugin for JetBrains review.

For future releases, update the plugin version in:

- `build.gradle.kts`
- `src/main/resources/META-INF/plugin.xml`

Then rebuild and upload the new ZIP.

## Project Structure

```text
src/main/kotlin/com/oktopios/intellij/        Plugin source code
src/main/kotlin/com/oktopios/intellij/run/    Run configuration support
src/main/kotlin/com/oktopios/intellij/actions/ Run menu actions
src/main/kotlin/com/oktopios/intellij/highlight/ Syntax highlighting
src/main/kotlin/com/oktopios/intellij/lexer/  Lexer support
src/main/resources/META-INF/plugin.xml        JetBrains plugin descriptor
src/main/resources/icons/                     Plugin icons
gradle/                                       Gradle wrapper files
```

## Development Notes

Generated lexer and parser files are produced with Grammar-Kit tasks. The Gradle build is configured so generation runs before Kotlin and Java compilation.

Some Java files are currently stored under `src/main/kotlin`, so the Gradle source set explicitly includes that directory for Java compilation.

## Troubleshooting

### `ClassNotFoundException` when opening the IDE

Make sure the plugin was rebuilt after changing source files:

```powershell
.\gradlew.bat clean buildPlugin
```

Then reinstall the newly generated ZIP.

### `okp` cannot be launched

The run action expects the `okp` executable to be available in your system PATH. Install Oktopios or add the executable directory to PATH before running `.okp` files from the IDE.

### WSL errors in IntelliJ logs

WSL errors are usually unrelated to this plugin. They can appear if IntelliJ detects a broken WSL distribution on Windows. Fix or remove the broken WSL distribution from Windows settings if needed.

## Author

ALI SOULE MOUANWIYA

## License

This project is open source, but the author keeps the project license and ownership. Add the full license text in a LICENSE file before accepting external contributions or publishing formal releases.