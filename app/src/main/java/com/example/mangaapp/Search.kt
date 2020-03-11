package com.example.mangaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Search : AppCompatActivity() {
    private val FORBIDDEN_REQUEST = 403
    private val API_URL: String = "https://api.jikan.moe/v3/";
    val DATA_TAG = "MANGA_DATA"
    var manga :Manga? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }
    fun sendSearchManga(v : View){
        val editText = findViewById<EditText?>(R.id.searchBar)
        val mangaTitle : String = editText?.text.toString()
        Log.i("SEARCH", mangaTitle);
        if(mangaTitle!=null) {
            startRequest(mangaTitle)
        }
    }
    fun sendData(data : Manga){
        val intent = Intent(this,MangaActivity::class.java )
        intent.putExtra(DATA_TAG,data)
        startActivity(intent)
    }
    fun startRequest(mangaTitle:String){
        val retrofit = Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        //creation du service (Retrofit + Mon interface)
        val service = retrofit.create(MangaApi::class.java);
        //creation de la requete GET
        //var mangaId = 1;
        val mangaRequest = service.getMangaWithTitle(mangaTitle,1);
        mangaRequest.enqueue(object : Callback<MangaResult> {
            override fun onFailure(call: Call<MangaResult>, t: Throwable) {
                error("${t.message}")
                error("Echec de la requete pour récupérer un manga")
            }
            override fun onResponse(call: Call<MangaResult>, response: Response<MangaResult>) {
                Log.i("InfoURL", response.toString())
                if(response.code() == FORBIDDEN_REQUEST){
                    Toast.makeText(baseContext,"ressayer".toUpperCase(), Toast.LENGTH_LONG).show()
                }
                if(response.isSuccessful) {
                    Log.d("RESPONSE_BODY",response?.body().toString())
                    Log.d("URL REQUEST",response?.toString())
                    val data = response?.body()
                    data?.results?.map({
                        sendData(it);
                    })
                    //  MangaResult = response.body();
                   /* if (MangaResult != null) {
                        //updateUI(manga);
                      //  Log.d("URL REQUEST",response.raw().toString())
                        //Log.i("URL IMG", manga?.image_url)
                        // Log.i("FROM",manga?.published.toString())
                    } else {
                        Log.e("NullValue", "ok")
                    }*/
                }
            }
        })

    }

}
