package tw.personal.jehuty.fsdation.dagger.module

import com.squareup.moshi.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import tw.personal.jehuty.fsdation.util.IntToCommodityTypeEnumAdapter
import tw.personal.jehuty.fsdation.util.StringToIntAdapter
import tw.personal.jehuty.fsdation.util.StringToNullIntAdapter
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class JsonModule {

    @Singleton
    @Provides
    fun provideStringToIntAdapter(): StringToIntAdapter = StringToIntAdapter()

    @Singleton
    @Provides
    fun provideIntToCommodityTypeEnumAdapter(): IntToCommodityTypeEnumAdapter =
        IntToCommodityTypeEnumAdapter()

    @Singleton
    @Provides
    fun provideStringToNullIntAdapter(): StringToNullIntAdapter = StringToNullIntAdapter()

    @Singleton
    @Provides
    fun provideMoshi(
        stringToIntAdapter: StringToIntAdapter,
        stringToBasicIsoDateAdapter: IntToCommodityTypeEnumAdapter,
        stringToNullIntAdapter: StringToNullIntAdapter
    ): Moshi = Moshi.Builder()
        .add(stringToIntAdapter)
        .add(stringToBasicIsoDateAdapter)
        .add(stringToNullIntAdapter)
        .build()
}