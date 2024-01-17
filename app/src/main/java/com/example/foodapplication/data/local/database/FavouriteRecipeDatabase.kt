package com.example.foodapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodapplication.data.local.dao.FavouriteRecipeDao
import com.example.foodapplication.domain.local.model.FavouriteRecipeEntity

@Database(entities = [FavouriteRecipeEntity::class], version = 1)
abstract class FavouriteRecipeDatabase : RoomDatabase() {
    abstract fun favouriteRecipeDao(): FavouriteRecipeDao
}