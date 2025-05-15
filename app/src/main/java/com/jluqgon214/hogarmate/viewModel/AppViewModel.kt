package com.jluqgon214.hogarmate.viewModel

    import androidx.lifecycle.ViewModel
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow

    /**
     * # ViewModel para gestionar el estado de la aplicación.
     * Proporciona flujos de estado para elementos de la interfaz de usuario como el texto del encabezado,
     * la visibilidad del FAB (Floating Action Button), la barra inferior y el índice seleccionado de la barra inferior.
     */
    class AppViewModel : ViewModel() {

        // Estado para el texto del encabezado superior
        private val _textoTop = MutableStateFlow("")
        /**
         * Flujo de estado que expone el texto del encabezado superior.
         */
        val textoTop: StateFlow<String> = _textoTop

        /**
         * Actualiza el texto del encabezado superior.
         * @param texto El nuevo texto a mostrar en el encabezado.
         */
        fun setTextoTop(texto: String) {
            _textoTop.value = texto
        }

        // Estado para la visibilidad del FAB (Floating Action Button)
        private val _showFAB = MutableStateFlow(false)
        /**
         * Flujo de estado que indica si el FAB debe mostrarse.
         */
        val showFAB: StateFlow<Boolean> = _showFAB

        /**
         * Actualiza la visibilidad del FAB.
         * @param show `true` para mostrar el FAB, `false` para ocultarlo.
         */
        fun setShowFAB(show: Boolean) {
            _showFAB.value = show
        }

        // Estado para la visibilidad de la barra inferior
        private val _showBottomBar = MutableStateFlow(false)
        /**
         * Flujo de estado que indica si la barra inferior debe mostrarse.
         */
        val showBottomBar: StateFlow<Boolean> = _showBottomBar

        /**
         * Actualiza la visibilidad de la barra inferior.
         * @param show `true` para mostrar la barra inferior, `false` para ocultarla.
         */
        fun setShowBottomBar(show: Boolean) {
            _showBottomBar.value = show
        }

        // Estado para el índice seleccionado de la barra inferior
        private val _selectedBottomBarIndex = MutableStateFlow(0)
        /**
         * Flujo de estado que expone el índice seleccionado de la barra inferior.
         */
        val selectedBottomBarIndex: StateFlow<Int> = _selectedBottomBarIndex

        /**
         * Actualiza el índice seleccionado de la barra inferior.
         * @param index El nuevo índice seleccionado.
         */
        fun setSelectedBottomBarIndex(index: Int) {
            _selectedBottomBarIndex.value = index
        }
    }