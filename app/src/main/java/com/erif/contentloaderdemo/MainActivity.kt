package com.erif.contentloaderdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.erif.contentloaderdemo.example.ActivityBanner
import com.erif.contentloaderdemo.example.ActivityCombine
import com.erif.contentloaderdemo.example.ActivityGrid
import com.erif.contentloaderdemo.example.ActivityHorizontal
import com.erif.contentloaderdemo.example.ActivityShimmer
import com.erif.contentloaderdemo.example.ActivityVertical

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.let {
            it.title = "Content Loader Animation"
            it.subtitle = "https://github.com/eriffanani/ContentLoader"
        }
        onClick(R.id.btnVertical, ActivityVertical::class.java)
        onClick(R.id.btnShimmer, ActivityShimmer::class.java)
        onClick(R.id.btnHorizontal, ActivityHorizontal::class.java)
        onClick(R.id.btnGrid, ActivityGrid::class.java)
        onClick(R.id.btnBanner, ActivityBanner::class.java)
        onClick(R.id.btnCombine, ActivityCombine::class.java)
    }

    private fun onClick(id: Int, destination: Class<*>) {
        button(id)?.setOnClickListener {
            intent(destination)
        }
    }

    private fun intent(destination: Class<*>) {
        val intent = Intent(this, destination)
        startActivity(intent)
    }

    private fun button(id: Int): Button? {
        return findViewById(id)
    }

}