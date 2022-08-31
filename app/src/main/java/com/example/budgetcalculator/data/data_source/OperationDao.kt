package com.example.budgetcalculator.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.budgetcalculator.domain.model.Operation
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDao {

    @Query("SELECT * FROM operation")
    fun getOperations(): Flow<List<Operation>>

    @Query("SELECT * FROM operation WHERE id = :id")
    fun getOperationById(id: Int): Operation?

    @Insert
    suspend fun addOperation(operation: Operation): Long

    @Delete
    suspend fun deleteOperation(operation: Operation)

}