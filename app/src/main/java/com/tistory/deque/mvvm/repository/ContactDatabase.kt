package com.tistory.deque.mvvm.repository

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.impl.WorkDatabaseMigrations
import com.tistory.deque.mvvm.model.CardItem
import com.tistory.deque.mvvm.model.Cat
import com.tistory.deque.mvvm.model.MultiTypeModel
import com.tistory.deque.mvvm.repository.ContactDatabase.Companion.DB_VERSION
import com.tistory.deque.mvvm.repository.dao.*
import com.tistory.deque.mvvm.util.CatDBWoker

@Database(entities = [Bookmark::class, Contact::class, Cat::class, MultiTypeModel::class, CardItem::class], version = DB_VERSION, exportSchema = true)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun getContactDao(): ContactDao
    abstract fun getBookmarkDao(): BookmarkDao
    abstract fun getCatDao(): CatDao
    abstract fun getMultiTypeDao(): MultiTypeDao
    abstract fun getCardItemDao(): CardItemDao

    companion object {
        const val DB_VERSION = 7
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

                        Log.e("CHEK DB GENERATOR", "DB GENERATE")
                        val request = OneTimeWorkRequest.Builder(CatDBWoker::class.java).build()
                        WorkManager.getInstance().enqueue(request)
                    }
                })
//                .addMigrations(MIGRATION_2_TO_3)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

        private val MIGRATION_2_TO_3 = object : Migration(6, 7) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.e("CHEK DB GENERATOR", "DB GENERATE into migrate")
            }
        }
    }
}