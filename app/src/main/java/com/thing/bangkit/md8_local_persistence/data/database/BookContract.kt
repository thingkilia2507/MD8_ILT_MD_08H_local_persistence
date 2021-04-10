package com.thing.bangkit.md8_local_persistence.data.database

import android.provider.BaseColumns

class BookContract {
    data class Book(
        val id : Int = 0,
        val title : String = "",
        val author : String = "",
        val genre : String = "",
        val pages : Int = 0
    )

    class BookEntry : BaseColumns{
        companion object{
            // table name
            const val TABLE_NAME = "fav_book"

            //columns that contains on 'fav_book' TABLE
            const val COLUMN_ID = "_id"
            const val COLUMN_TITLE = "title"
            const val COLUMN_AUTHOR = "author"
            const val COLUMN_GENRE = "genre"
            const val COLUMN_PAGES = "pages"


        }
    }

}