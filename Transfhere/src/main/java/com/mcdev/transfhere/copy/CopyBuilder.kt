package com.mcdev.transfhere.copy

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.channels.FileChannel

class CopyBuilder(context: Context) {
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
    fun filename(fileName: String) : CopyBuilder {
        Log.d(TAG, "filename: $fileName")
        this.fileName = fileName
        return this
    }

    /**
     * File name with its extension
     * @param fileNameWithExtension
     */
    fun fileNameWithExtension(fileNameWithExtension: String) : CopyBuilder {
        Log.d(TAG, "fileNameWithExtension: $fileNameWithExtension")
        this.fileNameWithExtension = fileNameWithExtension
        return this
    }

    /**
     * Path where file will be moved from
     */
    fun sourcePath(sourcePath: String) : CopyBuilder {
        Log.d(TAG, "sourcePath: $sourcePath")
        this.sourcePath = sourcePath
        return this
    }

    /**
     * Path where file will be moved to
     */
    fun destinationPath(destinationPath: String) : CopyBuilder {
        Log.d(TAG, "destinationPath: $destinationPath")
        this.destinationPath = destinationPath
        return this
    }

    /**
     * Create directory if it does not exist.
     */
    fun createDestinationDirIfNotExist() : CopyBuilder {
        val fileDestPath = File(destinationPath) //set file destination. ie. the destination path where the file will end up in
        if (!fileDestPath.exists()) //if the destination path does not exist, create it.
            Log.d(TAG, "createDestinationDirIfNotExist: Directory not found. Creating directory at $fileDestPath")
        fileDestPath.mkdirs()
        return this
    }


    /**
     * start copy
     */
    fun copy(){
        val source = File(sourcePath)
        val destination = File(destinationPath , fileNameWithExtension)

        this.sourceChannel = FileInputStream(source).channel
        this.destinationChannel = FileOutputStream(destination).channel

        //do transfer
        this.destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size())

        //close channels
        this.sourceChannel.close()
        this.destinationChannel.close()
        Log.d(TAG, "build: successful")
    }
}
