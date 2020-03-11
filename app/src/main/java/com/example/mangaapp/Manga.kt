package com.example.mangaapp

import android.os.Parcel
import android.os.Parcelable
import java.text.DateFormat

class Manga() : Parcelable {
    var id:Int? = null;
    var title:String? = null;
    var image_url:String? =null;
    var volumes : Int?=null;
    var chapters:Int?=null;
    var synopsis:String?=null;

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        title = parcel.readString()
        image_url = parcel.readString()
        volumes = parcel.readValue(Int::class.java.classLoader) as? Int
        chapters = parcel.readValue(Int::class.java.classLoader) as? Int
        synopsis = parcel.readString()
    }

    override fun toString(): String {
        return """
            id du manga:${id},
            titre du manga : ${title},
            image:${image_url},
            volumes : ${volumes},
            chapitres : ${chapters},
            synopsis :${synopsis}
        """.trimIndent()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(image_url)
        parcel.writeValue(volumes)
        parcel.writeValue(chapters)
        parcel.writeString(synopsis)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Manga> {
        override fun createFromParcel(parcel: Parcel): Manga {
            return Manga(parcel)
        }

        override fun newArray(size: Int): Array<Manga?> {
            return arrayOfNulls(size)
        }
    }
}