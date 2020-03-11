package com.example.mangaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MangaActivity : AppCompatActivity() {
    private val API_URL: String = "https://api.jikan.moe/v3/";
    var manga :Manga? = null
    var titleMangaView : TextView? = null ;
    //lateinit var mangaDate : MangaDate
    lateinit  var Img: ImageView
    lateinit var nbVolumesView: TextView
    lateinit var synopsis: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manga)
        val manga = intent.getParcelableExtra<Manga?>("MANGA_DATA")
        Log.i("MANGA_ACTIVITY", manga.toString())
       titleMangaView = findViewById(R.id.titreManga)
       Img = findViewById(R.id.imageView)
       synopsis = findViewById(R.id.synopsisText)
       nbVolumesView = findViewById(R.id.nbVolumes)
        updateUI(manga);
    }
    fun loadAndprintImage(url:String){
        Glide.with(this.baseContext).load(url).override(200,400)
            .into(Img)
    }
    fun updateUI(manga : Manga?) {
        if(manga?.title=="Naruto"){
            Log.d("TEST TITLE"," OK")
        }
        titleMangaView?.setText(manga?.title)
        loadAndprintImage(manga?.image_url.toString())
        nbVolumesView.setText(manga?.volumes.toString()+" tomes")
        synopsis.setText(manga?.synopsis);
    }

}
