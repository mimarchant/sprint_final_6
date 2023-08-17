package com.example.sprint_final_6.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sprint_final_6.data.local.PhoneDao
import com.example.sprint_final_6.data.local.detail.PhoneDetailEntity
import com.example.sprint_final_6.data.local.list.PhoneListEntity


@Database(entities = [PhoneDetailEntity::class, PhoneListEntity::class], version = 1)
abstract class PhoneDatabase: RoomDatabase() {
    abstract fun getCellPhonesDAO(): PhoneDao

    companion object {
        @Volatile
        private var INSTANCE: PhoneDatabase? = null

        fun getDataBase(context: Context): PhoneDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneDatabase::class.java,
                    "cell_phones_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}