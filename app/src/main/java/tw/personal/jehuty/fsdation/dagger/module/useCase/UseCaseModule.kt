package tw.personal.jehuty.fsdation.dagger.module.useCase
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import tw.personal.jehuty.fsdation.data.remote.model.CommodityDetailResponse
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItem
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItemRequest
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItemResponse
import tw.personal.jehuty.fsdation.domain.AppUseCase
import tw.personal.jehuty.fsdation.domain.GetCommodityDetailUseCase
import tw.personal.jehuty.fsdation.domain.GetInfoUseCase
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class UseCaseModule {
    @Singleton
    @Binds
    abstract fun bindGetInfoUseCase(getInfoUseCase: GetInfoUseCase): AppUseCase<CommodityItemRequest, CommodityItemResponse>
    @Singleton
    @Binds
    abstract fun bindGetCommodityDetailUseCase(getCommodityDetailUseCase: GetCommodityDetailUseCase): AppUseCase<String, CommodityDetailResponse>
}