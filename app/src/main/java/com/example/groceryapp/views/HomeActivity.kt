package com.example.groceryapp.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.adapters.GroceryAdapter
import com.example.groceryapp.databinding.ActivityHomeBinding
import com.example.groceryapp.model.Record
import com.example.groceryapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val mainViewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    var bottomSheet: SortDialogFragment? = null

    var recordPagingData: PagingData<Record>? = null
    var filterBottomSheet: FilterDialogFragment? = null
    lateinit var adapter: GroceryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        mainViewModel.list.observe(this) {
            recordPagingData = it
            adapter.submitData(lifecycle, it)


        }
    }

    fun initViews() {
        adapter = GroceryAdapter()
        binding.groceryRV.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.groceryRV.setHasFixedSize(true)
        binding.groceryRV.adapter = adapter
        bottomSheet = SortDialogFragment();
        filterBottomSheet = FilterDialogFragment();
        binding.sortingTV.setOnClickListener {
            bottomSheet!!.show(
                supportFragmentManager,
                "ModalBottomSheet"
            );
        }
        binding.filterTV.setOnClickListener {
            filterBottomSheet!!.show(
                supportFragmentManager,
                "ModalBottomSheet"
            );
        }
        bottomSheet?.onSortItemClick = {
            bottomSheet?.dismiss()}
        filterBottomSheet?.onFilterItemClick = { filterData, filterText ->
            val data: PagingData<Record>? = recordPagingData?.filter {
                if (filterData.toLowerCase() == "district") {
                    it.district.contains(filterText)
                } else {
                    it.market.contains(filterText)
                }

            }
            if (data != null) {
                adapter.submitData(lifecycle, data)
            }
            filterBottomSheet?.dismiss()
        }
    }

}