package com.mcdev.transfhere.copy

import android.content.Context

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
        fun with(context: Context) : CopyBuilder {
            return CopyBuilder(context)
        }
    }
}
