package com.example.coroutineasync

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutineasync.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myCoroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.start.setOnClickListener { start_coroutines() }
    }

    fun start_coroutines(){
        myCoroutineScope.launch {
            Log.i("alain", "launch coroutines")

            val res1 = myCoroutineScope.async { task_coroutine1() }
            val res2 = myCoroutineScope.async { task_coroutine2() }

            Log.i("alain", "all coroutines launched")
            Log.i("alain",  "all routines finished " + (res1.await() + res2.await()).toString())
        }
    }
    suspend fun task_coroutine1(): Int {
        delay(8000)
        Log.i("alain", getString(R.string.finished_coroutine, " 1"))
        return 1
    }
    suspend fun task_coroutine2(): Int {
        delay(10000)
        Log.i("alain", getString(R.string.finished_coroutine, " 2"))
        return 2
    }
}