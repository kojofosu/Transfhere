package com.mcdev.transfhere.utils

import android.os.Environment
import java.io.File

class TransfhereUtils {

    companion object{
        /**
         * Create External Storage Directory
         * @param childDir child directory in external storage
         * @sample TransfhereUtils.createExternalStorageDir("sample/test")
         * @return java.io.File
         */
        @Deprecated(message = "This will be replaced with a better alternative",replaceWith = ReplaceWith(expression = "",imports = []), level = DeprecationLevel.WARNING,)
        fun createExternalStorageDir(childDir: String) : File {
            return File(Environment.getExternalStorageDirectory(), childDir)
        }
    }

}