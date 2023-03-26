package com.example.assemblychain

import kotlinx.coroutines.channels.ReceiveChannel

class Worker4 {
    var text = ""
    suspend fun doWork(packetsChannel: ReceiveChannel<Int>, containerChannel: ReceiveChannel<Int>){
        var accumulatedPackets = 0
        for (packets in packetsChannel){
            accumulatedPackets += packets
        }
        val container = containerChannel.receive()
        val capacity = container * 4
        text = if (accumulatedPackets == capacity){
            println("The $accumulatedPackets packets are in the container with $capacity capacity")
            "The $accumulatedPackets packets are in the container with $capacity capacity"
        } else {
            println("The $accumulatedPackets packets are not in the container with $capacity capacity")
            "The $accumulatedPackets packets are not in the container with $capacity capacity"
        }

    }
}