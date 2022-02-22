package me.pasindu.dogbreedapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val breedList: List<String> = listOf("Afghan Hound","Beagle","Chihuahua","Dingo",
        "English Foxhound","French Bulldog")
    private  val breedImgMap: HashMap<Int,List<String>> = HashMap()

    private var tvQuestion: TextView? = null
    private var ivOne: ImageView? = null
    private var ivTwo: ImageView? = null
    private var ivThree: ImageView? = null
    private var tvResult: TextView? = null
    private var btnSubmit: Button? = null
    private var btnNext: Button? = null
    private var btnFinish: Button? = null

    private var queCount = 0
    private var correctCount = 0
    private var questionNum = 0
    private val arrayOfNum = arrayListOf(-1,-1,-1)
    private var imgOne = ""
    private var imgTwo = ""
    private var imgThree = ""
    private var userChoice = ""
    private var submitted: Boolean = false
    private var imgCountList = arrayListOf(0,0,0,0,0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvQuestion = findViewById(R.id.tv_question)
        ivOne = findViewById(R.id.iv_one)
        ivTwo = findViewById(R.id.iv_two)
        ivThree = findViewById(R.id.iv_three)
        tvResult = findViewById(R.id.tv_result)
        btnSubmit = findViewById(R.id.btn_submit)
        btnNext = findViewById(R.id.btn_next)
        btnFinish = findViewById(R.id.btn_finish)

        ivOne!!.setOnClickListener(this)
        ivTwo!!.setOnClickListener(this)
        ivThree!!.setOnClickListener(this)
        btnSubmit!!.setOnClickListener(this)
        btnNext!!.setOnClickListener(this)
        btnFinish!!.setOnClickListener(this)

        breedImgMap[0] = listOf("img1", "img2", "img3")
        breedImgMap[1] = listOf("img4", "img5", "img6")
        breedImgMap[2] = listOf("img7", "img8", "img9")
        breedImgMap[3] = listOf("img10", "img11", "img12")
        breedImgMap[4] = listOf("img13", "img14", "img15")
        breedImgMap[5] = listOf("img16", "img17", "img18")


        btnFinish!!.visibility = View.GONE

        setQuestion()
    }

    private fun setQuestion() {
        submitted = false
        userChoice = ""
        tvResult!!.text = ""
        ivOne!!.isEnabled = true
        ivOne!!.background = ContextCompat.getDrawable(this,R.drawable.default_border)
        ivTwo!!.isEnabled = true
        ivTwo!!.background = ContextCompat.getDrawable(this,R.drawable.default_border)
        ivThree!!.isEnabled = true
        ivThree!!.background = ContextCompat.getDrawable(this,R.drawable.default_border)
        queCount++

        var randomNum: Int
        val iterator = arrayOfNum.listIterator()

        while (iterator.hasNext()) {
            iterator.next()
            do {
                randomNum = (0 until breedImgMap.size-1).random()
            } while (randomNum in arrayOfNum)
            iterator.set(randomNum)
        }
        Log.d("Num Array","$arrayOfNum")

        questionNum = arrayOfNum[Random.nextInt(arrayOfNum.size)]

        tvQuestion!!.text = getString(R.string.question, breedList[questionNum])

        val listSize = breedImgMap.getValue(0).size

        for (i in arrayOfNum) {
            when(i) {
                0 -> {
                    if (imgCountList[0] == listSize) {
                        imgCountList[0] = 0
                    }
                }
                1 -> {
                    if (imgCountList[1] == listSize) {
                        imgCountList[1] = 0
                    }
                }
                2 -> {
                    if (imgCountList[2] == listSize) {
                        imgCountList[2] = 0
                    }
                }
                3 -> {
                    if (imgCountList[3] == listSize) {
                        imgCountList[3] = 0
                    }
                }
                4 -> {
                    if (imgCountList[4] == listSize) {
                        imgCountList[4] = 0
                    }
                }
                5 -> {
                    if (imgCountList[5] == listSize) {
                        imgCountList[5] = 0
                    }
                }
            }
        }

        when {
            imgCountList[arrayOfNum[0]] == 3 -> {
                imgCountList[arrayOfNum[0]] = 0
            }
            imgCountList[arrayOfNum[1]] == 3 -> {
                imgCountList[arrayOfNum[1]] = 0
            }
            imgCountList[arrayOfNum[2]] == 3 -> {
                imgCountList[arrayOfNum[2]] = 0
            }
        }

        imgOne = breedImgMap[arrayOfNum[0]]!![imgCountList[arrayOfNum[0]]]
        imgCountList[arrayOfNum[0]]++
        imgTwo = breedImgMap[arrayOfNum[1]]!![imgCountList[arrayOfNum[1]]]
        imgCountList[arrayOfNum[1]]++
        imgThree = breedImgMap[arrayOfNum[2]]!![imgCountList[arrayOfNum[2]]]
        imgCountList[arrayOfNum[2]]++
        Log.d("Count List","$imgCountList")

        ivOne!!.setImageResource(getImageId(this,imgOne))
        ivTwo!!.setImageResource(getImageId(this,imgTwo))
        ivThree!!.setImageResource(getImageId(this,imgThree))

    }

    private fun getAnswer(imgName: String) {
        if (imgName in breedImgMap[questionNum]!! && !submitted) {
            tvResult!!.text = getString(R.string.correct)
            tvResult!!.setTextColor(Color.parseColor("#FF99CC00"))
            correctCount++
            when (userChoice) {
                imgOne -> {
                    ivOne!!.background = ContextCompat.getDrawable(this,R.drawable.correct_border)
                }
                imgTwo -> {
                    ivTwo!!.background = ContextCompat.getDrawable(this,R.drawable.correct_border)
                }
                imgThree -> {
                    ivThree!!.background = ContextCompat.getDrawable(this,R.drawable.correct_border)
                }
            }
        } else if (imgName !in breedList[questionNum]){
            tvResult!!.text = getString(R.string.wrong)
            tvResult!!.setTextColor(Color.parseColor("#FFFF4444"))
            when (userChoice) {
                imgOne -> {
                    ivOne!!.background = ContextCompat.getDrawable(this,R.drawable.wrong_border)
                }
                imgTwo -> {
                    ivTwo!!.background = ContextCompat.getDrawable(this,R.drawable.wrong_border)
                }
                imgThree -> {
                    ivThree!!.background = ContextCompat.getDrawable(this,R.drawable.wrong_border)
                }
            }
        }
        submitted = true
    }

    private fun getImageId(context: Context, imgName: String): Int {
        return context.resources.getIdentifier("drawable/${imgName}",null,context.packageName)
    }

    override fun onClick(view: View) {
        if (queCount > 0) {
            btnFinish!!.visibility = View.VISIBLE
        }

        when(view.id) {
            ivOne!!.id -> {
                userChoice = imgOne
                ivTwo!!.isEnabled = false
                ivThree!!.isEnabled = false
                getAnswer(userChoice)
            }
            ivTwo!!.id -> {
                userChoice = imgTwo
                ivOne!!.isEnabled = false
                ivThree!!.isEnabled = false
                getAnswer(userChoice)
            }
            ivThree!!.id -> {
                userChoice = imgThree
                ivOne!!.isEnabled = false
                ivTwo!!.isEnabled = false
                getAnswer(userChoice)
            }
            btnSubmit!!.id -> {

            }
            btnNext!!.id -> {
                if (userChoice != "") {
                    setQuestion()
                }
            }
            btnFinish!!.id -> {
                val finish = Intent(this, FinishActivity::class.java)
                finish.putExtra("Questions",queCount)
                finish.putExtra("Correct", correctCount)
                startActivity(finish)
                finish()
            }
        }
    }
}