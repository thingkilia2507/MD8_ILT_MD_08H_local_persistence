package com.thing.bangkit.md8_local_persistence.data

import android.content.ContentValues
import android.content.Context
import com.thing.bangkit.md8_local_persistence.data.database.BookContract
import com.thing.bangkit.md8_local_persistence.data.database.BookContract.BookEntry.Companion.COLUMN_AUTHOR
import com.thing.bangkit.md8_local_persistence.data.database.BookContract.BookEntry.Companion.COLUMN_GENRE
import com.thing.bangkit.md8_local_persistence.data.database.BookContract.BookEntry.Companion.COLUMN_ID
import com.thing.bangkit.md8_local_persistence.data.database.BookContract.BookEntry.Companion.COLUMN_PAGES
import com.thing.bangkit.md8_local_persistence.data.database.BookContract.BookEntry.Companion.COLUMN_TITLE
import com.thing.bangkit.md8_local_persistence.data.database.BookContract.BookEntry.Companion.TABLE_NAME
import com.thing.bangkit.md8_local_persistence.data.database.BookDatabaseHelper

class BookLocalManager constructor(
    private val context: Context
) {
    //first, instace the book database helper
    private val dbHelper by lazy {
        BookDatabaseHelper(context)
    }

    //second, getting the database access
    private val writeableDb = dbHelper.writableDatabase
    private val readableDb = dbHelper.readableDatabase

    fun insertBook(title: String, author: String, genre: String, pages: Int) {
        //define the content value to storing the data into sqlite
        val values = ContentValues()
        values.put(COLUMN_TITLE, title)
        values.put(COLUMN_AUTHOR, author)
        values.put(COLUMN_GENRE, genre)
        values.put(COLUMN_PAGES, pages)

        writeableDb.insert(TABLE_NAME, null, values)
    }

    fun deleteBook(author: String) {
        val selection = BookContract.BookEntry.COLUMN_AUTHOR + " LIKE ?" //"?" means we have args and need to determine selectionArgs
        val selectionArgs = arrayOf(author)

        readableDb.delete(
            TABLE_NAME,
            selection,
            selectionArgs
        )
    }

    fun getBooks() : MutableList<BookContract.Book> {
        // define the temporary list of book variable
        val books = mutableListOf<BookContract.Book>()

        //define the cursor to representing of the data list
        readableDb.query(
            false,
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        ).apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(COLUMN_TITLE))
                val author = getString(getColumnIndexOrThrow(COLUMN_AUTHOR))
                val genre = getString(getColumnIndexOrThrow(COLUMN_GENRE))
                val pages = getInt(getColumnIndexOrThrow(COLUMN_PAGES))

                //inert into books
                books.add(BookContract.Book(
                    id,title,author,genre,pages
                ))
            }

        }
        return books
    }
}