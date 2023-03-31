package com.example.sayitahminuygulamasi

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.random.Random

@Composable
fun TahminEkrani(navController: NavController) {
    val tfTahmin = remember { mutableStateOf("") }
    val rastgeleSayi = remember { mutableStateOf(0) }
    val kalanHak = remember { mutableStateOf(5) }
    val yonlendirme = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LaunchedEffect(key1 = true) {
            rastgeleSayi.value = Random.nextInt(101)
            Log.e("Sayi", rastgeleSayi.value.toString())
        }
        Text(text = "Kalan Hak : ${kalanHak.value}", fontSize = 36.sp, color = Color.Red)
        Text(text = "Yardım : ${yonlendirme.value}", fontSize = 24.sp)
        TextField(
            value = tfTahmin.value,
            onValueChange = {
                tfTahmin.value = it.trim()
            },
            label = { Text(text = "Tahmin") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Button(onClick = {

            kalanHak.value = kalanHak.value - 1
            val tahmin = tfTahmin.value.toInt()
            if (tahmin == rastgeleSayi.value) {
                navController.navigate("sonucekrani/true") {
                    popUpTo("tahminekrani") {
                        inclusive = true
                    }
                }
                return@Button
            }
            if (tahmin > rastgeleSayi.value) {
                yonlendirme.value = "Azalt"
            }
            if (tahmin < rastgeleSayi.value) {
                yonlendirme.value = "Arttır"
            }
            if (kalanHak.value == 0) {
                navController.navigate("sonucekrani/false") {
                    popUpTo("tahminekrani") {
                        inclusive = true
                    }
                }
            }
            tfTahmin.value = ""

        }, modifier = Modifier.size(250.dp, 50.dp)) {
            Text(text = "TAHMİN ET")
        }
    }
}