package br.gftapps.arduinocontrolpro.Model

import android.content.ContentValues
import android.content.Context
import br.gftapps.arduinocontrolpro.DAO.Database
import java.lang.StringBuilder
import java.sql.SQLException

class BotaoFuncoesSQL(context: Context) {

    private val con = context
    private val TABLE_NAME = "botoes_funcao"

    fun atualizarComando(i: Int, n: String, d: String): Boolean {
        return try {
            var cv = ContentValues().apply {
                put("nome", n)
                put("dado", d)
            }
            Database(con).writableDatabase.update(TABLE_NAME, cv, "id = $i", null)
            true
        } catch (e: SQLException) {
            false
        }
    }

    fun pegarComando(): String {
        var sb = StringBuilder()
        var cursor = Database(con).readableDatabase.rawQuery("select * from $TABLE_NAME", null)
        while (cursor.moveToNext()) {
            sb.append(cursor.getString(2)).append(";")
        }
        cursor.close()
        return sb.toString()
    }

    fun pegarNomes(): String {
        var sb = StringBuilder()
        var cursor = Database(con).readableDatabase.rawQuery("select * from $TABLE_NAME", null)
        while (cursor.moveToNext()) {
            sb.append(cursor.getString(1)).append(";")
        }
        cursor.close()
        return sb.toString()
    }
}