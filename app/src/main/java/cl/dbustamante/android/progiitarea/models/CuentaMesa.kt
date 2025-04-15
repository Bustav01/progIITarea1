package cl.dbustamante.android.progiitarea.models

class CuentaMesa (
    val mesa: Int,
    private var aceptaPropina: Boolean = true
){
    private val items: MutableList<ItemMesa> = mutableListOf()

    fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {
        val itemMesa = ItemMesa(itemMenu, cantidad)
        items.add(itemMesa)
    }

    fun agregarItem(itemMesa: ItemMesa) {
        items.add(itemMesa)
    }

    fun calcularTotalSinPropina(): Int {
        return items.sumOf { it.calcularSubtotal() }
    }

    fun calcularPropina(): Int {
        return if (aceptaPropina) (calcularTotalSinPropina() * 0.1).toInt() else 0
    }

    fun calcularTotalConPropina(): Int {
        return calcularTotalSinPropina() + calcularPropina()
    }
    fun activarPropina(valor: Boolean) {
        aceptaPropina = valor
    }
}