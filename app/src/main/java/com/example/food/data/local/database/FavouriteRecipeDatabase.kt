package com.example.food.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.food.data.local.dao.FavouriteRecipeDao
import com.example.food.domain.local.model.FavouriteRecipeEntity

@Database(entities = [FavouriteRecipeEntity::class], version = 1)
abstract class FavouriteRecipeDatabase : RoomDatabase() {
    abstract fun favouriteRecipeDao(): FavouriteRecipeDao
}