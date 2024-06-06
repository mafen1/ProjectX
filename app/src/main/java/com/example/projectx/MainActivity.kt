package com.example.projectx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectx.databinding.ActivityMainBinding
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        checkProtect()
    }

    private fun initView() {
        binding.button0.setOnClickListener { updateText("0") }
        binding.button1.setOnClickListener { updateText("1") }
        binding.button2.setOnClickListener { updateText("2") }
        binding.button3.setOnClickListener { updateText("3") }
        binding.button4.setOnClickListener { updateText("4") }
        binding.button5.setOnClickListener { updateText("5") }
        binding.button6.setOnClickListener { updateText("6") }
        binding.button7.setOnClickListener { updateText("7") }
        binding.button8.setOnClickListener { updateText("8") }
        binding.button9.setOnClickListener { updateText("9") }

        binding.buttonAddition.setOnClickListener {
            checkProtect()
            updateText("", "+")
            clearCurrentNumber()
        }
        binding.buttonSubtraction.setOnClickListener {
            checkProtect()
            updateText("", "-")
            clearCurrentNumber()
        }
        binding.buttonMultiplication.setOnClickListener {
            checkProtect()
            updateText("", "*")
            clearCurrentNumber()
        }
        binding.buttonDivision.setOnClickListener {
            checkProtect()
            updateText("", "/")
            clearCurrentNumber()

        }

        binding.buttonCe.setOnClickListener { clearAll() }

        binding.buttonEqual.setOnClickListener {
            equal()
        }

        binding.buttonRoot.setOnClickListener {
            updateText("", "", "//")
            checkProtect()
        }
        binding.buttonSquare.setOnClickListener {
            checkProtect()
            updateText("", "", "**")
        }
        binding.buttonPercentage.setOnClickListener {
            checkProtect()
            updateText("", "", "%")
        }
        binding.buttonFraction.setOnClickListener {
            checkProtect()
            updateText("", "", "/1")
        }
        binding.buttonPlusMinus.setOnClickListener {
            updateText("", "", "", "yes")
        }
        binding.buttonComma.setOnClickListener {
            updateText("", "", "", "", "yes")
        }
    }

    private fun updateText(
        number: String = "0",
        operation: String = "",
        specialOperations: String = "",
        plusAndMinus: String = "",
        comma: String = ""
    ) {
        if (operation != "") {
            binding.numberCurrent.setText("${binding.numberCurrent.text}")
            viewModel.operations.value = operation
            viewModel.isClickable.value = false
            checkProtect()
        } else {
            binding.numberCurrent.setText("${binding.numberCurrent.text}${number}")
            viewModel.isClickable.value = true
            checkProtect()
        }
        if (specialOperations != "") {
            viewModel.operations.value = specialOperations
            viewModel.isClickable.value = false
            viewModel.number1.value = binding.numberCurrent.text.toString().toDouble()
            checkProtect()
            equal("flo")
        }
        if (plusAndMinus == "yes") {
            if (binding.buttonPlusMinus.text == "-") {
                binding.numberCurrent.setText("-${binding.numberCurrent.text}")
                binding.buttonPlusMinus.text = "+"
            } else {
                val newText = binding.numberCurrent.text.substring(
                    1,
                    1
                ) + binding.numberCurrent.text.substring(0 + 1)
                binding.numberCurrent.setText(newText)
                binding.buttonPlusMinus.text = "-"
            }
        }
        if (comma == "yes") {
            binding.numberCurrent.setText("${binding.numberCurrent.text}.")
            viewModel.isClickable.value = false
            checkProtect()
        }
    }


    private fun equal(specialOperations: String = "") {
        if (specialOperations == "") {
            viewModel.number2.value = binding.numberCurrent.text.toString().toDouble()
            viewModel.operations()
            viewModel.addHistoryResult()
            binding.numberHistory.setText("${viewModel.resultList.value},")
            binding.numberCurrent.setText("${viewModel.result.value}")
            viewModel.isClickable.value = true
        } else {
            viewModel.operations()
            viewModel.addHistoryResult()
            binding.numberHistory.setText("${viewModel.resultList.value},")
            binding.numberCurrent.setText("${viewModel.result.value}")
            viewModel.isClickable.value = true
        }
    }

    private fun clearAll() {
        viewModel.clearAll()
        binding.numberCurrent.text.clear()
        binding.numberHistory.text.clear()
        viewModel.isClickable.value = true
    }

    private fun checkProtect() {
        if (viewModel.isClickable.value == true) {
            buttonsOperationsActivated(true)

        } else {
            buttonsOperationsActivated(false)
        }
        if (binding.numberCurrent.text.isEmpty()) {
            buttonsOperationsActivated(false)
        }
    }


    private fun clearCurrentNumber() = run {
        viewModel.number1.value = binding.numberCurrent.text.toString().toDouble()
        binding.numberCurrent.setText("")
    }

    private fun buttonsOperationsActivated(isClickable: Boolean) {
        binding.buttonAddition.isClickable = isClickable
        binding.buttonSubtraction.isClickable = isClickable
        binding.buttonMultiplication.isClickable = isClickable
        binding.buttonDivision.isClickable = isClickable
        binding.buttonEqual.isClickable = isClickable
        binding.buttonRoot.isClickable = isClickable
        binding.buttonFraction.isClickable = isClickable
        binding.buttonSquare.isClickable = isClickable
        binding.buttonPercentage.isClickable = isClickable
        binding.buttonComma.isClickable = isClickable
    }


}