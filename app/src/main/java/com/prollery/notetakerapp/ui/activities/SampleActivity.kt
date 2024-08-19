package com.prollery.notetakerapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.lifecycleScope

import com.prollery.notetakerapp.databinding.ActivitySampleBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.util.Random

class SampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.toolbar.title = "S"

        binding.btnSubmit.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
//                val userOpr = launch {
//                    fetchUsers()
//                }
//                val playerOpr = launch {
//                    fetchPlayers()
//                }
//                userOpr.join()
//                playerOpr.join()
//                println("Both Use and player finished")


//                val userOpr = async {
//                    fetchUsers()
//                }
//                val playerOpr = launch {
//                    fetchPlayers()
//                }
//                val userOprResult = userOpr.await()
//                println("userOprResult : $userOprResult")
//                playerOpr.join()
//                println("Player completed")

                supervisorScope {
//                    val usrOpr = async { fetchUsers() }
//                    val playerOpr = async { fetchPlayers() }

                    val userJob = launch {
                        fetchUsers()
                            .onEach {
                                println(it)
                            }
                            .catch {
                                println(it.message)
                            }
                            .collect {
                                println("User : $it")
                            }
                    }

                    val playerJob = launch {
                        fetchPlayers()
                            .collect {
                                println("Player : $it")
                            }
                    }
                    userJob.join()
                    playerJob.join()
                    println("Both player and user completed")
                }

            }
        }
    }

    private suspend fun fetchUsers() : Flow<Int> = flow {
            var count = 20
            while (count > 0) {
                emit(count)
                delay(500)
                if (Random().nextBoolean()) {
                    throw Exception("User crashed at $count")
                }
                count--
            }
    }

    private suspend fun fetchPlayers() : Flow<Int> = flow {
        var count = 20
        while(count > 0) {
            emit(count)
            delay(1000)
            count--
        }
    }

}