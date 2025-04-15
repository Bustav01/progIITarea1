package cl.dbustamante.android.progiitarea.controllers
import cl.dbustamante.android.progiitarea.models.CuentaMesa
import cl.dbustamante.android.progiitarea.models.ItemMenu


class CuentaController {
    val cuentaMesa = CuentaMesa(mesa = 1)

    fun agregarItem(nombre: String, precio: String, cantidad: Int) {
        val itemMenu = ItemMenu(nombre, precio)
        cuentaMesa.reemplazarItem(itemMenu, cantidad)
    }

    fun obtenerTotalSinPropina(): Int {
        return cuentaMesa.calcularTotalSinPropina()
    }

    fun obtenerPropina(): Int {
        return cuentaMesa.calcularPropina()
    }

    fun obtenerTotalConPropina(): Int {
        return cuentaMesa.calcularTotalConPropina()
    }

    fun setPropinaActiva(valor: Boolean) {
        cuentaMesa.activarPropina(valor)
    }

    fun obtenerSubtotal(nombre: String): Int {
        return cuentaMesa.itemsPublicos
            .filter { it.itemMenu.nombre == nombre }
            .sumOf { it.calcularSubtotal() }
    }

}