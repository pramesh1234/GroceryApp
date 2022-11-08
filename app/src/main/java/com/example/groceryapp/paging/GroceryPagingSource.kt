package com.example.groceryapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.groceryapp.manager.ApiService
import com.example.groceryapp.model.Record

class GroceryPagingSource(val apiService: ApiService) : PagingSource<String, Record>() {
    override fun getRefreshKey(state: PagingState<String, Record>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.toInt()?.plus(1).toString() ?: state.closestPageToPosition(anchorPosition)?.nextKey?.toInt()?.minus(1).toString()
        }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Record> {
        return try {
            val position = params.key ?: "0"
            val response = apiService.getGroceries(
                "579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b",
                "json",
                position
            )
           response
                .let {
                    LoadResult.Page(
                        data = it.body()?.records!!,
                        prevKey = if (position == "0") null else (position.toInt() - 1).toString(),
                        nextKey = if (position.toInt() == it.body()?.total?.div(10)) null else (position.toInt() + 1).toString()
                    )
                }
        } catch (e: Exception) {
           LoadResult.Error(e)
        }
    }
}