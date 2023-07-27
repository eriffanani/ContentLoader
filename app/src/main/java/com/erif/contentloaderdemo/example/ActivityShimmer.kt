package com.erif.contentloaderdemo.example

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erif.contentloader.LoaderContainer
import com.erif.contentloaderdemo.R
import com.erif.contentloaderdemo.helper.adapter.AdapterVertical
import com.erif.contentloaderdemo.helper.timer.Delay
import com.google.android.material.progressindicator.CircularProgressIndicator

class ActivityShimmer: AppCompatActivity() {

    private val adapter = AdapterVertical()
    private val list: MutableList<Int> = ArrayList()

    private var progressIndicator: CircularProgressIndicator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shimmer)
        supportActionBar?.let {
            it.title = "Shimmer Loader"
            it.setDisplayHomeAsUpEnabled(true)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.act_shimmer_recyclerView)
        setupList(recyclerView)
        val loader = findViewById<LoaderContainer>(R.id.act_shimmer_contentLoader)
        loader.startAndHideContent(recyclerView)

        Delay(2.5) {
            adapter.setList(list)
            recyclerView.scheduleLayoutAnimation()
            Delay(
                0.1
            ) { loader.stopAndShowContent(recyclerView, true) }.start()
            Delay(.5) {
                if (progressIndicator != null)
                    progressIndicator?.isIndeterminate = false
            }.start()
        }.start()

    }

    private fun setupList(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
        val manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager
        for (i in 0..19) {
            list.add(i)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_loading, menu)
        val menuItem = menu.findItem(R.id.menu_loading)
        val actionView = menuItem.actionView
        progressIndicator = actionView?.findViewById<View>(R.id.menu_loading_progress) as CircularProgressIndicator?
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}