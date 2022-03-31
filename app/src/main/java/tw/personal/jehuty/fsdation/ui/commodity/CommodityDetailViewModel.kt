package tw.personal.jehuty.fsdation.ui.commodity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tw.personal.jehuty.fsdation.data.remote.model.CommodityDetailResponse
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItem
import tw.personal.jehuty.fsdation.data.vo.Result
import tw.personal.jehuty.fsdation.domain.GetCommodityDetailUseCase
import tw.personal.jehuty.fsdation.domain.GetInfoUseCase
import tw.personal.jehuty.fsdation.util.SingleLiveEvent
import javax.inject.Inject
@HiltViewModel
class CommodityDetailViewModel @Inject constructor(
    val getCommodityDetailUseCase : GetCommodityDetailUseCase
) : ViewModel() {

    val getCommodityDetailResult = SingleLiveEvent<Result<CommodityDetailResponse>>()

    fun getCommodityDetail(id:String) = viewModelScope.launch{
        getCommodityDetailResult.value = Result.Loading
        delay(1000)

        try{
            val  response= getCommodityDetailUseCase(id)

            getCommodityDetailResult.value = Result.Success(response)
        }catch (e:Exception){
            getCommodityDetailResult.value = Result.Error(e)
        }

    }

}