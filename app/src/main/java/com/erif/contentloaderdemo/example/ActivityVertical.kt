package com.erif.contentloaderdemo.example

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erif.contentloader.LoaderContainer
import com.erif.contentloaderdemo.R
import com.erif.contentloaderdemo.helper.adapter.AdapterVertical
import com.erif.contentloaderdemo.helper.timer.Delay

class ActivityVertical: AppCompatActivity() {

    private val adapter = AdapterVertical()
    private val list: MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical)
        supportActionBar?.let {
            it.title = "Vertical Loader"
            it.setDisplayHomeAsUpEnabled(true)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.act_vertical_recyclerView)
        setupList(recyclerView)

        val loader = findViewById<LoaderContainer>(R.id.content_loader_vertical)
        loader.startAndHideContent(recyclerView, true)

        Delay(2.5) {
            adapter.setList(list)
            recyclerView.scheduleLayoutAnimation()
            Delay(
                0.1
            ) { loader.stopAndShowContent(recyclerView, true) }.start()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}