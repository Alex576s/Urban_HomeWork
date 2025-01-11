package com.example.contextmenu

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var textET: EditText
    private lateinit var randomButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textET = findViewById(R.id.textET)

        randomButton = findViewById(R.id.randomButton)

        randomButton.setOnClickListener {
            val randomNumber = Random.nextInt(1, 51) // Генерация случайного числа от 1 до 50
            textET.setText(randomNumber.toString())
        }
        registerForContextMenu(textET)
        textET.setOnLongClickListener {
            openContextMenu(textET)
            true
        }


    }



    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("Выберите действие")
        menu.add(0, v.id, 0, "Цветовое качество")
        menu.add(0, v.id, 0, "Выход из приложения")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.title) {
            "Цветовое качество" -> {
                setColorBasedOnInput()
                true
            }
            "Выход из приложения" -> {
                finish() // Завершение активности
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun setColorBasedOnInput() {
        val input = textET.text.toString().toIntOrNull()
        if (input != null) {
            when (input) {
                1 -> textET.setBackgroundColor(Color.parseColor("#FFA500"))
                2 -> textET.setBackgroundColor(Color.YELLOW)
                3 -> textET.setBackgroundColor(Color.GREEN)
                4 -> textET.setBackgroundColor(Color.BLUE)
                5 -> textET.setBackgroundColor(Color.RED)
                in 1..10 -> textET.setBackgroundColor(Color.RED)
                in 11..20 -> textET.setBackgroundColor(Color.parseColor("#FFA500"))
                in 21..30 -> textET.setBackgroundColor(Color.YELLOW)
                in 31..40 -> textET.setBackgroundColor(Color.GREEN)
                in 41..50 -> textET.setBackgroundColor(Color.BLUE)
                else -> textET.setBackgroundColor(Color.TRANSPARENT) // Сброс цвета
            }
        } else {
            textET.setBackgroundColor(Color.TRANSPARENT) // Сброс цвета, если ввод не корректен
        }
    }
}