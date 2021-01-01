package com.tinaciousdesign.covidtoday.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tinaciousdesign.covidtoday.R
import com.tinaciousdesign.covidtoday.data.getTabs
import com.tinaciousdesign.covidtoday.viewmodels.MainViewModel

class PageFragment private constructor(): Fragment() {

    private lateinit var testingText: TextView
    private lateinit var mMainViewModel: MainViewModel

    companion object {
        private const val TAG = "PageFragment"
        const val BUNDLE_EXTRA_POSITION = "position"

        fun getInstance(position: Int): Fragment {
            val pageFragment = PageFragment()
            val bundle = Bundle()
            bundle.putInt(BUNDLE_EXTRA_POSITION, position)
            pageFragment.arguments = bundle
            return pageFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = requireArguments().getInt(BUNDLE_EXTRA_POSITION)

        testingText = view.findViewById(R.id.testingText)

        val tab = getTabs(requireContext())[position]
        Log.d(TAG, "tab: $tab")

        testingText.text = "tab: $tab at $position"

        mMainViewModel = ViewModelProvider(requireActivity())
            .get(MainViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initObservers()
        initViews()
    }

    private fun initObservers() {
        mMainViewModel.countries.observe(requireActivity(), {
            Log.d(TAG, "Here 2 - ${it?.size}")
        })
    }

    private fun initViews() {
        // TODO: Adapter things
    }
}
