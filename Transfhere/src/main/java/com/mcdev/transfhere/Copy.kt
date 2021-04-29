package com.mcdev.transfhere

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.channels.FileChannel

/**
 * @author Kojo Fosu Bempa Edue
 */
class Copy {

    companion object{
        /**
         * @author Kojo Fosu Bempa Edue
         * @param context context
         * @return CopyBuilder
         */
        fun with(context: Context) : CopyBuilder{
            return CopyBuilder(context)
        }
    }
}
