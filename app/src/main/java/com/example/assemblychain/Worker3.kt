package com.example.assemblychain

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay

class Worker3 {
    private val containerChannel = Channel<Int>()

    suspend fun doWork(){
        val positions = (2..4).random()
        val fabricationDelay = (10000..20000).random()
        delay(fabricationDelay.toLong())
        containerChannel.send(positions)
        containerChannel.close()
    }

    fun getContainerChannel(): Channel<Int> {
        return containerChannel
    }
}