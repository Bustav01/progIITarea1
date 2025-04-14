package cl.dbustamante.android.progiitarea.models

class ItemMesa(
    private val itemMenu: ItemMenu,
    private val cantidad: Int
) {
    fun calcularSubtotal(): Int {
        return itemMenu.precio.toInt() * cantidad
    }
}