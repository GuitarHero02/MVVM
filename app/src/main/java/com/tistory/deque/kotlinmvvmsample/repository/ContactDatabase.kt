package com.tistory.deque.kotlinmvvmsample.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tistory.deque.kotlinmvvmsample.repository.ContactDatabase.Companion.DB_VERSION
import com.tistory.deque.kotlinmvvmsample.repository.dao.ContactDao

@Database(entities = [Contact::class], version = DB_VERSION, exportSchema = false)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun getContactDao(): ContactDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "eds.database"
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, ContactDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

        private val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}