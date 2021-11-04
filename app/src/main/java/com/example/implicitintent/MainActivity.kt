package com.example.implicitintent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ShareCompat
import com.example.implicitintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

    }

    fun openWebsite(view: android.view.View) {
        val url = binding.websiteEdittext.text.toString()
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this!")
        }

    }
    fun openLocation(view: android.view.View) {
        val loc = binding.locationEdittext.text.toString()
        val addressUri = Uri.parse("geo:0,0?q=$loc")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }

    }
    fun shareText(view: android.view.View) {
        val txt = binding.shareEdittext.text.toString()
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share this text with: ")
            .setText(txt)
            .startChooser()

    }

    fun takePic(view: android.view.View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
        startActivity(intent)
    }
}