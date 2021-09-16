package coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    10.let {
        runChannel(it).apply {
            repeat(it) {
                prt(receive())
            }
        }
    }
    prt("Done!")
}

private fun CoroutineScope.runChannel(numberForProcess: Int): Channel<Int> =
    Channel<Int>().apply {
        launch {
            // starts at 1
            (1..numberForProcess).forEach { x ->
                send(x * x)
                if (x == numberForProcess) { // end condition
                    close()
                }
            }
        }
    }
