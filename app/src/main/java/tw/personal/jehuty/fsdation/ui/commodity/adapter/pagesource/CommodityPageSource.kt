package tw.personal.jehuty.fsdation.ui.commodity.adapter.pagesource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.hilt.android.scopes.ActivityScoped
import tw.personal.jehuty.fsdation.data.remote.CommodityService
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItem
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItemRequest
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItemResponse
import tw.personal.jehuty.fsdation.domain.GetInfoUseCase
import java.lang.Exception
import javax.inject.Inject

const val NETWORK_PAGE_SIZE = 10
private const val INITIAL_LOAD_SIZE = 1

class CommodityPageSource(private val getInfoUseCase: GetInfoUseCase) :
    PagingSource<Int, CommodityItem>() {
    var fragmentPosition = 0
    override fun getRefreshKey(state: PagingState<Int, CommodityItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommodityItem> {
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = 10
        return try {
            val result = getInfoUseCase(
                CommodityItemRequest(
                    type = fragmentPosition,
                    page = position,
                    offset = offset
                )
            )
            val nextKey = if (result.commodityList.isEmpty()) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = result.commodityList,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}