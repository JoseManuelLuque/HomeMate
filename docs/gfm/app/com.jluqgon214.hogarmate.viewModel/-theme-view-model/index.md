//[app](../../../index.md)/[com.jluqgon214.hogarmate.viewModel](../index.md)/[ThemeViewModel](index.md)

# ThemeViewModel

class [ThemeViewModel](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

# ViewModel para gestionar el estado del tema de la aplicación (modo claro/oscuro).

#### Parameters

androidJvm

| | |
|---|---|
| context | El contexto de la aplicación necesario para acceder a las preferencias. |

## Constructors

| | |
|---|---|
| [ThemeViewModel](-theme-view-model.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Crea una instancia de ThemeViewModel con el contexto proporcionado. |

## Properties

| Name | Summary |
|---|---|
| [isDarkTheme](is-dark-theme.md) | [androidJvm]<br>val [isDarkTheme](is-dark-theme.md): StateFlow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt;<br>Flujo de estado que expone si el tema oscuro está activado. |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](index.md#383812252%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](index.md#383812252%2FFunctions%2F-912451524)(closeable: [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html))<br>fun [addCloseable](index.md#1722490497%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), closeable: [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html)) |
| [getCloseable](index.md#1102255800%2FFunctions%2F-912451524) | [androidJvm]<br>fun &lt;[T](index.md#1102255800%2FFunctions%2F-912451524) : [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html)&gt; [getCloseable](index.md#1102255800%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [T](index.md#1102255800%2FFunctions%2F-912451524)? |
| [toggleTheme](toggle-theme.md) | [androidJvm]<br>fun [toggleTheme](toggle-theme.md)()<br>Actualiza el estado interno y guarda la preferencia en almacenamiento persistente. |
