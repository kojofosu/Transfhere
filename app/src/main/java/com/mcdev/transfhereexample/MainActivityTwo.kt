package com.mcdev.transfhereexample

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mcdev.transfhere.Copy
import com.mcdev.transfhere.utils.TransfhereUtils
import kotlinx.android.synthetic.main.activity_main_two.*
import java.io.File


class MainActivityTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_two)

        val uri = intent.data

        image_iv.setImageURI(uri)

        move_btn.setOnClickListener {
            moveFiles(uri!!)
        }
    }

    fun moveFiles(uri: Uri) {

        var aaa = TransfhereUtils.createExternalStorageDir("testTestTwo")
        Log.d("TAG", "moveFiles: aaa2 : ${aaa.path}")

        var ppp = getRealPathFromURI(uri)
        Log.d("TAG", "moveFiles: the name:  $ppp")


        var lll = getPath(this@MainActivityTwo, uri)
        Log.d("TAG", "moveFiles: the path : $lll")


        Copy.Builder(this)
            .fileNameWithExtension(ppp!!)
            .sourcePath(sourcePath = lll!!)
            .destinationPath(destinationPath = aaa.path)
            .createDestinationDirIfNotExist()
            .build()
    }

    private fun getRealPathFromURI(contentURI: Uri): String? {
        var thePath = "no-path-found"
        val filePathColumn = arrayOf(MediaStore.Images.Media.DISPLAY_NAME)
        val cursor: Cursor? = contentResolver.query(contentURI, filePathColumn, null, null, null)
        if (cursor!!.moveToFirst()) {
            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
            thePath = cursor.getString(columnIndex)
        }
        cursor.close()
        return thePath
    }

    fun getPath(context: Context, uri: Uri?): String? {
        var result: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = context.getContentResolver().query(uri!!, proj, null, null, null)
        if (cursor!!.moveToFirst()) {
            val columnIndex = cursor.getColumnIndexOrThrow(proj[0])
            result = cursor.getString(columnIndex)
        }
        cursor.close()
        if (result == null) {
            result = "Not found"
        }
        return result
    }
}