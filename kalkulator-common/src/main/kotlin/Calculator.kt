package de.awenger.kalkulator

class Calculator {

    private val observers = mutableListOf<Observer>()
    private var inputState = emptyList<Input>()

    fun addInput(input: Input) {
        inputState = when (input) {
            Input.EQUALS -> calc(inputState)?.toString()?.toList()?.map { it.toInput() } ?: emptyList()
            Input.RESET  -> emptyList()
            else         -> inputState.plus(input)
        }
        observers.forEach { it.onChange(getState()) }
    }

    fun getState(): State {
        val input = formatInputLine(inputState)
        val result = calc(inputState)?.toString() ?: ""
        return State(
                inputLine = input,
                resultPreview = if (result != input) result else ""
        )
    }

    fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    fun unregisterObserver(observer: Observer) {
        observers.remove(observer)
    }

    private fun formatInputLine(inputState: List<Input>): String {
        return inputState.map {
            val formatting = if (it is Input.Operator) FORMATTING_SPACE else ""
            formatting + it.toChar() + formatting
        }.joinToString(separator = "")
    }

    private fun calc(input: List<Input>): Long? {
        if (input.isEmpty()) return null
        if (input.all { it is Input.Number }) {
            return input.map { it as Input.Number }.map { it.value() }.reduce { acc, next -> acc * 10 + next }
        }

        val operatorPos = input.indexOfLast { it is Input.Operator }
        val operator = input[operatorPos] as Input.Operator

        val leftSide = calc(input.subList(0, operatorPos))
        val rightSide = calc(input.subList(operatorPos + 1, input.size))

        return when (operator) {
            Input.Operator.MINUS -> (leftSide ?: 0) - (rightSide ?: 0)
            Input.Operator.PLUS  -> (leftSide ?: 0) + (rightSide ?: 0)
        }
    }

    private fun Char.toInput() = when (this) {
        '0'  -> Input.Number.ZERO
        '1'  -> Input.Number.ONE
        '2'  -> Input.Number.TWO
        '3'  -> Input.Number.THREE
        '4'  -> Input.Number.FOUR
        '5'  -> Input.Number.FIVE
        '6'  -> Input.Number.SIX
        '7'  -> Input.Number.SEVEN
        '8'  -> Input.Number.EIGHT
        '9'  -> Input.Number.NINE
        '+'  -> Input.Operator.PLUS
        '-'  -> Input.Operator.MINUS
        else -> throw IllegalStateException("unmapable char $this")
    }

    private fun Input.Number.value(): Long = when (this) {
        Input.Number.ZERO  -> 0
        Input.Number.ONE   -> 1
        Input.Number.TWO   -> 2
        Input.Number.THREE -> 3
        Input.Number.FOUR  -> 4
        Input.Number.FIVE  -> 5
        Input.Number.SIX   -> 6
        Input.Number.SEVEN -> 7
        Input.Number.EIGHT -> 8
        Input.Number.NINE  -> 9
    }

    private fun Input.toChar() = when (this) {
        Input.Number.ZERO    -> '0'
        Input.Number.ONE     -> '1'
        Input.Number.TWO     -> '2'
        Input.Number.THREE   -> '3'
        Input.Number.FOUR    -> '4'
        Input.Number.FIVE    -> '5'
        Input.Number.SIX     -> '6'
        Input.Number.SEVEN   -> '7'
        Input.Number.EIGHT   -> '8'
        Input.Number.NINE    -> '9'
        Input.Operator.PLUS  -> '+'
        Input.Operator.MINUS -> '-'
        Input.EQUALS         -> throw IllegalStateException("equals should never be rendered")
        Input.RESET          -> throw IllegalStateException("reset should never be rendered")
    }

    interface Observer {
        fun onChange(state: State)
    }

    companion object {
        private const val FORMATTING_SPACE = "\u2006"
    }
}
