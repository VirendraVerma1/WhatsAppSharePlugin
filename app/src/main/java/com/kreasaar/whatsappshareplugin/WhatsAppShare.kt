package com.kreasaar.whatsappshareplugin

import android.content.Context
import android.content.Intent
import android.net.Uri

class WhatsAppShare(private val context: Context) {

    fun shareText(text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        intent.setPackage("com.whatsapp")
        context.startActivity(Intent.createChooser(intent, "Share with"))
    }

    fun shareImage(imageUri: Uri, text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_STREAM, imageUri)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        intent.setPackage("com.whatsapp")
        context.startActivity(Intent.createChooser(intent, "Share with"))
    }
}
