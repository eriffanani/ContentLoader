package com.erif.contentloaderdemo.example

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erif.contentloader.LoaderContainer
import com.erif.contentloaderdemo.R
import com.erif.contentloaderdemo.helper.adapter.AdapterHorizontal
import com.erif.contentloaderdemo.helper.timer.Delay

class ActivityHorizontal: AppCompatActivity() {

    private val adapter = AdapterHorizontal()
    private val list: MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal)
        supportActionBar?.let {
            it.title = "Horizontal Loader"
            it.setDisplayHomeAsUpEnabled(true)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.act_horizontal_recyclerView)
        setupList(recyclerView)

        val content = findViewById<LinearLayout>(R.id.act_horizontal_contents)

        val loader = findViewById<LoaderContainer>(R.id.content_loader_horizontal)
        loader.startAndHideContent(content, true)

        Delay(2.5) {
            recyclerView.scheduleLayoutAnimation()
            adapter.setList(list)
            Delay(
                0.1
            ) { loader.stopAndShowContent(content, true) }.start()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}