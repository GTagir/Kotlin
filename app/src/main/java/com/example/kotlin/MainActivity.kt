package com.example.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById<Button>(R.id.buttonOne)
        val textView = findViewById<TextView>(R.id.resultClick)
        val  note1 : NoteKotlin = NoteKotlin("Data Class Title1","Data Class Note1")
        val  note2 : NoteKotlin = NoteKotlin("Data Class Title2","Data Class Note1" )

        val dataClassTitle = findViewById<TextView>(R.id.dataClassTitle)
        val dataClassNote = findViewById<TextView>(R.id.dataClassNote)

        val w = Weather("Perm", 0)

        button.setOnClickListener {
            textView.text = resources.getString(R.string.motivation_for_action_press)
            loopFirstAndSecond(4)
            dataClassTitle.text = note1.title.toString()
            dataClassNote.text = note1.note.toString()

            


        }
    }

    data class NoteKotlin(val title:String,val note:String)




    private fun loopFirstAndSecond(maxInt: Int) {
        Thread {
            val loopActionStatus = findViewById<TextView>(R.id.loopActionStatus)
            loopActionStatus.text = resources.getString(R.string.loop_started)
            val loopResult = findViewById<TextView>(R.id.loopResult)
            for (i in 1..maxInt) {
                Log.d("Thread", "Итерация: " + i + "поток: " + Thread.currentThread())
                when {
                    i % 2 == 0 -> {
                        this.runOnUiThread(java.lang.Runnable {
                            loopResult.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                            loopResult.text = resources.getString(R.string.loop_yell_one)
                        })
                    }
                    else -> {
                        this.runOnUiThread(java.lang.Runnable {
                            loopResult.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                            loopResult.text = resources.getString(R.string.loop_yell_two)
                        })
                    }
                }
                sleep(1000)
            }
            this.runOnUiThread(java.lang.Runnable {
                loopResult.textAlignment = View.TEXT_ALIGNMENT_CENTER
                loopResult.text = resources.getString(R.string.loop_yell_calculation_is_over)
                loopActionStatus.text = resources.getString(R.string.loop_finished)
            })
        }.start()
    }
}

