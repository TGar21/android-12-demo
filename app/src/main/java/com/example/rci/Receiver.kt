package com.example.rci

import android.view.View
import android.widget.ImageView
import androidx.core.util.component1
import androidx.core.util.component2
import androidx.core.view.ContentInfoCompat
import androidx.core.view.OnReceiveContentListener

class Receiver(private val imageView: ImageView) : OnReceiveContentListener {

    override fun onReceiveContent(
        view: View,
        contentInfo: ContentInfoCompat
    ): ContentInfoCompat? {
        // We need to split contentInfo to two groups.
        // because we care only about uriContent – it matches our predicate,
        // we let remaining system to handle
        val (uriContent, remaining) = contentInfo.partition { it.uri != null }

        if (uriContent != null) {
            when {
                uriContent.clip.description.getMimeType(0).contains("image") ->
                    imageView.setImageURI(uriContent.clip.getItemAt(0).uri)
                else ->
                    imageView.setImageResource(R.drawable.unsupported)
            }

        }
        return remaining
    }


}