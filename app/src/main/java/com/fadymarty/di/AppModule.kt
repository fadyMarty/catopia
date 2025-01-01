package com.fadymarty.di

import android.app.Application
import androidx.room.Room
import com.fadymarty.catopia.data.local.CatDatabase
import com.fadymarty.catopia.data.manger.LocalUserMangerImpl
import com.fadymarty.catopia.data.remote.TheCatApi
import com.fadymarty.catopia.data.repository.CatRepositoryImpl
import com.fadymarty.catopia.domain.manager.LocalUserManger
import com.fadymarty.catopia.domain.repository.CatRepository
import com.fadymarty.catopia.domain.use_case.app_entry.AppEntryUseCases
import com.fadymarty.catopia.domain.use_case.app_entry.ReadAppEntry
import com.fadymarty.catopia.domain.use_case.cat_pictures.CatPicturesUseCases
import com.fadymarty.catopia.domain.use_case.cat_pictures.DeleteCatPictureUseCase
import com.fadymarty.catopia.domain.use_case.cat_pictures.GetCatPicturesUseCase
import com.fadymarty.catopia.domain.use_case.cat_pictures.SelectCatPictureUseCase
import com.fadymarty.catopia.domain.use_case.cat_pictures.SelectCatPicturesUseCase
import com.fadymarty.catopia.domain.use_case.cat_pictures.UpsertCatPictureUseCase
import com.fadymarty.core.util.Constants.BASE_URL
import com.loc.newsapp.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTheCatApi(): TheCatApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheCatApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCatDatabase(
        application: Application,
    ): CatDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = CatDatabase::class.java,
            name = "cat_photos_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCatRepository(
        theCatApi: TheCatApi,
        catDatabase: CatDatabase,
    ): CatRepository {
        return CatRepositoryImpl(
            theCatApi = theCatApi,
            catDao = catDatabase.catDao
        )
    }

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application,
    ): LocalUserManger = LocalUserMangerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger,
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun provideCatPicturesUseCases(
        catRepository: CatRepository,
    ): CatPicturesUseCases {
        return CatPicturesUseCases(
            getCatPicturesUseCase = GetCatPicturesUseCase(catRepository),
            upsertCatPictureUseCase = UpsertCatPictureUseCase(catRepository),
            deleteCatPictureUseCase = DeleteCatPictureUseCase(catRepository),
            selectCatPicturesUseCase = SelectCatPicturesUseCase(catRepository),
            selectCatPictureUseCase = SelectCatPictureUseCase(catRepository)
        )
    }
}