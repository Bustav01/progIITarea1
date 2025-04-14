package cl.dbustamante.android.progiitarea.controllers

class CuentaController {
    val cuentaMesa = CuentaMesa(mesa = 1)

    fun agregarItem(nombre: String, precio: String, cantidad: Int) {
        val itemMenu = ItemMenu(nombre, precio)
        cuentaMesa.agregarItem(itemMenu, cantidad)
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
}