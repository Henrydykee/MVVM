package com.example.mvvm.ui.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm.R
import com.example.mvvm.data.Quote
import com.example.mvvm.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_quote.*
import java.lang.StringBuilder
import kotlin.text.Typography.quote
import kotlin.collections.forEach as forEach1

class QuoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)
        initializeUi()
    }

    private  fun initializeUi(){
        val factory =InjectorUtils.provideQuoteViewModelFcatory()
        val viewModel = ViewModelProviders.of(this,factory)
            .get(QuotesViewModel::class.java)

        viewModel.getQuotes().observe(this, Observer {quotes ->
        val stringBuilder = StringBuilder()
            quotes.forEach{ quote ->
                stringBuilder.append("$quote\n\n")
            }
            textView_quotes.text = stringBuilder.toString()
        })
            button_add_quote.setOnClickListener{
                val quote = Quote(editText_quote.text.toString(),
                    editText_Author.text.toString())
                viewModel.addQuote(quote)
                editText_quote.setText("")
                editText_Author.setText("")
            }
    }
}
