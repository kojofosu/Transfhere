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
     * Builder
     * @param context
     */
    class Builder (context: Context) {
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
        fun filename(fileName: String) : Builder{
            Log.d(TAG, "filename: $fileName")
            this@Builder.fileName = fileName
            return this@Builder
        }

        /**
         * File name with its extension
         * @param fileNameWithExtension
         */
        fun fileNameWithExtension(fileNameWithExtension: String) : Builder {
            Log.d(TAG, "fileNameWithExtension: $fileNameWithExtension")
            this@Builder.fileNameWithExtension = fileNameWithExtension
            return this@Builder
        }

        /**
         * Path where file will be moved from
         */
        fun sourcePath(sourcePath: String) : Builder{
            Log.d(TAG, "sourcePath: $sourcePath")
            this.sourcePath = sourcePath
            return this@Builder
        }

        /**
         * Path where file will be moved to
         */
        fun destinationPath(destinationPath: String) : Builder{
            Log.d(TAG, "destinationPath: $destinationPath")
            this.destinationPath = destinationPath
            return this@Builder
        }

        /**
         * Create directory if it does not exist.
         */
        fun createDestinationDirIfNotExist() : Builder{
            Log.d(TAG, "createDestinationDirIfNotExist: File name is : $fileNameWithExtension")
            Log.d(TAG, "createDestinationDirIfNotExist: Destination path : $destinationPath")
            val fileDestPath = File(destinationPath) //set file destination. ie. the destination path where the file will end up in
            if (!fileDestPath.exists()) //if the destination path does not exist, create it.
                Log.d(TAG, "createDestinationDirIfNotExist: Directory not found. Creating directory at $fileDestPath")
                fileDestPath.mkdirs()
            return this@Builder
        }


        /**
         * Build file channels and starts transfer execution. Channels are closed after transfer
         */
        fun build(){
            val source = File(sourcePath)
            val destination = File(destinationPath , fileNameWithExtension)

            this@Builder.sourceChannel = FileInputStream(source).channel
            this@Builder.destinationChannel = FileOutputStream(destination).channel

            //do transfer
            this@Builder.destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size())

            //close channels
            this@Builder.sourceChannel.close()
            this@Builder.destinationChannel.close()
            Log.d(TAG, "build: successful")
        }
    }
}
