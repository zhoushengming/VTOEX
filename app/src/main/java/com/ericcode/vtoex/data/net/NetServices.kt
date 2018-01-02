package com.ericcode.vtoex.data.net

import com.ericcode.vtoex.data.bean.Node
import com.ericcode.vtoex.data.bean.Topic
import retrofit2.http.GET
import rx.Observable

/**
 * Created by xiaoming on 2017/12/28.
 * api services
 */
interface NetServices {

    @GET("api/nodes/all.json")
    fun allNodes(): Observable<List<Node>>

    @GET("api/topics/latest.json")
    fun latestTopics(): Observable<List<Topic>>

    @GET("api/topics/hot.json")
    fun hotTopics(): Observable<List<Topic>>
}