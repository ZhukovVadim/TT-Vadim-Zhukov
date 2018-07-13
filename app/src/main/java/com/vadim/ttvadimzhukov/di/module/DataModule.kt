package com.vadim.ttvadimzhukov.di.module


import android.arch.persistence.room.Room
import android.content.Context
import com.vadim.ttvadimzhukov.data.local.database.DataBase
import com.vadim.ttvadimzhukov.data.DataManager
import com.vadim.ttvadimzhukov.data.remote.TTApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [(RetrofitModule::class)])
class DataModule {

    @Provides
    @Singleton
    fun provideDataManager(restApi: TTApi, db: DataBase): DataManager {
        return DataManager(restApi, db)
    }


    @Provides
    @Singleton
    fun provideAuctionApi(retrofit: Retrofit): TTApi = retrofit.create(TTApi::class.java)


    @Provides
    @Singleton
    fun provideDataBase(context: Context): DataBase = Room.databaseBuilder(
        context, DataBase::class.java,
        "TTDataBase"
    ).allowMainThreadQueries().build()
}