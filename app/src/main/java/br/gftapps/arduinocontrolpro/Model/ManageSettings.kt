package br.gftapps.arduinocontrolpro

import android.content.Context
import android.widget.Toast
import java.io.*
import java.lang.StringBuilder

class ManageSettings {

    fun saveFile(fileName: String, data: String, c: Context) : Boolean {
        var f: FileOutputStream? = null
        return try {
            f = c.openFileOutput(fileName, Context.MODE_PRIVATE)
            f.write(data.toByteArray())

            true
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            false
        } catch (e: IOException) {
            e.printStackTrace()
            false
        } finally {
            f?.close()
        }
    }

    fun readFile(fileName: String, c : Context) : String {
        var fis: FileInputStream
        var br: BufferedReader
        var sb: StringBuilder? = null
        try {

            fis = c.openFileInput(fileName)
            var isr = InputStreamReader(fis)
           br = BufferedReader(isr)
            sb = StringBuilder()
            var text = br.readLine()

            while (text != null) {
                sb.append(text).append(";")
                text = br.readLine()
            }
            fis.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return sb.toString()
    }
}