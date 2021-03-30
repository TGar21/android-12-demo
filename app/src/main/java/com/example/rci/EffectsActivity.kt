package com.example.rci

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.rci.databinding.ActivityEffectsBinding

const val DESATURATION_ADDITION = 2f

class EffectsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEffectsBinding
    private var blur = 2f
    private var desaturation = 2f

    @RequiresApi(31)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEffectsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupEffectListeners()
        binding.resetButton
    }

    @RequiresApi(31)
    private fun setupEffectListeners() {
        binding.img1.setOnClickListener {
            it.setRenderEffect(RenderEffect.createBlurEffect(blur, blur, Shader.TileMode.MIRROR))
            blur += BLUR_ADDITION
        }

        binding.img2.setOnClickListener {
            it.setRenderEffect(
                RenderEffect.createColorFilterEffect(
                    ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(desaturation) })
                )
            )
            desaturation += DESATURATION_ADDITION
        }
        setupCombinedEffect()
    }

    @RequiresApi(31)
    private fun setupCombinedEffect() {
        binding.img3.setOnClickListener {
            val blurEffect = RenderEffect.createBlurEffect(blur, blur, Shader.TileMode.MIRROR)
            val desaturateEffect = RenderEffect.createColorFilterEffect(
                ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(desaturation) })
            )

            it.setRenderEffect(
                RenderEffect.createChainEffect(
                    blurEffect,
                    desaturateEffect
                )
            )
        }
    }
}
