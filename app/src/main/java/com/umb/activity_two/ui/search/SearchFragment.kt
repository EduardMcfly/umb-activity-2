package com.umb.activity_two.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.umb.activity_two.R

class SearchFragment : Fragment() {

    private lateinit var webView: WebView
    private lateinit var textView: TextView
    private var url = "https://www.google.com"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        textView = root.findViewById(R.id.search_input)
        textView.text = url

        webView = root.findViewById(R.id.web_view)
        webView.settings.setJavaScriptEnabled(true)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl(url)
        val button = root.findViewById<Button>(R.id.search_button)
        button.setOnClickListener {
            search(it)
        }
        return root
    }

    fun search(view: View) {
        webView.loadUrl(textView.text.toString())
    }
}