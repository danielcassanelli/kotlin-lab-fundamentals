package coroutines

import com.google.gson.GsonBuilder
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val client = HttpClient(CIO) {
        engine {
            maxConnectionsCount = 1000
            endpoint {
                maxConnectionsPerRoute = 100
                pipelineMaxSize = 20
                keepAliveTime = 5000
                connectTimeout = 5000
                connectAttempts = 5
            }
//            https {
//                serverName = "api.ktor.io"
//                cipherSuites = CIOCipherSuites.SupportedSuites
//                trustManager = myCustomTrustManager
//                random = mySecureRandom
//                addKeyStore(myKeyStore, myKeyStorePassword)
//            }
        }
        install(JsonFeature) {
            serializer = GsonSerializer() {
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }
    }

    val response: HttpResponse = client.get("https://ktor.io/") {
        headers {
            append(HttpHeaders.Accept, "text/html")
            append(HttpHeaders.Authorization, "token")
            append(HttpHeaders.UserAgent, "ktor client")
        }
    }

    val httpResponse: HttpResponse = client.get("https://ktor.io/")
    val byteArrayBody: ByteArray = httpResponse.receive()


    // If you need to use HttpClient for a single request, call the use function,
    // which automatically calls close after executing the code block
    val status = HttpClient().use { client ->  }

    val secondRequest: Deferred<String> = async {
        client.get(
            "http://api.weatherstack.com/current?access_key=3bb6f792833d7a8c986e413136ee8b40&query=Cascavel"
        )
    }

    secondRequest.await().apply {
        GsonBuilder().setPrettyPrinting().create().toJson(this).apply {
            print(this)
        }

    }

    val job = launch {
        val requestContent: String = client.get("http://localhost:8080")
    }
    job.cancel()

    // After you finish working with the HTTP client,
    // you need to free up the resources: threads,
    // connections, and CoroutineScope for coroutines.
    // To do this, call the HttpClient.close
    client.close()
}