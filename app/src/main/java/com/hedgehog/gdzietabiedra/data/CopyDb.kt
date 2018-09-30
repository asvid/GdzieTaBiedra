package com.hedgehog.gdzietabiedra.data

import android.content.Context
import com.hedgehog.gdzietabiedra.R
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

object CopyDb {

  fun initRealmDb(context: Context) {
    copyBundledRealmFile(context.resources.openRawResource(R.raw.default1), "default1.realm",
        context)
  }

  private fun copyBundledRealmFile(inputStream: InputStream, outFileName: String,
      context: Context): String? {
    try {
      val file = File(context.filesDir, outFileName)
      if (file.exists()) return null
      val outputStream = FileOutputStream(file)
      val buf = ByteArray(1024)
      var bytesRead = inputStream.read(buf)
      while (bytesRead > 0) {
        outputStream.write(buf, 0, bytesRead)
        bytesRead = inputStream.read(buf)
      }
      outputStream.close()
      return file.absolutePath
    } catch (e: IOException) {
      e.printStackTrace()
      Timber.e(e)
    }
    return null
  }
}