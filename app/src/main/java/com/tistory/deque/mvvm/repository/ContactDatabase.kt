package com.tistory.deque.mvvm.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.tistory.deque.mvvm.model.Cat
import com.tistory.deque.mvvm.repository.ContactDatabase.Companion.DB_VERSION
import com.tistory.deque.mvvm.repository.dao.BookmarkDao
import com.tistory.deque.mvvm.repository.dao.CatDao
import com.tistory.deque.mvvm.repository.dao.ContactDao
import com.tistory.deque.mvvm.util.CatDBWoker

@Database(entities = [Bookmark::class, Contact::class, Cat::class], version = DB_VERSION, exportSchema = false)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun getContactDao(): ContactDao
    abstract fun getBookmarkDao(): BookmarkDao
    abstract fun getCatDao(): CatDao

    companion object {
        const val DB_VERSION = 2
        private const val DB_NAME = "eds.database"
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, ContactDatabase::class.java, DB_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        val request = OneTimeWorkRequest.Builder(CatDBWoker::class.java).build()
                        WorkManager.getInstance().enqueue(request)
                    }
                })
                .addMigrations(MIGRATION_1_TO_2)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

        private val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}