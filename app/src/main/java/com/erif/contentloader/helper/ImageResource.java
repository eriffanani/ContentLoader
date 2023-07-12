package com.erif.contentloader.helper;

import com.erif.contentloader.R;

import java.util.Random;

public class ImageResource {

    public static int get() {
        int[] images = new int[] {
                R.mipmap.man1,
                R.mipmap.man2,
                R.mipmap.man3,
                R.mipmap.women1,
                R.mipmap.women2,
                R.mipmap.women3,
        };
        int randomNumber = new Random().nextInt(images.length);
        return images[randomNumber];
    }

    public static int getAll() {
        int[] images = new int[] {
                R.mipmap.man1,
                R.mipmap.man2,
                R.mipmap.man3,
                R.mipmap.women1,
                R.mipmap.women2,
                R.mipmap.women3,
                R.mipmap.img1,
                R.mipmap.img2,
                R.mipmap.img3,
                R.mipmap.img4,
                R.mipmap.img5
        };
        int randomNumber = new Random().nextInt(images.length);
        return images[randomNumber];
    }

}
