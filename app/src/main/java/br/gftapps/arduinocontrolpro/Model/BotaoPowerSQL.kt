package br.gftapps.arduinocontrolpro.Model

import android.content.ContentValues
import android.content.Context
import br.gftapps.arduinocontrolpro.DAO.Database
import java.lang.StringBuilder
import java.sql.SQLException

class BotaoPowerSQL(context: Context) {
    private val con = context
    private val TABLE_NAME = "botoes_alternado"

    fun atualizarComando(d1: String, d2: String): Boolean {
        return try {
            var cv = ContentValues().apply {
                put("dado_ligar", d1)
                put("dado_desligar", d2)
            }
            Database(con).writableDatabase.update(TABLE_NAME, cv, null, null)
            true
        } catch (e: SQLException) {
            false
        }
    }

    fun pegarComando(): String {
        var sb = StringBuilder()
        var cursor = Database(con).readableDatabase.rawQuery("select * from $TABLE_NAME", null)
        while (cursor.moveToNext()) {
            sb.append(cursor.getString(2)).append(";").append(cursor.getString(3)).append(";")
        }
        cursor.close()
        return sb.toString()
    }
}