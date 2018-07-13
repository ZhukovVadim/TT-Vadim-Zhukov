package com.vadim.ttvadimzhukov.data.local.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.vadim.ttvadimzhukov.data.local.entity.CategoryEntity
import io.reactivex.Flowable

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: CategoryEntity)

    @Update()
    fun update(entity: CategoryEntity)

    @Delete
    fun delete(entity: CategoryEntity)

    @Query("SELECT * FROM  CATEGORY")
    fun getAll(): List<CategoryEntity>

    @Query("SELECT * FROM CATEGORY WHERE name = :categoryName")
    fun getCategory(categoryName: String): CategoryEntity?
}