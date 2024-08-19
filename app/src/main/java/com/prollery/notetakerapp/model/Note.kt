package com.prollery.notetakerapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tblNotes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0,
    var title: String,
    var description: String,
    var createdTimestamp: String,
    var createdTimestampInMillis: Long,
    var modifiedTimestamp: String,
    var modifiedTimestampInMillis: Long
) : Parcelable