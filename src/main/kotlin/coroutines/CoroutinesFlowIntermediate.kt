package coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


fun main() = runBlocking<Unit> {
    println("Map and Collect...............")
    mapAndCollectFlow()
    println("Transform.....................")
    transformFlow()
    println("Size-limiting.................")
    numbers()
        .take(2) // take only the first two
        .collect { value -> println(value) }

    println("..............................")
    terminalFlowOperation()
    println("..............................")
    sequentialFlow()
}

private suspend fun sequentialFlow() {
    (1..10).asFlow()
        .filter {
            println("Number: $it")
            it % 2 == 0
        }
        .map {
            println("Filtered number : $it")
            "string $it"
        }.collect {
            println("Collect number : $it")
        }
}

private suspend fun terminalFlowOperation() {
    println("Terminal flow operators")
    val sum = (1..5).asFlow()
        .map { it * it } // squares of numbers from 1 to 5
        .reduce { a, b -> a + b } // sum them (terminal operator)
    println(sum)
}

/***
 * The basic operators have familiar names like map and filter.
 * The important difference to sequences is that blocks of code
 * inside these operators can call suspending functions.
 ***/
private suspend fun mapAndCollectFlow() {
    (1..3).asFlow() // a flow of requests
        .map { request -> performRequest(request) } // map == transformation
        .collect { response -> println(response) }// collect == process
}

/***
 * Using the transform operator,
 * we can emit arbitrary values an arbitrary number of times.
 ***/
private suspend fun transformFlow() {
    (1..3).asFlow() // a flow of requests
        .transform { request ->
            // emit: Collects the value emitted by the upstream.
            emit("Making request $request")
            emit(performRequest(request))
        }
        .collect { response -> println(response) }
}

suspend fun performRequest(request: Int): String {
    delay(1000) // imitate long-running asynchronous work
    return "response $request"
}

fun numbers(): Flow<Int> = flow {
    try {
        emit(1)
        emit(2)
        println("This line will not execute")
        emit(3)
    } finally {
        println("Finally in numbers")
    }
}


