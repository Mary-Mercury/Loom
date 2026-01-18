package com.example.loom.Data.datastore

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSoreModule {

    @Provides
    @Singleton
    fun provideDataStoreUtils(@ApplicationContext context: Context): DataStoreUtil = DataStoreUtil(context)
}