package com.sdu.share.expense.security

import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class PasswordEncryptor(
    initializationVector: String,
    secretKey: String
) {
    private val transformationMode = "AES/CBC/PKCS5PADDING"
    private val encryptionCipher: Cipher
    private val decryptionCipher: Cipher
    private val base64Encoder = Base64.getEncoder()
    private val base64Decoder = Base64.getDecoder()

    init {
        val secretKeyArray = secretKey.toByteArray()
        val initializationVectorArray = initializationVector.toByteArray()
        val generatedKey = SecretKeySpec(secretKeyArray, "AES")

        encryptionCipher = Cipher.getInstance(transformationMode)
        encryptionCipher.init(
            Cipher.ENCRYPT_MODE,
            generatedKey,
            IvParameterSpec(initializationVectorArray)
        )

        decryptionCipher = Cipher.getInstance(transformationMode)
        decryptionCipher.init(
            Cipher.DECRYPT_MODE,
            generatedKey,
            IvParameterSpec(initializationVectorArray)
        )
    }

    fun encryptPassword(password: String): String {
        val passwordBytes = encryptionCipher.doFinal(password.toByteArray())
        return base64Encoder.encodeToString(passwordBytes)
    }

    fun decryptPassword(encryptedPassword: String): String {
        val passwordBytes = base64Decoder.decode(encryptedPassword)
        val decryptedBytes = decryptionCipher.doFinal(passwordBytes)
        return String(decryptedBytes)
    }
}