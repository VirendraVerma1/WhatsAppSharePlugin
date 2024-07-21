package com.kreasaar.whatsappshareplugin

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

class WhatsAppShare(private val context: Context) {

    fun shareText(text: String): Boolean {
        return try {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
            }
            startWhatsAppIntent(intent)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun shareImage(imageUri: Uri, text: String): Boolean {
        return try {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "image/*"
                putExtra(Intent.EXTRA_STREAM, imageUri)
                putExtra(Intent.EXTRA_TEXT, text)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startWhatsAppIntent(intent)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun startWhatsAppIntent(intent: Intent) {
        val packageManager = context.packageManager
        val whatsappIntent = if (isWhatsAppInstalled(packageManager, "com.whatsapp")) {
            intent.setPackage("com.whatsapp")
            intent
        } else if (isWhatsAppInstalled(packageManager, "com.whatsapp.w4b")) {
            intent.setPackage("com.whatsapp.w4b")
            intent
        } else {
            Intent.createChooser(intent, "Share via")
        }

        context.startActivity(whatsappIntent)
    }

    private fun isWhatsAppInstalled(packageManager: PackageManager, packageName: String): Boolean {
        return try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}