package com.ericcode.vtoex.view

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ericcode.vtoex.R
import com.ericcode.vtoex.data.net.Api
import com.ericcode.vtoex.util.Logger
import com.ericcode.vtoex.view.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_feed_list.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.ref.WeakReference

/**
 * feed
 */
class FeedListFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_feed_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
//                super.getItemOffsets(outRect, view, parent, state)
                var position = parent?.getChildAdapterPosition(view)
                if (position == 0) {
                    outRect?.set(0, 1, 1, 1)
                } else {
                    outRect?.set(0, 0, 1, 1)
                }
            }
        })
        val adapter = RecyclerViewAdapter(WeakReference(this.activity))
        recyclerView.adapter = adapter

        Api.ins.getHotTopics()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { result ->
                            Logger.i(result.size.toString())
                            adapter.updateTopics(result)
                        },
                        { throwable -> Logger.printException(throwable) }
                )
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Logger.i("onAttach")

    }

    override fun onDetach() {
        Logger.i("onDetach")
        super.onDetach()
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FeedListFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): FeedListFragment {
            val fragment = FeedListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
