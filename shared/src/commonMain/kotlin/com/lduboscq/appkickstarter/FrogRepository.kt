package com.lduboscq.appkickstarter

interface FrogRepository {
    suspend fun getFrog(frogName : String): FrogData?
    suspend fun addFrog(frogData : FrogData): FrogData?
    suspend fun updateFrog(frogData : String): FrogData?
    suspend fun deleteFrog(frogData : String): FrogData?
}