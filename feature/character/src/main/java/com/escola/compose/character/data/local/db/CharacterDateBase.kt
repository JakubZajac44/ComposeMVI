package com.escola.compose.character.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.escola.compose.character.data.local.dao.CharacterDao
import com.escola.compose.character.data.local.dao.CharacterKeyDao
import com.escola.compose.character.data.local.model.CharacterEntity
import com.escola.compose.character.data.local.model.CharacterRemoteKeyEntity

private const val CHARACTER_DB_NAME = "character_database"

@Database(
    entities = [CharacterEntity::class, CharacterRemoteKeyEntity:: class],
    version = 1
)
abstract class CharacterDateBase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun characterRemoteKeyDao(): CharacterKeyDao


    companion object {
        fun create(context: Context): CharacterDateBase {

            return Room.databaseBuilder(
                context,
                CharacterDateBase::class.java,
                CHARACTER_DB_NAME
            ).build()
        }
    }

}