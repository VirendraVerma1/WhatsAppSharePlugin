package com.kreasaar.whatsappshareplugin

import android.app.Activity
import android.net.Uri

class UnityBridge private constructor(private val activity: Activity) {
    private val whatsappShare: WhatsAppShare = WhatsAppShare(activity)

    companion object {
        @JvmStatic
        fun getInstance(activity: Activity): UnityBridge {
            return UnityBridge(activity)
        }
    }

    fun shareText(text: String): Boolean {
        return whatsappShare.shareText(text)
    }

    fun shareImage(uri: String, text: String): Boolean {
        return try {
            val imageUri = Uri.parse(uri)
            whatsappShare.shareImage(imageUri, text)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}