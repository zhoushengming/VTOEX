package com.ericcode.vtoex.data.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by xiaoming on 2017/12/28.
 */
data class Node(
        val id: Long?, //1
        val name: String?, //babel
        val url: String?, //http://www.v2ex.com/go/babel
        val title: String?, //Project Babel
        val title_alternative: String?, //Project Babel
        val topics: Long?, //1119
        val header: String?, //Project Babel - 帮助你在云平台上搭建自己的社区
        val footer: String?, //V2EX 基于 Project Babel 驱动。Project Babel 是用 Python 语言写成的，运行于 Google App Engine 云计算平台上的社区软件。Project Babel 当前开发分支 2.5。最新版本可以从 <a href="http://github.com/livid/v2ex" target="_blank">GitHub</a> 获取。
        val created: Long? //1272206882
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(title)
        parcel.writeString(title_alternative)
        parcel.writeValue(topics)
        parcel.writeString(header)
        parcel.writeString(footer)
        parcel.writeValue(created)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Node> {
        override fun createFromParcel(parcel: Parcel): Node {
            return Node(parcel)
        }

        override fun newArray(size: Int): Array<Node?> {
            return arrayOfNulls(size)
        }
    }
}

data class Topic(
        val id: Long?, //418297
        val title: String?, //请教个 Python mysql 数据过大 的问题
        val url: String?, //http://www.v2ex.com/t/418297
        val content: String?, //现在是这样的
        val content_rendered: String?,
        val replies: Long?, //1
        val member: Member?,
        val node: TopicInnerNode?,
        val created: Long?, //1514442772
        val last_modified: Long?, //1514442772
        val last_touched: Long? //1514424592
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readParcelable(Member::class.java.classLoader),
            parcel.readParcelable(TopicInnerNode::class.java.classLoader),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readValue(Long::class.java.classLoader) as? Long) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(content)
        parcel.writeString(content_rendered)
        parcel.writeValue(replies)
        parcel.writeParcelable(member, flags)
        parcel.writeParcelable(node, flags)
        parcel.writeValue(created)
        parcel.writeValue(last_modified)
        parcel.writeValue(last_touched)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Topic> {
        override fun createFromParcel(parcel: Parcel): Topic {
            return Topic(parcel)
        }

        override fun newArray(size: Int): Array<Topic?> {
            return arrayOfNulls(size)
        }
    }
}

data class Member(
        val id: Long?, //85325
        val username: String?, //Anybfans
        val tagline: String?,
        val avatar_mini: String?, ////v2ex.assets.uxengine.net/gravatar/1c54d8bb7c3e6ed3753ffcafa0ac4865?s=24&d=retro
        val avatar_normal: String?, ////v2ex.assets.uxengine.net/gravatar/1c54d8bb7c3e6ed3753ffcafa0ac4865?s=48&d=retro
        val avatar_large: String? ////v2ex.assets.uxengine.net/gravatar/1c54d8bb7c3e6ed3753ffcafa0ac4865?s=73&d=retro
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(username)
        parcel.writeString(tagline)
        parcel.writeString(avatar_mini)
        parcel.writeString(avatar_normal)
        parcel.writeString(avatar_large)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Member> {
        override fun createFromParcel(parcel: Parcel): Member {
            return Member(parcel)
        }

        override fun newArray(size: Int): Array<Member?> {
            return arrayOfNulls(size)
        }
    }
}

data class TopicInnerNode(
        val id: Long?, //12
        val name: String?, //qna
        val title: String?, //问与答
        val title_alternative: String?, //Questions and Answers
        val url: String?, //http://www.v2ex.com/go/qna
        val topics: Long?, //100140
        val avatar_mini: String?, ////v2ex.assets.uxengine.net/navatar/c20a/d4d7/12_mini.png?m=1513063412
        val avatar_normal: String?, ////v2ex.assets.uxengine.net/navatar/c20a/d4d7/12_normal.png?m=1513063412
        val avatar_large: String? ////v2ex.assets.uxengine.net/navatar/c20a/d4d7/12_large.png?m=1513063412
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(title)
        parcel.writeString(title_alternative)
        parcel.writeString(url)
        parcel.writeValue(topics)
        parcel.writeString(avatar_mini)
        parcel.writeString(avatar_normal)
        parcel.writeString(avatar_large)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TopicInnerNode> {
        override fun createFromParcel(parcel: Parcel): TopicInnerNode {
            return TopicInnerNode(parcel)
        }

        override fun newArray(size: Int): Array<TopicInnerNode?> {
            return arrayOfNulls(size)
        }
    }
}