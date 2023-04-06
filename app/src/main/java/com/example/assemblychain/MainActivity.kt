package com.example.assemblychain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("Start <----------------------")
        val worker1 = Worker1()
        val worker2 = Worker2()
        val worker3 = Worker3()
        val worker4 = Worker4()
        repeat(5) {
            CoroutineScope(Dispatchers.Default).launch {
                worker1.doWork()
                worker2.doWork(worker1.sendUnitsAsync().await())
                worker3.doWork()
                worker4.doWork(
                    worker2.getPacketAsync().await(),
                    worker3.getContainerAsync().await()
                )
                println("Done<---------------------------")
                withContext(Dispatchers.Main) {
                    Toast.makeText(baseContext, worker4.text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}