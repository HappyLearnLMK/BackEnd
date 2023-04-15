package com.backend.util.file

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.*

@Component
class FileStore {

    @Value("\${file.dir}")
    var fileDir: String = ""

    fun getFullPath(fileName: String): String {
        return fileDir + fileName
    }

    fun storeFile(multipartFile: MultipartFile?): UploadFile?{
        if (multipartFile!!.isEmpty) {
            return null
        }
        val originalFilename = multipartFile.originalFilename
        val storeFileName = createStoreFileName(originalFilename)
        multipartFile.transferTo(File(getFullPath(storeFileName)))

        return UploadFile(originalFilename!!, storeFileName)
    }

    private fun createStoreFileName(originalFilename: String?): String {
        val ext = extractExt(originalFilename)
        val uuid = UUID.randomUUID().toString()
        return "$uuid.$ext"
    }

    private fun extractExt(originalFilename: String?): String? {
        val pos = originalFilename?.lastIndexOf(".")
        return originalFilename?.substring(pos!! + 1)
    }
}