package br.gftapps.arduinocontrolpro.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import br.gftapps.arduinocontrolpro.ManageSettings
import br.gftapps.arduinocontrolpro.MyInterface
import br.gftapps.arduinocontrolpro.R
import br.gftapps.arduinocontrolpro.SocketConnection

class ChangeAddressActivity : AppCompatActivity(), MyInterface {
    private var progress: ProgressBar? = null
    private var address: EditText? = null
    private var port: EditText? = null
    override fun VerifySocket(b: Boolean) {
        if (b) {
            if (ManageSettings().saveFile(
                    "addressConf.cfg",
                    address!!.text.toString() + "\n" + port!!.text.toString(),
                    applicationContext
                )
            ) {
                Toast.makeText(
                    applicationContext,
                    R.string.a1,
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        } else {
            progress!!.visibility = View.INVISIBLE
            Toast.makeText(
                applicationContext,
                R.string.a3,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_address)
        var b = findViewById<Button>(R.id.btn_test_save)
        progress = findViewById<ProgressBar>(R.id.pb_connect_save)
        address = findViewById<EditText>(R.id.edt_address)
        port = findViewById<EditText>(R.id.edt_port)
        progress!!.visibility = View.INVISIBLE
        var data: List<String> = ManageSettings()
            .readFile("addressConf.cfg", applicationContext).split(";")
        if (!data[0].equals("null")) {
            address!!.setText(data[0])
            port!!.setText(data[1])
        }

        b.setOnClickListener {
            if (address!!.text.toString().isNotEmpty() and port!!.text.toString().isNotEmpty()) {
                progress!!.visibility = View.VISIBLE
                var socketConnection = SocketConnection()
                socketConnection.Parameters(this)
                socketConnection.execute("TestConnection", address!!.text.toString(), port!!.text.toString())
            } else {
                Toast.makeText(applicationContext, getString(R.string.a2), Toast.LENGTH_LONG).show()
            }
        }
    }
}
