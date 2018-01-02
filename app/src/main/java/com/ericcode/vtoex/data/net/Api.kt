package com.ericcode.vtoex.data.net

import com.ericcode.vtoex.config.Const
import com.ericcode.vtoex.data.bean.Node
import com.ericcode.vtoex.data.bean.Topic
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

/**
 * Created by xiaoming on 2017/12/28.
 * network api
 */
class Api private constructor() {
    companion object {
        val ins: Api by lazy {
            Hold.ins
        }
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(chain.request())
                }
                .build()
    }
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//新的配置
                .baseUrl(Const.BaseUrl)
                .build()
    }

    private val netServices: NetServices by lazy {
        retrofit.create(NetServices::class.java)
    }


    private object Hold {
        var ins: Api = Api()
    }

    fun getAllNodes(): Observable<List<Node>> = netServices.allNodes()

    fun getLatestTopics(): Observable<List<Topic>> = netServices.latestTopics()

    fun getHotTopics(): Observable<List<Topic>> = netServices.hotTopics()
}