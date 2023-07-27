package com.erif.contentloaderdemo.helper

import com.erif.contentloaderdemo.R
import java.util.Random

class ImageRes {

    companion object {

        fun people(): Int {
            val images = intArrayOf(
                R.mipmap.man1,
                R.mipmap.man2,
                R.mipmap.man3,
                R.mipmap.women1,
                R.mipmap.women2,
                R.mipmap.women3
            )
            val randomNumber = Random().nextInt(images.size)
            return images[randomNumber]
        }

        fun wallpaper(): Int {
            val images = intArrayOf(
                R.mipmap.img1,
                R.mipmap.img2,
                R.mipmap.img3,
                R.mipmap.img4,
                R.mipmap.img5
            )
            val randomNumber = Random().nextInt(images.size)
            return images[randomNumber]
        }

        fun clothes(): Int {
            val images = intArrayOf(
                R.mipmap.jacket2,
                R.mipmap.shirt2,
                R.mipmap.pants1,
                R.mipmap.pants3
            )
            val randomNumber = Random().nextInt(images.size)
            return images[randomNumber]
        }

        fun getAll(): Int {
            val images = intArrayOf(
                R.mipmap.img1,
                R.mipmap.img2,
                R.mipmap.img3,
                R.mipmap.img4,
                R.mipmap.img5,
                R.mipmap.man1,
                R.mipmap.man2,
                R.mipmap.man3,
                R.mipmap.women1,
                R.mipmap.women2,
                R.mipmap.women3,
                R.mipmap.jacket2,
                R.mipmap.shirt2,
                R.mipmap.pants1,
                R.mipmap.pants3,
                R.mipmap.man1,
                R.mipmap.man2,
                R.mipmap.man3,
                R.mipmap.women1,
                R.mipmap.women2,
                R.mipmap.women3,
                R.mipmap.jacket2,
                R.mipmap.shirt2,
                R.mipmap.pants1,
                R.mipmap.pants3
            )
            val randomNumber = Random().nextInt(images.size)
            return images[randomNumber]
        }

    }

}