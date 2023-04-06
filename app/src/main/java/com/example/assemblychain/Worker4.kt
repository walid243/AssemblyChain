package com.example.assemblychain

class Worker4 {
    var text = ""
    var accumulatedPackets = 0
    fun doWork(packets: Int, container: Int){
            accumulatedPackets += packets
        val capacity = container * 4
        text = if (accumulatedPackets <= capacity){
            val packetsInContainer = accumulatedPackets
            accumulatedPackets = 0
            println("The $packetsInContainer packets are in the container with $capacity capacity")
            "The $packetsInContainer packets are in the container with $capacity capacity"
        } else {
            val packetsNotInContainer = accumulatedPackets % capacity
            accumulatedPackets = packetsNotInContainer
            println("The $packetsNotInContainer packets are not in the container with $capacity capacity")
            "The $packetsNotInContainer packets are not in the container with $capacity capacity"
        }

    }
}