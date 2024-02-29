package com.example.todolistjetpackcompose.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolistjetpackcompose.data.local.TodoDatabase
import com.example.todolistjetpackcompose.data.local.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "todo_database"

    @Provides
    fun provideTodoDao(database: TodoDatabase): TodoDao {
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRoom(
        @ApplicationContext appContext: Context
    ): TodoDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            TodoDatabase::class.java,
            DATABASE_NAME
            ).build()
    }
}