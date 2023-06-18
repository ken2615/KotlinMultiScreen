package com.lduboscq.appkickstarter

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.flow.Flow

interface FrogRepository {
    suspend fun getFrog(frogName : String): FrogData?
    suspend fun addFrog(frogData : FrogData): FrogData?
    suspend fun updateFrog(frogName : String): FrogData?
    suspend fun deleteFrog(frogName : String): FrogData?
    suspend fun getAllFrogsList(frogName: String?): List<FrogData>
    suspend fun getAllFrogs(frogName: String?): MutableState<List<FrogData>>
    suspend fun getAllFrogsFlow(frogName: String?): Flow<List<FrogData>>
}