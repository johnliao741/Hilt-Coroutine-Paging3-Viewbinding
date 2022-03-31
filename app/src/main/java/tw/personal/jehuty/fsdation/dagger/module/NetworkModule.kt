package tw.personal.jehuty.fsdation.dagger.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.wifi.p2p.WifiP2pManager
import android.util.Base64
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tw.personal.jehuty.fsdation.BuildConfig
import tw.personal.jehuty.fsdation.data.remote.CommodityService
import tw.personal.jehuty.fsdation.data.remote.mock.MockCommodityService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideCommodityService(retrofit: Retrofit): CommodityService =
        if(BuildConfig.DEBUG) MockCommodityService()
        else retrofit.create(CommodityService::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://xxx.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
}