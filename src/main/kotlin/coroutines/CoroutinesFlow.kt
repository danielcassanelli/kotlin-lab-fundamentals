package coroutines


import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun simple(): Flow<Int> = flow { // flow builder
    for (i in 1..10) {
        val wait = (3..8).shuffled().first() * (10..50).shuffled().first()
        delay(wait.toLong()) // pretend we are doing something useful here
        println("flow: $wait")
        emit(wait) // emit next value
    }
}

fun main() = runBlocking<Unit> {
    // Launch a concurrent coroutine to check if the main thread is blocked
    launch {
        for (k in 1..60) {
            print(".") // keep printing points
            delay(100) // with 100m delay
        }
    }
    // Collect the flow
    simple().collect { value -> print(value) }
}