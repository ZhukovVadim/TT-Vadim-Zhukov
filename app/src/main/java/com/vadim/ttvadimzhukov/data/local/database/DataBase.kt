package com.vadim.ttvadimzhukov.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.vadim.ttvadimzhukov.data.local.database.dao.CategoryDao
import com.vadim.ttvadimzhukov.data.local.entity.CategoryEntity
import com.vadim.ttvadimzhukov.utils.DataBaseConverter

@Database(entities = [(CategoryEntity::class)], version = 1, exportSchema = false)
@TypeConverters(DataBaseConverter::class)
abstract class DataBase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao

}