package coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    cancellationCoopCheck()
}

suspend fun cancellationCoopCheck() = coroutineScope {
    val job = launch {
        try {
            do { // computation loop, just wastes CPU
                // print a message twice a second
                println("job: I'm sleeping $isActive ...")
                delay(100)
            } while (isActive)
        } finally {
            println("Finally")
        }
    }
    delay(1500L) // delay a bit
    println("main: I'm tired of waiting!")
    // Both join and cancelAndJoin wait for all finalization actions to complete
    job.cancelAndJoin()
    println("main: Now I can quit.")
}