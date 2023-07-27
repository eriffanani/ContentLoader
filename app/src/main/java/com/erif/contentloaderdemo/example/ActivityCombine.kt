package com.erif.contentloaderdemo.example

import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup.MarginLayoutParams
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erif.contentloader.LoaderContainer
import com.erif.contentloaderdemo.R
import com.erif.contentloaderdemo.helper.adapter.AdapterHorizontal
import com.erif.contentloaderdemo.helper.adapter.AdapterVertical
import com.erif.contentloaderdemo.helper.timer.Delay
import com.google.android.material.imageview.ShapeableImageView
import kotlin.math.abs

class ActivityCombine: AppCompatActivity() {

    private val adapter = AdapterHorizontal()
    private val adapterVertical = AdapterVertical()
    private val list: MutableList<Int> = ArrayList()
    private val listVertical: MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combine)
        supportActionBar?.let {
            it.title = "Combined Loader"
            it.setDisplayHomeAsUpEnabled(true)
        }

        val loader = findViewById<LoaderContainer>(R.id.act_combined_loader)
        val image = findViewById<RelativeLayout>(R.id.act_combined_layoutImage)
        loader.startAndHideContent(image, true)

        val recyclerView = findViewById<RecyclerView>(R.id.act_combined_horizontal_recyclerView)
        setupList(recyclerView)

        val horizontalContent = findViewById<LinearLayout>(R.id.act_combined_horizontal_contents)
        val loaderHorizontal = findViewById<LoaderContainer>(R.id.content_loader_horizontal)
        loaderHorizontal.startAndHideContent(horizontalContent, true)

        Delay(
            2
        ) { loader.stopAndShowContent(image, true) }.start()

        Delay(2) {
            adapter.setList(list)
            loaderHorizontal.stopAndShowContent(horizontalContent, true)
            recyclerView.scheduleLayoutAnimation()
        }.start()

        val sWidth = resources.displayMetrics.widthPixels
        val imgLeft = findViewById<ShapeableImageView>(R.id.act_combined_imgLeft)
        val paramLeft = imgLeft.layoutParams as MarginLayoutParams
        val snipped = (sWidth * -1).toFloat()
        val transX = snipped + paramLeft.marginEnd * 2
        imgLeft.translationX = transX

        val imgRight = findViewById<ShapeableImageView>(R.id.act_combined_imgRight)
        imgRight.translationX = abs(transX)

        val imgCenter = findViewById<ShapeableImageView>(R.id.act_combined_imgCenter)
        val paramCenter = imgCenter.layoutParams as MarginLayoutParams
        paramCenter.marginStart = paramCenter.marginStart * 2
        paramCenter.marginEnd = paramCenter.marginEnd * 2
        imgCenter.layoutParams = paramCenter

        val recyclerViewVertical =
            findViewById<RecyclerView>(R.id.act_combined_loader_vertical_recyclerView)
        setupListVertical(recyclerViewVertical)

        val loaderVertical = findViewById<LoaderContainer>(R.id.act_combined_loader_vertical)
        loaderVertical.startAndHideContent(recyclerViewVertical, true)

        Delay(2.2) {
            adapterVertical.setList(listVertical)
            recyclerViewVertical.scheduleLayoutAnimation()
            Delay(
                0.1
            ) { loaderVertical.stopAndShowContent(recyclerViewVertical, true) }.start()
        }.start()

    }

    private fun setupList(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
        val manager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView.layoutManager = manager
        for (i in 0..19) {
            list.add(i)
        }
    }

    private fun setupListVertical(recyclerView: RecyclerView) {
        recyclerView.adapter = adapterVertical
        val manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager
        for (i in 0..19) {
            listVertical.add(i)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}