package com.thing.bangkit.md8_local_persistence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thing.bangkit.md8_local_persistence.adapter.BookAdapter
import com.thing.bangkit.md8_local_persistence.data.BookLocalManager
import com.thing.bangkit.md8_local_persistence.data.database.BookContract

class BookListActivity : AppCompatActivity() {

    private var lstBook: RecyclerView? = null
    private val bookManager by lazy{
        BookLocalManager(this)
    }


    private lateinit var adapter: BookAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        //casting the recyclerview
        lstBook = findViewById(R.id.lst_book)
        lstBook?.layoutManager = LinearLayoutManager(this)

       adapter = BookAdapter(::onItemDeleted, bookManager.getBooks())

        //set the data into recyclerview's adapter
        lstBook?.adapter = adapter
    }

    fun addBook() {
        adapter.addBook(BookContract.Book())
    }

    private fun onItemDeleted (position: Int,book :BookContract.Book){
        bookManager.deleteBook(book.author)
        adapter.deleteBook(position)
    }
}