package com.njeri.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.njeri.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TipCalculator()
                }
            }
        }
    }
}

@Preview
@Composable
fun TipCalculator(){
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
        ,
       horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {
        Text(
            text = "Tip",
//            fontSize = 32.dp
        )
        Spacer(modifier = Modifier.height(16.dp))
        TipTextField()
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = stringResource(id = R.string.tip))
    }
}

@Composable
fun TipTextField(){

    var amountInput by remember {
        mutableStateOf("")
    }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    var tip= calculateTip(amount,15.21)
    TextField(
        value = amountInput,
        onValueChange = {amountInput = it},
        label = { Text(text = "Amount")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number )
        )

}

private fun calculateTip(
    amount:Double,
    tipPercent:Double=15.00
    ):String {
     val tip= amount/100 * tipPercent
    return NumberFormat.getCurrencyInstance().format(tip)
}