package com.example.mangaapp

import java.util.*

class MangaResult{
    var results : List<Manga>? = null
    override fun toString(): String {
        return "Results: ${
                results?.map {
                    it -> it.title+ " a un nb de tomes : "+ it.volumes + " et son synopsis est :"+
                        it.synopsis
                }
        }"
    }
}