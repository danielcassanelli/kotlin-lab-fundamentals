package coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    nonCancellable()
}

suspend fun nonCancellable() = coroutineScope {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job::run: Running $i ...")
                delay(500L)
            }
        } finally {
            withContext(NonCancellable) {
                println("job::finally: I'm running 10 non Cancellable iterations")
                repeat(10) { i ->
                    println("job::finally: Running NonCancellable ${i+1} ...")
                    delay(500L)
                }
            }
        }
    }
    delay(2000L) // delay a bit
    println("main: Calling cancelAndJoin!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Done.")
}