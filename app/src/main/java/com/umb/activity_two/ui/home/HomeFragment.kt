package com.umb.activity_two.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.umb.activity_two.R


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var webView: WebView
    private var url = "https://umb.edu.co/"

    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        webView = root.findViewById(R.id.web_view)
        webView.settings.setJavaScriptEnabled(true)


        progressBar = root.findViewById(R.id.progressBar)
        progressBar.max = 100
        progressBar.progress = 20
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                view.loadUrl(url!!)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String) {
                if (progressBar!!.isVisible) {
                    progressBar!!.visibility = View.INVISIBLE
                }
            }

            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String,
                failingUrl: String?
            ) {
                progressBar!!.visibility = View.INVISIBLE
            }
        }
        webView.loadUrl(url)
        return root
    }
}