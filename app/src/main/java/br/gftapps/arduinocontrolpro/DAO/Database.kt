package br.gftapps.arduinocontrolpro.DAO

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    companion object {
        private const val DATABASE_NAME = "commands.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME_TOGGLE = "botoes_alternado"
        private const val TABLE_NAME_BFUNCTION = "botoes_funcao"
    }

    override fun onCreate(p0: SQLiteDatabase?) {

        p0!!.execSQL(
            "create table if not exists $TABLE_NAME_TOGGLE (" +
                    "id integer primary key AUTOINCREMENT," +
                    "nome text," +
                    "dado_ligar text," +
                    "dado_desligar text)"
        )

        p0!!.execSQL("create table if not exists $TABLE_NAME_BFUNCTION(" +
                "id integer primary key AUTOINCREMENT," +
                "nome text," +
                "dado text)")

        var cv = ContentValues().apply {
            put("nome", "power")
            put("dado_ligar", 1)
            put("dado_desligar", 0)
        }
        p0!!.insert(TABLE_NAME_TOGGLE, null, cv)
        var i = 1
        while (i <= 12) {
            var cv2 = ContentValues().apply {
                put("nome", "F$i")
            }
            p0!!.insert(TABLE_NAME_BFUNCTION, null, cv2)
            i += 1
        }
    }


    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}