package com.example.simpletodolist.room

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, "ToDo")
            .addMigrations(migration1_2)
            .build()

    @Singleton
    @Provides
    fun provideDAO(appDataBase: AppDataBase) = appDataBase.ToDoDAO()

    private val migration1_2 = object : Migration(1, 2){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE 'ToDoList' ADD COLUMN 'hour'  INTEGER NOT NULL DEFAULT 0")
            database.execSQL("ALTER TABLE 'ToDoList' ADD COLUMN 'minute' INTEGER NOT NULL DEFAULT 0")
        }
    }
}