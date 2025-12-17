package br.gftapps.arduinocontrolpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import br.gftapps.arduinocontrolpro.Model.BotaoFuncoesSQL
import br.gftapps.arduinocontrolpro.Model.BotaoPowerSQL

class ChangeCommandsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_commands)

        var pgb = findViewById<ProgressBar>(R.id.progressBar_display)
        pgb.visibility = View.INVISIBLE
        var bsave = findViewById<Button>(R.id.btn_save)
        var poweroff = findViewById<EditText>(R.id.edt_valueof)
        var poweron = findViewById<EditText>(R.id.edt_valueon)
        var edtnames = listOf<EditText>(
            findViewById(R.id.edt_name), findViewById(R.id.edt_name2), findViewById(R.id.edt_name3),
            findViewById(R.id.edt_name4), findViewById(R.id.edt_name5), findViewById(R.id.edt_name6),
            findViewById(R.id.edt_name7), findViewById(R.id.edt_name8), findViewById(R.id.edt_name9),
            findViewById(R.id.edt_name10), findViewById(R.id.edt_name11), findViewById(R.id.edt_name12)
        )

        var edtcommands = listOf<EditText>(
            findViewById(R.id.edt_command), findViewById(R.id.edt_command2), findViewById(R.id.edt_command3),
            findViewById(R.id.edt_command4), findViewById(R.id.edt_command5), findViewById(R.id.edt_command6),
            findViewById(R.id.edt_command7), findViewById(R.id.edt_command8), findViewById(R.id.edt_command9),
            findViewById(R.id.edt_command10), findViewById(R.id.edt_command11), findViewById(R.id.edt_command12)
        )
        var names = BotaoFuncoesSQL(
            applicationContext
        ).pegarNomes().split(";")

        var commands = BotaoFuncoesSQL(
            applicationContext
        ).pegarComando().split(";")

        var i = 0
        while (i < 12) {
            edtnames[i].setText(names[i])
            Log.d("OI", i.toString())
            if (!commands[0].equals("null")) {
                edtcommands[i].setText(commands[i])
            }
            i += 1
        }

        poweroff.setText(
            BotaoPowerSQL(
                applicationContext
            ).pegarComando().split(";")[1])
        poweron.setText(
            BotaoPowerSQL(
                applicationContext
            ).pegarComando().split(";")[0])

        bsave.setOnClickListener {
            pgb.visibility = View.VISIBLE
            var name = mutableListOf(
                edtnames[0].text.toString(), edtnames[1].text.toString(), edtnames[2].text.toString(),
                edtnames[3].text.toString(), edtnames[4].text.toString(), edtnames[5].text.toString(),
                edtnames[6].text.toString(), edtnames[7].text.toString(), edtnames[8].text.toString(),
                edtnames[9].text.toString(), edtnames[10].text.toString(), edtnames[11].text.toString()
            )

            var command = listOf(
                edtcommands[0].text.toString(), edtcommands[1].text.toString(), edtcommands[2].text.toString(),
                edtcommands[3].text.toString(), edtcommands[4].text.toString(), edtcommands[5].text.toString(),
                edtcommands[6].text.toString(), edtcommands[7].text.toString(), edtcommands[8].text.toString(),
                edtcommands[9].text.toString(), edtcommands[10].text.toString(), edtcommands[11].text.toString()
            )

            var j = 0
            while (j < 12) {
                if (name[j] != "") {
                    Log.d("WHILE", "IF")
                    BotaoFuncoesSQL(
                        applicationContext
                    )
                        .atualizarComando(j+1, name[j], command[j])
                } else {
                    Log.d("WHILE", "ELSE")
                    var t = j + 1
                    name[j] = "F$t"
                    BotaoFuncoesSQL(
                        applicationContext
                    )
                        .atualizarComando(j+1, name[j], command[j])
                }
                j += 1
            }

            BotaoPowerSQL(applicationContext)
                .atualizarComando(poweron.text.toString(), poweroff.text.toString())

            finish()
        }

    }
}
