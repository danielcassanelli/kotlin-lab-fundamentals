package coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    runCancellation()
}

/***
 * The launch function returns a Job that can be used to cancel the running coroutine
 ***/
suspend fun runCancellation() = coroutineScope {

    println("main: Running job until cancellation ...")

    val job = launch {
        repeat(1000) { i ->
            println("job: Running $i ...")
            delay(250L)
        }
    }
    delay(3000L) // delays allowing the job to run for x secs
    job.cancelAndJoin() // after the delay, cancels the job

    // As soon as main invokes job.cancel, we don't see any output
    // from the other coroutine because it was cancelled. There is
    // also a Job extension function cancelAndJoin that combines
    // cancel and join invocations.
    println("main: Cancellation done.") // notify finalisation
}
