package me.pasindu.dogbreedapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.view.marginTop

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        title = getString(R.string.finishTitle)

        val msg = findViewById<TextView>(R.id.msg)
        val resultText = findViewById<TextView>(R.id.results)

        val queCount = intent.getIntExtra("Questions",0)
        val correctCount = intent.getIntExtra("Correct",0)
        Log.d("Questions","$queCount")
        Log.d("Correct Count", "$correctCount")

        if (correctCount == 0) {
            msg.isVisible = false
        }
        resultText.text = getString(R.string.finishResult, correctCount, queCount)
    }
}