package com.erif.contentloaderdemo.example

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erif.contentloader.LoaderContainer
import com.erif.contentloaderdemo.R
import com.erif.contentloaderdemo.helper.adapter.AdapterGrid
import com.erif.contentloaderdemo.helper.timer.Delay

class ActivityGrid: AppCompatActivity() {

    private val adapter = AdapterGrid()
    private val list: MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)
        supportActionBar?.let {
            it.title = "Grid Loader"
            it.setDisplayHomeAsUpEnabled(true)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.act_grid_recyclerView)
        setupList(recyclerView)

        val loader = findViewById<LoaderContainer>(R.id.content_loader_grid)
        loader.post { loader.startAndHideContent(recyclerView, true) }

        Delay(2.5) {
            recyclerView.scheduleLayoutAnimation()
            adapter.setList(list)
            Delay(
                0.1
            ) { loader.stopAndShowContent(recyclerView, true) }.start()
        }.start()

    }

    private fun setupList(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
        val manager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = manager
        for (i in 0..29) {
            list.add(i)
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