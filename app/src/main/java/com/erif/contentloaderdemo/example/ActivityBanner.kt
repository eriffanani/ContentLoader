package com.erif.contentloaderdemo.example

import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup.MarginLayoutParams
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.erif.contentloader.LoaderContainer
import com.erif.contentloaderdemo.R
import com.erif.contentloaderdemo.helper.timer.Delay
import com.google.android.material.imageview.ShapeableImageView
import kotlin.math.abs

class ActivityBanner: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        supportActionBar?.let {
            it.title = "Banner / Slider Loader"
            it.setDisplayHomeAsUpEnabled(true)
        }

        val loader = findViewById<LoaderContainer>(R.id.act_frame_loader)
        val image = findViewById<RelativeLayout>(R.id.act_frame_layoutImage)
        loader.startAndHideContent(image, true)

        Delay(
            2.5
        ) { loader.stopAndShowContent(image, true) }.start()

        val sWidth = resources.displayMetrics.widthPixels
        val imgLeft = findViewById<ShapeableImageView>(R.id.act_frame_imgLeft)
        val paramLeft = imgLeft.layoutParams as MarginLayoutParams
        val snipped = (sWidth * -1).toFloat()
        val transX = snipped + paramLeft.marginEnd * 2
        imgLeft.translationX = transX

        val imgRight = findViewById<ShapeableImageView>(R.id.act_frame_imgRight)
        imgRight.translationX = abs(transX)

        val imgCenter = findViewById<ShapeableImageView>(R.id.act_frame_imgCenter)
        val paramCenter = imgCenter.layoutParams as MarginLayoutParams
        paramCenter.marginStart = paramCenter.marginStart * 2
        paramCenter.marginEnd = paramCenter.marginEnd * 2
        imgCenter.layoutParams = paramCenter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}