package com.ericcode.vtoex

import android.app.Application
import com.ericcode.vtoex.extention.mTag
import com.ericcode.vtoex.util.Logger
import kotlin.properties.Delegates

/**
 * Created by xiaoming on 2017/12/28.
 * app
 */
class App : Application() {

    companion object {
        var ins: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        ins = this
        Logger.i(mTag, mTag)
    }
}