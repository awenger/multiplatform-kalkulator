import kotlin.js.Json

@JsModule("restify")
external object restify {
    fun createServer(): Server

    val plugins: PluginRegistry
}

external interface PluginRegistry {
    fun bodyParser(): (request: Request, response: Response, next: () -> Unit) -> Unit
}

external interface Server {

    fun use(handler: (request: Request, response: Response, next: () -> Unit) -> Unit)

    fun get(path: String, handler: (request: Request, response: Response, next: () -> Unit) -> Unit)
    fun post(path: String, handler: (request: Request, response: Response, next: () -> Unit) -> Unit)

    fun listen(port: Int, done: () -> Unit)
}

external interface Request {
    val body: Json
    val params: Json
}

external interface Response {
    fun header(key: String, value: String)
    fun send(body: Json)
    fun send(code: Int, body: String? = definedExternally)
    fun send(body: String)
}