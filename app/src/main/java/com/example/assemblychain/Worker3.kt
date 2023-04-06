package com.example.assemblychain

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

class Worker3 {
    private val containers = mutableListOf<Deferred<Int>>()

    suspend fun doWork(){
        val positions = (2..4).random()
        val fabricationDelay = (10000..20000).random()
        delay(fabricationDelay.toLong())
        containers.add(CoroutineScope(Dispatchers.Default).async { positions })
    }

    fun getContainerAsync(): Deferred<Int> {
        val dataToReturn = containers.first()
        containers.remove(dataToReturn)

        return dataToReturn
    }
}