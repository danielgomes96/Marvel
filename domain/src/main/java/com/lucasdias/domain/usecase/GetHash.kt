package com.lucasdias.domain.usecase

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class GetHash {
    @Throws(Exception::class)
    operator fun invoke(vararg params: String): String {
        try {
            val value = getValuesToHash(*params)
            val md5Encoder = MessageDigest.getInstance(HASH_TYPE)
            val digest = md5Encoder.digest(value.toByteArray())

            return digest.fold("", { str, it -> str + HASH_FORMAT.format(it) })
        } catch (e: NoSuchAlgorithmException) {
            throw Exception("Cannot generate the hash", e)
        }
    }

    private fun getValuesToHash(vararg params: String): String {
        var valueToHash = ""

        params.forEach {
            valueToHash += it
        }

        return valueToHash
    }

    companion object {
        private const val HASH_TYPE = "MD5"
        private const val HASH_FORMAT = "%02x"
    }
}
