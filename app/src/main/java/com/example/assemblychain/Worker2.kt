package com.example.assemblychain

import kotlinx.coroutines.*

class Worker2 {

    private val packetChannel = mutableListOf<Deferred<Int>>()
    var accumulatedPrimeMaterials = 0

    suspend fun doWork(primeMaterials: Int){
            accumulatedPrimeMaterials += primeMaterials
            if (accumulatedPrimeMaterials >= 4){
                val packets = accumulatedPrimeMaterials / 4
                repeat(packets){
                    packetChannel.add(CoroutineScope(Dispatchers.Default).async { 4 })
                }
                accumulatedPrimeMaterials %= 4
                delay(1000)
        }
        if (accumulatedPrimeMaterials > 0){
            packetChannel.add(CoroutineScope(Dispatchers.Default).async { accumulatedPrimeMaterials })
        }
    }

    fun getPacketAsync(): Deferred<Int> {
        val dataToReturn = packetChannel.first()
        packetChannel.remove(dataToReturn)
        return dataToReturn
    }


}