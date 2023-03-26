package com.example.assemblychain

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay

class Worker1 {
    private val unitsChannel = Channel<Int>()
    suspend fun doWork(){
        repeat(5){
            makeUnits()
            unitsChannel.send(makeUnits())
            delay(3000)
        }
        unitsChannel.close()
    }
    fun makeUnits(): Int {
        return (3..21).random()
    }

    fun sendUnitsChannel(): ReceiveChannel<Int> {
        return unitsChannel
    }
}