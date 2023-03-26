package com.example.assemblychain

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay

class Worker2 {

    private val packetChannel = Channel<Int>()

    suspend fun doWork(primeMaterialsChannel: ReceiveChannel<Int>){
        var accumulatedPrimeMaterials = 0
        for (primeMaterials in primeMaterialsChannel){
            accumulatedPrimeMaterials += primeMaterials
            if (accumulatedPrimeMaterials >= 4){
                val packets = accumulatedPrimeMaterials / 4
                repeat(packets){
                    packetChannel.send(4)
                }
                accumulatedPrimeMaterials %= 4
                delay(1000)
        }
    }
        if (accumulatedPrimeMaterials > 0){
            packetChannel.send(accumulatedPrimeMaterials)
        }
        packetChannel.close()
    }

    fun getPacketChannel(): ReceiveChannel<Int> {
        return packetChannel
    }


}