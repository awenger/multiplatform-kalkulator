import de.awenger.kalkulator.Calculator
import de.awenger.kalkulator.Input
import kotlin.js.Json

@JsName("main")
fun main(args: Array<String>) {

    val calculator = Calculator()

    val server = restify.createServer()
    server.use(restify.plugins.bodyParser())

    server.get("/calculator") { req, res, next ->
        val state = calculator.getState() as Json

        res.send(state)
        next()
    }

    server.post("/calculator/inputs/") { req, res, next ->

        val input = when (req.body["input"]) {
            "0" -> Input.Number.ZERO
            "1" -> Input.Number.ONE
            "2" -> Input.Number.TWO
            "3" -> Input.Number.THREE
            "4" -> Input.Number.FOUR
            "5" -> Input.Number.FIVE
            "6" -> Input.Number.SIX
            "7" -> Input.Number.SEVEN
            "8" -> Input.Number.EIGHT
            "9" -> Input.Number.NINE
            "+" -> Input.Operator.PLUS
            "-" -> Input.Operator.MINUS
            "=" -> Input.EQUALS
            "CLR" -> Input.RESET
            else -> null
        }

        if (input == null) {
            res.send(400, "invalid input ${req.body["input"]}")
            next()
            return@post
        }

        calculator.addInput(input)

        res.send(201)
        next()
    }

    server.listen(8082) { println("server started on 8082") }
}