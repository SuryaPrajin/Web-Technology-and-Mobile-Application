package com.example.calculator

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : Activity() {

    private lateinit var display: TextView  // Use 'lateinit' for non-nullable TextView

    private var currentInput = ""
    private var operator = ""
    private var firstOperand = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Link the layout file

        // Initialize the TextView
        display = findViewById(R.id.display)

        // Set up number buttons
        setUpNumberButton(R.id.button0, "0")
        setUpNumberButton(R.id.button1, "1")
        setUpNumberButton(R.id.button2, "2")
        setUpNumberButton(R.id.button3, "3")
        setUpNumberButton(R.id.button4, "4")
        setUpNumberButton(R.id.button5, "5")
        setUpNumberButton(R.id.button6, "6")
        setUpNumberButton(R.id.button7, "7")
        setUpNumberButton(R.id.button8, "8")
        setUpNumberButton(R.id.button9, "9")

        // Set up operator buttons
        setUpOperatorButton(R.id.buttonAdd, "+")
        setUpOperatorButton(R.id.buttonSubtract, "-")
        setUpOperatorButton(R.id.buttonMultiply, "*")
        setUpOperatorButton(R.id.buttonDivide, "/")

        // Set up equals button
        val equalsButton: Button = findViewById(R.id.buttonEquals)
        equalsButton.setOnClickListener {
            if (currentInput.isNotEmpty()) {
                val secondOperand = currentInput.toDouble()
                val result = performOperation(firstOperand, secondOperand, operator)
                display.text = result.toString()
                currentInput = result.toString()
            }
        }

        // Set up clear button
        val clearButton: Button = findViewById(R.id.buttonClear)
        clearButton.setOnClickListener {
            currentInput = ""
            operator = ""
            firstOperand = 0.0
            display.text = "0"
        }
    }

    private fun setUpNumberButton(buttonId: Int, number: String) {
        val button: Button = findViewById(buttonId)
        button.setOnClickListener {
            currentInput += number
            display.text = currentInput
        }
    }

    private fun setUpOperatorButton(buttonId: Int, op: String) {
        val button: Button = findViewById(buttonId)
        button.setOnClickListener {
            if (currentInput.isNotEmpty()) {
                firstOperand = currentInput.toDouble()
                currentInput = ""
                operator = op
            }
        }
    }

    private fun performOperation(firstOperand: Double, secondOperand: Double, operator: String): Double {
        return when (operator) {
            "+" -> firstOperand + secondOperand
            "-" -> firstOperand - secondOperand
            "*" -> firstOperand * secondOperand
            "/" -> if (secondOperand != 0.0) firstOperand / secondOperand else Double.NaN  // Handle divide by zero
            else -> 0.0
        }
    }
}
