package com.ericcode.vtoex.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ericcode.vtoex.App
import com.ericcode.vtoex.R
import com.ericcode.vtoex.config.Const
import com.ericcode.vtoex.data.bean.Node
import com.ericcode.vtoex.view.FeedListFragment
import java.util.ArrayList

/**
 * Created by xiaoming on 2017/12/28.
 * adapter
 */
class PagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    private val nodes: MutableList<Node> by lazy {
        ArrayList<Node>()
    }

    private val latestNode: Node by lazy {
        Node(Const.LatestNodeId,
                App.ins.getString(R.string.latest),
                "",
                App.ins.getString(R.string.latest),
                "",
                -1,
                "",
                "",
                -1)
    }

    fun updateNodes(nodes: List<Node>?) {
        if (nodes == null) {
            return
        }
        this.nodes.clear()
        this.nodes.add(0, latestNode)
        this.nodes.addAll(nodes)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return FeedListFragment.newInstance("", "")
        } else {
            return FeedListFragment.newInstance("", "")
        }
    }

    override fun getCount(): Int = this.nodes.size

    override fun getPageTitle(position: Int): CharSequence? = nodes[position].title
}