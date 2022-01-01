package eu.epfc.hangmanui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var rectangleViews : MutableList<View>
    private val gameManager = GameManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rectangleViews = mutableListOf()
        rectangleViews.add(findViewById(R.id.rectangle1))
        rectangleViews.add(findViewById(R.id.rectangle2))
        rectangleViews.add(findViewById(R.id.rectangle3))
        rectangleViews.add(findViewById(R.id.rectangle4))
        rectangleViews.add(findViewById(R.id.rectangle5))
        rectangleViews.add(findViewById(R.id.rectangle6))

        for (indicatorView in rectangleViews){
            indicatorView.alpha = 0.2f
        }

        gameManager.startNewGame(this)
        displayGameState(GameManager.GameState.Running)
        val maskedWordText : TextView = findViewById(R.id.maskedWord)
        maskedWordText.text = gameManager.maskedWord
    }

    /**
     * callback called when the user press the ok button
     */
    fun onClickOkButton(view: View) {
        val editText : EditText = findViewById(R.id.editText)
        val stringEditText = editText.text
        // if the user has entered a letter
        if (stringEditText.isNotEmpty()) {

            gameManager.playLetter(stringEditText[0])

            // update the views representing tryCount
            updateTryCountViews(gameManager.tryCount)

            // update maskedWord TextView
            val maskedWordText : TextView = findViewById(R.id.maskedWord)
            maskedWordText.text = gameManager.maskedWord

            // erase the letter in the EditText
            editText.setText("")

            // display gameOver screen if needed
            displayGameState(gameManager.gameState)
        }
    }

    /**
     * update the appearance of the rectangle views representing tryCount
     * @param tryCount the number of tries
     */
    private fun updateTryCountViews(tryCount : Int){

        for (i in 0 until tryCount){
            rectangleViews[i].alpha = 1f
        }
        for (i in tryCount until rectangleViews.size){
            rectangleViews[i].alpha = 0.2f
        }
    }

    /**
     * Read a GameState object and update the UI accordingly (color of background,
     * retry button, views representing the try count ... etc
     * @param gameState : object representing the current state of the game
     */
    private fun displayGameState(gameState : GameManager.GameState){
        val maskedWordText : TextView = findViewById(R.id.maskedWord)
        val pageTitle : TextView = findViewById(R.id.page_title)
        val retryButton : Button = findViewById(R.id.retry_button)
        val inputLayout : ViewGroup = findViewById(R.id.input_layout)
        when (gameState){
            GameManager.GameState.GameOverLose -> {
                // update masked word TextView
                maskedWordText.text = gameManager.wordToGuess
                pageTitle.text = "GAME OVER"
                maskedWordText.textSize = 50f
                // change the color of the background
                findViewById<LinearLayout>(R.id.root_layout).setBackgroundResource(R.color.red)
                // display retry button
                retryButton.visibility = View.VISIBLE
                inputLayout.visibility = View.INVISIBLE
                hideKeyboard()
            }
            GameManager.GameState.GameOverWin -> {
                // update masked word TextView
                maskedWordText.text = gameManager.wordToGuess
                pageTitle.text = "YOU WIN"
                maskedWordText.textSize = 50f
                // change the color of the background
                findViewById<LinearLayout>(R.id.root_layout).setBackgroundResource(R.color.green)
                // display retry button
                retryButton.visibility = View.VISIBLE
                inputLayout.visibility = View.INVISIBLE
                hideKeyboard()
            }
            GameManager.GameState.Running -> {
                pageTitle.text = "GUESS THE WORD"
                // update masked word TextView
                maskedWordText.text = gameManager.maskedWord
                maskedWordText.textSize = 24f
                // change the color of the background
                findViewById<LinearLayout>(R.id.root_layout).setBackgroundResource(R.color.white)
                // hide retry button
                retryButton.visibility = View.INVISIBLE
                inputLayout.visibility = View.VISIBLE
            }
        }
    }
    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val rootView : View = findViewById(android.R.id.content)
        imm.hideSoftInputFromWindow(rootView.windowToken, 0)
    }

    fun onRetryButton(view: View) {
        gameManager.startNewGame(this)
        displayGameState(GameManager.GameState.Running)
    }
}
