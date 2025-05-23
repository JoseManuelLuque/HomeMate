//[app](../../../index.md)/[com.jluqgon214.hogarmate.viewModel](../index.md)/[AppViewModel](index.md)

# AppViewModel

[androidJvm]\
class [AppViewModel](index.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

# ViewModel para gestionar el estado de la aplicación.

Proporciona flujos de estado para elementos de la interfaz de usuario como el texto del encabezado, la visibilidad del FAB (Floating Action Button), la barra inferior y el índice seleccionado de la barra inferior.

## Constructors

| | |
|---|---|
| [AppViewModel](-app-view-model.md) | [androidJvm]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [selectedBottomBarIndex](selected-bottom-bar-index.md) | [androidJvm]<br>val [selectedBottomBarIndex](selected-bottom-bar-index.md): StateFlow&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)&gt;<br>Flujo de estado que expone el índice seleccionado de la barra inferior. |
| [showBottomBar](show-bottom-bar.md) | [androidJvm]<br>val [showBottomBar](show-bottom-bar.md): StateFlow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt;<br>Flujo de estado que indica si la barra inferior debe mostrarse. |
| [showFAB](show-f-a-b.md) | [androidJvm]<br>val [showFAB](show-f-a-b.md): StateFlow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt;<br>Flujo de estado que indica si el FAB debe mostrarse. |
| [textoTop](texto-top.md) | [androidJvm]<br>val [textoTop](texto-top.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt;<br>Flujo de estado que expone el texto del encabezado superior. |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-theme-view-model/index.md#383812252%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-theme-view-model/index.md#383812252%2FFunctions%2F-912451524)(closeable: [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html))<br>fun [addCloseable](../-theme-view-model/index.md#1722490497%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), closeable: [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html)) |
| [getCloseable](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524) | [androidJvm]<br>fun &lt;[T](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524) : [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html)&gt; [getCloseable](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [T](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524)? |
| [setSelectedBottomBarIndex](set-selected-bottom-bar-index.md) | [androidJvm]<br>fun [setSelectedBottomBarIndex](set-selected-bottom-bar-index.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html))<br>Actualiza el índice seleccionado de la barra inferior. |
| [setShowBottomBar](set-show-bottom-bar.md) | [androidJvm]<br>fun [setShowBottomBar](set-show-bottom-bar.md)(show: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html))<br>Actualiza la visibilidad de la barra inferior. |
| [setShowFAB](set-show-f-a-b.md) | [androidJvm]<br>fun [setShowFAB](set-show-f-a-b.md)(show: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html))<br>Actualiza la visibilidad del FAB. |
| [setTextoTop](set-texto-top.md) | [androidJvm]<br>fun [setTextoTop](set-texto-top.md)(texto: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))<br>Actualiza el texto del encabezado superior. |
