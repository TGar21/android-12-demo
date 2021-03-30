package com.example.rci

import android.content.Intent
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.example.rci.databinding.ActivityMainBinding

const val BLUR_ADDITION = 5f

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var blur = 1f

    @RequiresApi(31)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnReceiveContentListener(
            binding.editQuery,
            arrayOf("image/*", "video/*", "audio/*"),
            Receiver(binding.image)
        )

        setupListeners()
    }

    @RequiresApi(31)
    private fun setupListeners(){
        binding.effectsButton.setOnClickListener {
            val intent = Intent(this, EffectsActivity::class.java)
            this.startActivity(intent)
        }

        binding.image.setOnClickListener {
            // larger radius -> bigger blur, shader type specifies corners
            it.setRenderEffect(
                RenderEffect.createBlurEffect(
                    blur,
                    blur,
                    Shader.TileMode.MIRROR
                )
            )
            blur += BLUR_ADDITION
        }
    }
}

// MIME TYPES: http://androidxref.com/4.4.4_r1/xref/frameworks/base/media/java/android/media/MediaFile.java#174