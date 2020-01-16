package com.tistory.deque.mvvm.repository

import androidx.room.*
import com.tistory.deque.mvvm.model.Repository
import com.tistory.deque.mvvm.util.DateConverter
import java.util.*

/**
 * @author Leopold
 */
@TypeConverters(DateConverter::class)
@Entity(tableName = "Bookmark")
data class Bookmark(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "language") val language: String?,
    @ColumnInfo(name = "stargazers_count") val stargazersCount: Int,
    @ColumnInfo(name = "created") val created: Date
) {
    companion object {
        fun to(repository: Repository): Bookmark {
            return Bookmark(
                name = repository.name,
                description = repository.description,
                language = repository.language,
                stargazersCount = repository.stargazersCount,
                created = Date()
            )
        }
    }
}