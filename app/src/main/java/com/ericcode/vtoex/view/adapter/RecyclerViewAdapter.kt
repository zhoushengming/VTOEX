package com.ericcode.vtoex.view.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.ericcode.vtoex.R
import com.ericcode.vtoex.data.bean.Topic
import com.ericcode.vtoex.extention.lossTimeCN
import com.ericcode.vtoex.extention.textInVisible
import com.ericcode.vtoex.util.Logger
import com.ericcode.vtoex.view.WebShowActivity
import kotlinx.android.synthetic.main.item_topic.view.*
import java.lang.ref.WeakReference


/**
 * Created by xiaoming on 2017/12/28.
 * adapter
 */
class RecyclerViewAdapter(val weakReference: WeakReference<Activity>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private val topics: ArrayList<Topic> by lazy {
        ArrayList<Topic>()
    }

    fun updateTopics(topics: List<Topic>?) {
        if (topics == null) {
            return
        }

        this.topics.clear()
        this.topics.addAll(topics)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        with(holder?.itemView!!) {
            with(topics[position]) {
                tvContent.text = title
                tvNode.textInVisible = node?.name
                tvUser.textInVisible = member?.username
                tvTimeLoss.textInVisible = last_modified?.lossTimeCN
                tvLatestCommit.textInVisible = null
                tvCommitNumber.textInVisible = replies?.toString()
                rlRoot.setOnClickListener {
                    Logger.i(url)
                    WebShowActivity.startMe(weakReference.get(), this)
                }
            }
        }


    }

    override fun getItemCount(): Int = topics.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(View.inflate(parent?.context, R.layout.item_topic, null))
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}

