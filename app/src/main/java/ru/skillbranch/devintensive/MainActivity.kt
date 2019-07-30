package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity() {
    object SavedInstancesConst {

        const val STATUS = "STATUS"
        const val QUESTION = "QUESTION"
    }
    //VAR INIT
    lateinit var benderImage: ImageView

    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView
    lateinit var benderObj: Bender
    //VAR INIT END



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString(SavedInstancesConst.STATUS) ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString(SavedInstancesConst.QUESTION) ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()

//        messageEt.imeOptions = EditorInfo.IME_ACTION_DONE
        messageEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                sendMessage()
//                Toast.makeText(applicationContext,"DONE btn work", Toast.LENGTH_SHORT).show()
                true
            } else {
                false
            }
        }


        sendBtn.setOnClickListener { sendMessage() }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(SavedInstancesConst.STATUS, benderObj.status.name)
        outState?.putString(SavedInstancesConst.QUESTION, benderObj.question.name)
        super.onSaveInstanceState(outState)
    }

    private fun sendMessage() {
        val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString())
        val (r, g, b) = color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = phrase
        messageEt.setText("")
        hideKeyboard()
    }


}
