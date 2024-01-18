package com.atthnei.sharetofirefox

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main) // No layout needed for this functionality

        // Handle the shared content
        when (intent?.action) {
            Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    handleSharedText(intent.getStringExtra(Intent.EXTRA_TEXT))
                }
            }
            // Add more cases if needed, e.g., ACTION_SEND_MULTIPLE
        }

        finish() // Close the activity once the action is performed
    }

    private fun handleSharedText(sharedText: String?) {
        sharedText?.let {
            // Construct a custom URL for Firefox. Adjust this based on your requirement.
            val customUrl = "https://duckduckgo.com/?q=$it"
            openInChrome(customUrl)
        }
    }

    private fun openInFirefox(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.setPackage("org.mozilla.firefox")

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Firefox is not installed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openInChrome(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.setPackage("com.android.chrome")

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Chrome is not installed", Toast.LENGTH_SHORT).show()
        }
    }
}
