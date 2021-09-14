package coroutines

import kotlinx.coroutines.*

/***
 * Coroutine cancellation is cooperative.
 * A coroutine code has to cooperate to be cancellable.
 * All the suspending functions in kotlinx.coroutines are cancellable.
 * They check for cancellation of coroutine and throw CancellationException when cancelled.
 * However, if a coroutine is working in a computation and does not check for cancellation,
 * then it cannot be cancelled
 ***/
fun main() = runBlocking {
    cancellationCoop()
}

suspend fun cancellationCoop() = coroutineScope {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 10) { // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1500L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}