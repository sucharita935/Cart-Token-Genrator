package com.handson.carttokengenerator

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomePage(onNavigateToTokenPage: (Int) -> Unit) {

    // welcome page for the app to register the user
    var phoneNumber = remember { mutableStateOf("") }
    var customerName = remember { mutableStateOf("") }
    val isPayBill = remember { mutableStateOf(true) }
    val isExchange = remember { mutableStateOf(false) }
    val tokenNumber = remember { mutableIntStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Login to Quick Home")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = com.handson.carttokengenerator.ui.theme.Purple40,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
    ) { innerPadding ->
        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Register yourself",
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
            TextField(
                value = customerName.value,
                onValueChange = { customerName.value = it },
                label = { Text("Customer Name") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                placeholder = { Text("Example Example") }
            )
            TextField(
                value = phoneNumber.value,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        phoneNumber.value = input
                    }
                },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text("9999999999") }
            )

            Spacer(modifier = Modifier.padding(10.dp))
            //radio button options to pay bill or exchange
            androidx.compose.foundation.layout.Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Radio button to pay bill
                    RadioButton(
                        selected = isPayBill.value,
                        onClick = {
                            isPayBill.value = true
                            isExchange.value = false
                        }
                    )
                    Text("Pay Bill")
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Radio button to pay bill
                    RadioButton(
                        selected = isExchange.value,
                        onClick = {
                            isExchange.value = true
                            isPayBill.value = false
                        }
                    )
                    Text("Exchange")
                }
            }

            val selectedText = if (isPayBill.value) {
                "Pay Bill"
            } else if (isExchange.value) {
                "Exchange"
            } else {
                null
            }
            // Button to Login
            Button(
                onClick = {
                    tokenNumber.intValue+=1
                    onNavigateToTokenPage(tokenNumber.intValue)
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                enabled = customerName.value.isNotEmpty() && phoneNumber.value.isNotEmpty() && phoneNumber.value.length == 10,
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = com.handson.carttokengenerator.ui.theme.Purple40,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = "Register")
            }
        }
    }
}