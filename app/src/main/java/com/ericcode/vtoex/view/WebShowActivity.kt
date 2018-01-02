package com.ericcode.vtoex.view

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.*
import com.ericcode.vtoex.R
import com.ericcode.vtoex.data.bean.Topic
import com.ericcode.vtoex.util.Logger
import kotlinx.android.synthetic.main.activity_web_show.*

/**
 * show web page
 */
class WebShowActivity : AppCompatActivity() {
    private var topic: Topic? = null

    companion object {
        private val KEY_TOPIC: String = "KEY_TOPIC"
        fun startMe(context: Activity?, topic: Topic) {
            val intent = Intent(context, WebShowActivity::class.java)
            intent.putExtra(KEY_TOPIC, topic)
            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_show)

        topic = intent.getParcelableExtra(KEY_TOPIC)

        if (topic == null) {
            finish()
        } else {
            with(topic!!) {
                setTitle(title)
                initWebView()
                webView.loadUrl(url)
            }
        }
    }

    private fun initWebView() {

        val hideProgressBar = Runnable { progressBar.visibility = View.INVISIBLE; }

        val webSettings = webView.settings

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
        webSettings.javaScriptEnabled = true


        //设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true //将图片调整到适合webview的大小
        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小

        //缩放操作
//        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
//        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
//        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
//        webSettings.setAllowFileAccess(true); //设置可以访问文件
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.loadsImagesAutomatically = true //支持自动加载图片

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                injection(webView)
                view.loadUrl(url)
                changeToNormalPage()
                swipeRefreshLayout.isEnabled = false
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                Logger.i("magic begin")
                injection(webView)
                super.onPageFinished(view, url)
            }

            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Logger.i("onReceivedError: " + (error?.errorCode ?: ""))
                } else {
                    Logger.i("onReceivedError: ")
                }
            }

            fun injection(webView: WebView) {
                val function: String = "javascript:function hideOther() {\n" +
                        "document.getElementById('Top').style.display='none';\n" +
                        "document.querySelector('#Wrapper>.content').style.padding=0;\n" +
                        "document.querySelector('.header>span').style.display='none';\n" +
                        "document.querySelector('.sep10').style.display='none';\n" +
                        "var matches = document.querySelectorAll('.header>a');for(var item in matches){matches[item].style.display='none';}\n" +
                        "}"
                webView.loadUrl(function)
                webView.loadUrl("javascript:hideOther();")
            }

            override fun onReceivedHttpError(view: WebView, request: WebResourceRequest, errorResponse: WebResourceResponse) {
                super.onReceivedHttpError(view, request, errorResponse)
                Logger.i("onReceivedHttpError()")
            }

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                swipeRefreshLayout.isEnabled = true
                Logger.i("onReceivedError(), errorCode:%d, failingUrl:%s", errorCode, failingUrl)
                changeToErrorPage()
            }
        }

        webView.webChromeClient = object : WebChromeClient() {

            override fun onReceivedTitle(view: WebView, title: String) {
                Logger.i("title:" + title)
                setTitle(title)
            }

            //获取加载进度
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                Logger.i("progress:" + newProgress.toString())
                if (newProgress < 100) {
                    if (progressBar.visibility == View.GONE) {
                        progressBar.visibility = View.VISIBLE
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        progressBar.setProgress(newProgress, true)
                    } else {
                        progressBar.progress = newProgress
                    }

                    if (hideProgressBar != null) {
                        progressBar.removeCallbacks(hideProgressBar)
                    }
                } else {
                    if (hideProgressBar != null) {
                        progressBar.postDelayed(hideProgressBar, 500)
                    }

                    if (swipeRefreshLayout.isRefreshing) {
                        swipeRefreshLayout.isRefreshing = false
                    }
                }
            }

            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                AlertDialog.Builder(this@WebShowActivity)
                        .setTitle("JsAlert")
                        .setMessage(message)
                        .setPositiveButton("OK") { dialog, which -> result.confirm() }
                        .setCancelable(false)
                        .show()
                return true
            }

        }
    }


    private fun changeToErrorPage() {
        webView.visibility = View.GONE
        layoutErrorPage.visibility = View.VISIBLE
    }

    private fun changeToNormalPage() {
        webView.visibility = View.VISIBLE
        layoutErrorPage.visibility = View.GONE
    }

}
