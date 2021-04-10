package com.thing.bangkit.md8_local_persistence.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thing.bangkit.md8_local_persistence.R
import com.thing.bangkit.md8_local_persistence.data.database.BookContract

class BookAdapter constructor(
    private val onPressDeleted: (Int, BookContract.Book) -> Unit,
    private val books: MutableList<BookContract.Book> = mutableListOf()
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    fun addBook(book: BookContract.Book) {
        books.add(book)
        notifyDataSetChanged()
    }

    fun deleteBook(position: Int) {
        books.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(onPressDeleted, layoutInflater)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    inner class BookViewHolder(
        private val onPressDeleted: (Int, BookContract.Book) -> Unit,
        view: View
    ) : RecyclerView.ViewHolder(view) {

        private val txtTitle: TextView = view.findViewById(R.id.txt_title)
        private val txtAuthor: TextView = view.findViewById(R.id.txt_author)

        fun bind(book: BookContract.Book) {
            txtTitle.text = book.title
            txtAuthor.text = book.author

            itemView.setOnClickListener {
                onPressDeleted(adapterPosition, book)
            }
        }
    }
}