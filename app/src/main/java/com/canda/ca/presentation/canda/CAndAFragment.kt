package com.canda.ca.presentation.canda

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.canda.ca.databinding.FragmentCandaBinding


class CAndAFragment : Fragment() {

    private lateinit var binding: FragmentCandaBinding

    companion object {
        const val CA_URL = "https://www.c-and-a.com/de/de/shop/damen"
        const val HOST = "www.c-and-a.com"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCandaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webView.settings.javaScriptEnabled = true

        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url: String?
            ): Boolean {
                if (url != null && Uri.parse(url).host == HOST) {
                    view?.loadUrl(url)
                    return false
                }

                Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    startActivity(this)
                }
                return true
            }
        }
        binding.webView.loadUrl(CA_URL)
    }


}