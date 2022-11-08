package com.example.groceryapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.groceryapp.manager.ApiService
import com.example.groceryapp.paging.GroceryPagingSource
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiService: ApiService) {
     fun getGroceries() = Pager(config = PagingConfig(10, maxSize = 100),
    pagingSourceFactory = {GroceryPagingSource(apiService)}).liveData
}