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

    /**
     * @param context
     */
    class with (context: Context) {
        private val TAG = Copy::class.qualifiedName

        private lateinit var fileName: String
        private lateinit var fileNameWithExtension: String
        private lateinit var sourcePath: String
        private lateinit var destinationPath: String
        private lateinit var sourceChannel: FileChannel
        private lateinit var destinationChannel: FileChannel

        /**
         * @param fileName name of the file without its extension
         */
        fun filename(fileName: String) : with{
            Log.d(TAG, "filename: $fileName")
            this@with.fileName = fileName
            return this@with
        }

        /**
         * File name with its extension
         * @param fileNameWithExtension
         */
        fun fileNameWithExtension(fileNameWithExtension: String) : with {
            Log.d(TAG, "fileNameWithExtension: $fileNameWithExtension")
            this@with.fileNameWithExtension = fileNameWithExtension
            return this@with
        }

        /**
         * Path where file will be moved from
         */
        fun sourcePath(sourcePath: String) : with{
            Log.d(TAG, "sourcePath: $sourcePath")
            this.sourcePath = sourcePath
            return this@with
        }

        /**
         * Path where file will be moved to
         */
        fun destinationPath(destinationPath: String) : with{
            Log.d(TAG, "destinationPath: $destinationPath")
            this.destinationPath = destinationPath
            return this@with
        }

        /**
         * Create directory if it does not exist.
         */
        fun createDestinationDirIfNotExist() : with{
            val fileDestPath = File(destinationPath) //set file destination. ie. the destination path where the file will end up in
            if (!fileDestPath.exists()) //if the destination path does not exist, create it.
                Log.d(TAG, "createDestinationDirIfNotExist: Directory not found. Creating directory at $fileDestPath")
                fileDestPath.mkdirs()
            return this@with
        }


        /**
         * start copy
         */
        fun copy(){
            val source = File(sourcePath)
            val destination = File(destinationPath , fileNameWithExtension)

            this@with.sourceChannel = FileInputStream(source).channel
            this@with.destinationChannel = FileOutputStream(destination).channel

            //do transfer
            this@with.destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size())

            //close channels
            this@with.sourceChannel.close()
            this@with.destinationChannel.close()
            Log.d(TAG, "build: successful")
        }
    }
}
