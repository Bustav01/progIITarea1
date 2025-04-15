package cl.dbustamante.android.progiitarea


import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import android.widget.Switch
import android.widget.EditText
import android.widget.TextView
import java.util.Locale
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.dbustamante.android.progiitarea.controllers.CuentaController
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var  cuentaController: CuentaController
    private lateinit var textViewTotalSinPropina: TextView
    private lateinit var textViewPropina: TextView
    private lateinit var textViewTotalConPropina: TextView
    private lateinit var textViewSubtotalPastel: TextView
    private lateinit var textViewSubtotalCazuela: TextView
    private lateinit var textViewTotalPastelDeChoclo: TextView
    private lateinit var textViewTotalCazuela: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        cuentaController = CuentaController()
        val editTextPastelDeChoclo = findViewById<EditText>(R.id.editTextPastelDeChoclo)
        val editTextCazuela = findViewById<EditText>(R.id.editTextCazuela)

        textViewTotalSinPropina = findViewById(R.id.textViewTotalSinPropina)
        textViewPropina = findViewById(R.id.textViewPropina)
        textViewTotalConPropina = findViewById(R.id.textViewTotalConPropina)
        val switchPropina = findViewById<Switch>(R.id.switchPropina)
        textViewSubtotalPastel = findViewById(R.id.textViewTotalPastelDeChoclo)
        textViewSubtotalCazuela = findViewById(R.id.textViewTotalCazuela)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editTextPastelDeChoclo.addTextChangedListener { editable ->
            val cantidad = editable?.toString()?.toIntOrNull() ?: 0
            cuentaController.agregarItem("Pastel de Choclo", "12000", cantidad)
            actualizarTotales()
        }

        editTextCazuela.addTextChangedListener { editable ->
            val cantidad = editable?.toString()?.toIntOrNull() ?: 0
            cuentaController.agregarItem("Cazuela", "10000", cantidad)
            actualizarTotales()
        }

        switchPropina.setOnCheckedChangeListener { _, isChecked ->
            cuentaController.setPropinaActiva(isChecked)
            actualizarTotales()
        }
    }

    private fun formatearPesos(valor: Int): String {
        val formato = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        return formato.format(valor)
    }

    private fun actualizarTotales() {
        val totalSinPropina = cuentaController.obtenerTotalSinPropina()
        val propina = cuentaController.obtenerPropina()
        val totalConPropina = cuentaController.obtenerTotalConPropina()
        val subtotalPastel = cuentaController.obtenerSubtotal("Pastel de Choclo")
        val subtotalCazuela = cuentaController.obtenerSubtotal("Cazuela")

        textViewTotalSinPropina.text = formatearPesos(totalSinPropina)
        textViewPropina.text = formatearPesos(propina)
        textViewTotalConPropina.text = formatearPesos(totalConPropina)
        textViewTotalPastelDeChoclo.text = formatearPesos(subtotalPastel)
        textViewTotalCazuela.text = formatearPesos(subtotalCazuela)
    }
}