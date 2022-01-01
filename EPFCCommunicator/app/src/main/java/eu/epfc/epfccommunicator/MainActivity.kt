package eu.epfc.epfccommunicator

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("WrongViewCast")
    fun onSendButtonClicked(view: View){

        val messageEditText : EditText = findViewById(R.id.edit_text_message)
        val editTextString = messageEditText.text.toString()

        // create an explicite intent with class ReceiveMessageActivity
        val intent = Intent(this, ReceiveMessageActivity::class.java)

        // transmit data to the intent
        intent.putExtra(Intent.EXTRA_TEXT, editTextString)

        startActivity(intent)
    }

    fun OnShareButtonClicked(view: View){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"

        val messageEditText : EditText = findViewById(R.id.edit_text_message)
        val editTextString = messageEditText.text.toString()

        intent.putExtra(Intent.EXTRA_TEXT, editTextString)
        startActivity(intent)
    }
}