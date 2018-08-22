package de.awenger.kalkulator.android

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import de.awenger.kalkulator.Calculator
import de.awenger.kalkulator.Input
import de.awenger.kalkulator.State
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Calculator.Observer {

    private val calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input_0.setOnClickListener { calculator.addInput(Input.Number.ZERO) }
        input_1.setOnClickListener { calculator.addInput(Input.Number.ONE) }
        input_2.setOnClickListener { calculator.addInput(Input.Number.TWO) }
        input_3.setOnClickListener { calculator.addInput(Input.Number.THREE) }
        input_4.setOnClickListener { calculator.addInput(Input.Number.FOUR) }
        input_5.setOnClickListener { calculator.addInput(Input.Number.FIVE) }
        input_6.setOnClickListener { calculator.addInput(Input.Number.SIX) }
        input_7.setOnClickListener { calculator.addInput(Input.Number.SEVEN) }
        input_8.setOnClickListener { calculator.addInput(Input.Number.EIGHT) }
        input_9.setOnClickListener { calculator.addInput(Input.Number.NINE) }

        input_plus.setOnClickListener { calculator.addInput(Input.Operator.PLUS) }
        input_minus.setOnClickListener { calculator.addInput(Input.Operator.MINUS) }

        input_clear.setOnClickListener { calculator.addInput(Input.RESET) }
        input_equals.setOnClickListener { calculator.addInput(Input.EQUALS) }
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        calculator.registerObserver(this)
    }

    override fun onPause() {
        super.onPause()
        calculator.unregisterObserver(this)
    }

    override fun onChange(state: State) {
        input_preview.text = state.inputLine
        result_preview.text = state.resultPreview
    }
}
