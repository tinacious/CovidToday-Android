package com.tinaciousdesign.covidtoday.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tinaciousdesign.covidtoday.R
import com.tinaciousdesign.covidtoday.adapters.CountriesAdapter
import com.tinaciousdesign.covidtoday.databinding.FragmentPageBinding
import com.tinaciousdesign.covidtoday.viewmodels.MainViewModel


class PageFragment private constructor(): Fragment() {

//    private lateinit var testingText: TextView
    private lateinit var mMainViewModel: MainViewModel

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: CountriesAdapter

    private var _binding: FragmentPageBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "PageFragment"
        const val BUNDLE_EXTRA_POSITION = "position"

        private var currentPosition: Int = -1

        fun getInstance(position: Int): Fragment {
            val pageFragment = PageFragment()
            val bundle = Bundle()
            bundle.putInt(BUNDLE_EXTRA_POSITION, position)
            pageFragment.arguments = bundle
            return pageFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = requireArguments().getInt(BUNDLE_EXTRA_POSITION)
        currentPosition = position

//        val tab = getTabs(requireContext())[position]
//        Log.d(TAG, "tab: $tab")

//        testingText = view.findViewById(R.id.testingText)
//        testingText.text = "tab: $tab at $position"

        initViews(view)

        mMainViewModel = ViewModelProvider(requireActivity())
            .get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPageBinding.inflate(inflater, container, false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initObservers()
    }

    private fun initObservers() {
        mMainViewModel.countries.observe(requireActivity(), {
            Log.d(TAG, "Here 2 - ${it?.size}")
            mAdapter.updateData(it)

            if (it.isNotEmpty()) {
               binding.loader.visibility = View.GONE
            } else {
               binding.loader.visibility = View.VISIBLE
            }
        })
    }

    private fun initViews(view: View) {
        mRecyclerView = binding.countriesList
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        mAdapter = CountriesAdapter()
        mRecyclerView.adapter = mAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
