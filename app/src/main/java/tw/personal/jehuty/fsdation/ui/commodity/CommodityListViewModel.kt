package tw.personal.jehuty.fsdation.ui.commodity

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import tw.personal.jehuty.fsdation.domain.GetInfoUseCase
import tw.personal.jehuty.fsdation.ui.commodity.adapter.pagesource.CommodityPageSource
import tw.personal.jehuty.fsdation.ui.commodity.adapter.pagesource.NETWORK_PAGE_SIZE

import javax.inject.Inject
@HiltViewModel
class CommodityListViewModel @Inject constructor(
    val getInfoUseCase: GetInfoUseCase

) : ViewModel() {
    var position = 0
    var getInfoResult = Pager(
    config = PagingConfig(
    pageSize = NETWORK_PAGE_SIZE,
    enablePlaceholders = false
    )
    ){
        CommodityPageSource(getInfoUseCase)
            .apply { fragmentPosition = position }
    }.liveData

}