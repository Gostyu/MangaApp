package com.example.mangaapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MangaApi {

    @GET("search/manga")
    fun getMangaWithTitle (@Query("q") q:String,
                           @Query("limit") limit:Int) : Call<MangaResult>
}