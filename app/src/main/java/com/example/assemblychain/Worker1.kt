package com.example.assemblychain

import kotlinx.coroutines.*

class Worker1 {
    private val units = mutableListOf<Deferred<Int>>()
    suspend fun doWork(){
            units.add(makeUnitsAsync())
            delay(3000)
    }
    private fun makeUnitsAsync(): Deferred<Int> {
        return CoroutineScope(Dispatchers.Default).async {
            (3..21).random()
        }
    }

    fun sendUnitsAsync(): Deferred<Int> {
        val dataToReturn = units.first()
        units.remove(dataToReturn)
        return dataToReturn
    }
}