package de.awenger.kalkulator


sealed class Input {
    sealed class Number : Input() {
        object ZERO : Number()
        object ONE : Number()
        object TWO : Number()
        object THREE : Number()
        object FOUR : Number()
        object FIVE : Number()
        object SIX : Number()
        object SEVEN : Number()
        object EIGHT : Number()
        object NINE : Number()

    }

    sealed class Operator : Input() {
        object MINUS : Operator()
        object PLUS : Operator()
    }

    object EQUALS : Input()
    object RESET : Input()
}