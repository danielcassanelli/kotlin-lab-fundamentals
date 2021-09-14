package coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking { // this: CoroutineScope

    val numberInteger = 0
    val numberDecimal = 0.0

    launch {
        doWorld(numberInteger)
        doWorld(numberDecimal)
    }.also {
        println("Also: ${it.isActive}")
    }.run {
        println("Run: $isActive")
    }
    println("End")
}

// this is your first suspending function
suspend fun doWorld(number: Number) {
    delay(1000L)
    println(number)
}