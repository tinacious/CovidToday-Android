package com.tinaciousdesign.covidtoday.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tinaciousdesign.covidtoday.adapters.CountriesAdapter
import com.tinaciousdesign.covidtoday.data.Country
import com.tinaciousdesign.covidtoday.data.TabIndex
import com.tinaciousdesign.covidtoday.databinding.FragmentPageBinding
import com.tinaciousdesign.covidtoday.utils.CountriesDiffUtilCallback
import com.tinaciousdesign.covidtoday.viewmodels.MainViewModel
import kotlinx.coroutines.*


class PageFragment private constructor() : Fragment() {

    private lateinit var mMainViewModel: MainViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: CountriesAdapter

    private var _binding: FragmentPageBinding? = null
    private val binding get() = _binding!!

//    private var mTabContentLookup: Map<Int, List<Country>>? = null

    companion object {
        private const val TAG = "PageFragment"
        const val BUNDLE_EXTRA_POSITION = "position"

        private var currentPosition: Int = -1

        fun getInstance(position: Int): Fragment {
            // TODO: Perhaps create the sorted data here?
            val pageFragment = PageFragment()
            val bundle = Bundle()
            bundle.putInt(BUNDLE_EXTRA_POSITION, position)
            pageFragment.arguments = bundle
            return pageFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentPosition = requireArguments().getInt(BUNDLE_EXTRA_POSITION)

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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initObservers()
    }

    private fun initObservers() {
        // Not sure if I should have this
        binding.loader.visibility = View.VISIBLE
//        binding.loader.visibility = View.GONE
        mAdapter.updateData(listOf())

        mMainViewModel.countries.observe(requireActivity(), {
//            val sorted = sortCountriesForTab(it)
//            mAdapter.updateData(sorted)

            // Sync
//            val sorted = sortCountriesForTab(it)
//            mAdapter.updateData(sorted)
//
//            if (it.isNotEmpty()) {
//                binding.loader.visibility = View.GONE
//            } else {
//                binding.loader.visibility = View.VISIBLE
//            }

            // Async without DiffUtil
//            GlobalScope.launch(Dispatchers.IO) {
//                Log.i(TAG, "GlobalScope.launch(Dispatchers.IO) called")
//                val sorted = sortCountriesForTab(it)
//
//                launch(Dispatchers.Main) {
//                    Log.d(TAG, "launch(Dispatchers.Main) called")
//                    mAdapter.updateData(sorted)
//
//                    if (it.isNotEmpty()) {
//                        binding.loader.visibility = View.GONE
//                    } else {
//                        binding.loader.visibility = View.VISIBLE
//                    }
//                }
//            }

            // Async with DiffUtil
            GlobalScope.launch(Dispatchers.IO) {
                val oldItems = mAdapter.data
                val sorted = sortCountriesForTab(it)
                val result = DiffUtil.calculateDiff(CountriesDiffUtilCallback(oldItems, sorted))

                launch(Dispatchers.Main) {
                    delay(600)
                    mAdapter.data = sorted as MutableList<Country>
                    result.dispatchUpdatesTo(mAdapter)

                    if (it.isNotEmpty()) {
                        binding.loader.visibility = View.GONE
                    } else {
                        binding.loader.visibility = View.VISIBLE
                    }
                }
            }

            // Sync with DiffUtil
//            val oldItems = mAdapter.data
//            val sorted = sortCountriesForTab(it)
//            val result = DiffUtil.calculateDiff(CountriesDiffUtilCallback(oldItems, sorted))
//
//            mAdapter.data = sorted as MutableList<Country>
//            result.dispatchUpdatesTo(mAdapter)
//            if (it.isNotEmpty()) {
//                binding.loader.visibility = View.GONE
//            }
//            else {
//                binding.loader.visibility = View.VISIBLE
//            }
        })

        // Async end
}

    private fun initViews(view: View) {
        mRecyclerView = binding.countriesList
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        mAdapter = CountriesAdapter()
        mAdapter.setHasStableIds(true)
        mRecyclerView.adapter = mAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun buildLookupForTab(countries: List<Country>): Map<Int, List<Country>> =
//        mapOf(
//            TabIndex.CASES_TODAY.ordinal to countries.sortedByDescending { it.todayCases },
//            TabIndex.DEATHS_TODAY.ordinal to countries.sortedByDescending { it.todayDeaths },
//            TabIndex.ALL_CASES.ordinal to countries.sortedByDescending { it.cases },
//            TabIndex.ALL_DEATHS.ordinal to countries.sortedByDescending { it.deaths },
//            TabIndex.RECOVERED.ordinal to countries.sortedByDescending { it.recovered },
//            TabIndex.ACTIVE.ordinal to countries.sortedByDescending { it.active },
//            TabIndex.CRITICAL.ordinal to countries.sortedByDescending { it.critical },
//            TabIndex.CASES_PER_MILLION.ordinal to countries.sortedByDescending { it.casesPerOneMillion },
//            TabIndex.DEATHS_PER_MILLION.ordinal to countries.sortedByDescending { it.deathsPerOneMillion },
//        )

    private fun sortCountriesForTab(countries: List<Country>): List<Country> =
        when (currentPosition) {
            TabIndex.CASES_TODAY.ordinal -> countries.sortedByDescending { it.todayCases }
            TabIndex.DEATHS_TODAY.ordinal -> countries.sortedByDescending { it.todayDeaths }
            TabIndex.ALL_CASES.ordinal -> countries.sortedByDescending { it.cases }
            TabIndex.ALL_DEATHS.ordinal -> countries.sortedByDescending { it.deaths }
            TabIndex.RECOVERED.ordinal -> countries.sortedByDescending { it.recovered }
            TabIndex.ACTIVE.ordinal -> countries.sortedByDescending { it.active }
            TabIndex.CRITICAL.ordinal -> countries.sortedByDescending { it.critical }
            TabIndex.CASES_PER_MILLION.ordinal -> countries.sortedByDescending { it.casesPerOneMillion }
            TabIndex.DEATHS_PER_MILLION.ordinal -> countries.sortedByDescending { it.deathsPerOneMillion }
            else -> countries
        }


}
