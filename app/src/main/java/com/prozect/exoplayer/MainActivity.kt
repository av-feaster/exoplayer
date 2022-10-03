package com.prozect.exoplayer

import android.content.ContentValues.TAG
import android.graphics.Insets.add
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.example.Hits
import com.example.example.Large
import com.example.example.VideoApi
import com.prozect.exoplayer.databinding.ActivityMainBinding
import kotlin.math.log

private var videos=ArrayList<Video1>();
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    public lateinit var Vx: VideoApi
    private lateinit var adapter: VideoAdapter
//    private var videos=ArrayList<Video1>();

    private var exoPlayerItems = ArrayList<ExoPlayerItem>();

    //    var videos = ArrayList<Video1>();
    var mApp = MyApplication()
    var globvid = mApp.videos


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //fetch videos from pixybay api
        //using retrofit
        
        var arrayList: ArrayList<Hits> = arrayListOf()
        val retrofit = RetrofitVideo.getInstance()
        val apiInterface = retrofit.create(ApiMethods::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getAllUsers()
                if (response.isSuccessful()) {
                    Vx = response.body()!!
                    if (Vx != null) {
                        Log.i(TAG, "onResponse: " + Vx)

                    }
                    arrayList = Vx.hits
                    for (item in arrayList) {
                        Log.e(TAG, "getUserList: ${item.videosme?.large?.url}",)
                        videos.add(
                            Video1(
                                "${item.id}",
                                "${item.videosme?.large?.url}"
                            )
                        )

                    }
                    for (item in videos) {
                        Log.d(TAG, "getUserList: ${item.title}, ${item.url}")
                    }
                    Log.e(TAG, "${videos.size}")
                    this@MainActivity.globvid = videos

                    adapter = VideoAdapter(this@MainActivity, globvid, object : VideoAdapter.OnVideoPrepareListener {
                        override fun onVideoPrepared(exoPlayerItem: ExoPlayerItem) {
                            exoPlayerItems.add(exoPlayerItem)
                        }
                    })

                    binding.viewPager.adapter = adapter

                    //
                    binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            val previousIndex = exoPlayerItems.indexOfFirst { it.exoPlayer.isPlaying }
                            if (previousIndex != -1) {
                                val player = exoPlayerItems[previousIndex].exoPlayer
                                player.pause()
                                player.playWhenReady = false
                            }
                            val newIndex = exoPlayerItems.indexOfFirst { it.position == position }
                            if (newIndex != -1) {
                                val player = exoPlayerItems[newIndex].exoPlayer
                                player.playWhenReady = true
                                player.play()
                            }
                        }
                    })
                }


             else {
                    Toast.makeText(
                        this@MainActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }


        //

        Log.e(TAG, "onCreate main: ${globvid.size}")
    }
//

    override fun onPause() {
        super.onPause()
        val index = exoPlayerItems.indexOfFirst { it.position == binding.viewPager.currentItem }
        if (index != -1) {
            val player = exoPlayerItems[index].exoPlayer
            player.playWhenReady = false;
            player.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        val index = exoPlayerItems.indexOfFirst { it.position == binding.viewPager.currentItem }
        if (index != -1) {
            val player = exoPlayerItems[index].exoPlayer
            player.playWhenReady = true;
            player.play()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (exoPlayerItems.isNotEmpty()) {
            for (item in exoPlayerItems) {
                val player = item.exoPlayer
                player.stop()
                player.clearMediaItems()
            }
        }
    }
}






