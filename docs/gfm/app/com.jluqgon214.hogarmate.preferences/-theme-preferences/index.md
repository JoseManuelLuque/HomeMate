//[app](../../../index.md)/[com.jluqgon214.hogarmate.preferences](../index.md)/[ThemePreferences](index.md)

# ThemePreferences

class [ThemePreferences](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))

# Clase que gestiona las preferencias relacionadas con el tema de la aplicación.

Utiliza DataStore para almacenar y recuperar la preferencia del tema (claro u oscuro).

#### Parameters

androidJvm

| | |
|---|---|
| context | Contexto de la aplicación necesario para acceder a DataStore. |

## Constructors

| | |
|---|---|
| [ThemePreferences](-theme-preferences.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Properties

| Name | Summary |
|---|---|
| [isDarkTheme](is-dark-theme.md) | [androidJvm]<br>val [isDarkTheme](is-dark-theme.md): Flow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt; |

## Functions

| Name | Summary |
|---|---|
| [saveTheme](save-theme.md) | [androidJvm]<br>suspend fun [saveTheme](save-theme.md)(isDark: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)) |
