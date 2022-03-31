package tw.personal.jehuty.fsdation.dagger.module


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import tw.personal.jehuty.fsdation.data.AppRepository
import tw.personal.jehuty.fsdation.data.repository.CommodityRepository
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCommodityRepository(commodityRepository: CommodityRepository): AppRepository


}