package com.thing.bangkit.md8_local_persistence

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.thing.bangkit.md8_local_persistence.data.BookLocalManager
import com.thing.bangkit.md8_local_persistence.data.SessionManager

class MainActivity : AppCompatActivity() {

    private var etUsername : EditText? = null
    private var btnLogin : Button? = null
    private var tvUsername: TextView? = null

    /*private lateinit var sessionManager: SessionManager*/
    private val sessionManager: SessionManager? by lazy {
        SessionManager(this)
    }

    private val bookManager by lazy {BookLocalManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        castViewComponent()

        //instance the object of session manager
        /*sessionManager = SessionManager(this)*/

        // getting the data of username
        userProfile()

        btnLogin?.setOnClickListener {
            //getting the text we have input in edit text
            val username = etUsername?.text?.toString()?:""

            //storing the data into shared preferences
            sessionManager?.storeUserSession(username = username, isLogin = true)

            //update the data immediately
            userProfile()

            //stoting the book data
            bookManager.insertBook("Bangkit", "Thingkilia", "Workshop", 123)
            bookManager.insertBook("Tokopedia", "Finnatia", "Office-book", 456)

            //intent to booklistactivity
            startActivity(Intent(this@MainActivity, BookListActivity::class.java))
        }
    }

    private fun userProfile() {
        val isLogin = sessionManager?.isLogin()
        if (isLogin == true) {
            tvUsername?.text = sessionManager?.getUsername()
            startActivity(Intent(this@MainActivity, BookListActivity::class.java))
        }else{
            tvUsername?.text = "You're not login yet"
        }
    }

    private fun castViewComponent() {
        etUsername = findViewById(R.id.et_username)
        btnLogin = findViewById(R.id.btn_login)
        tvUsername = findViewById(R.id.tv_username)
    }
}