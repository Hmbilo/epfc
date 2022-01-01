package eu.epfc.hangmanui

import android.content.Context
import android.content.res.AssetManager
import java.io.*
import kotlin.random.Random

class GameManager {

    enum class GameState {
        Running, GameOverWin, GameOverLose
    }

    var tryCount = 0
    var wordToGuess = ""
    var maskedWord = ""
    private var playedLetters = mutableListOf<Char>()

    var gameState : GameState = GameState.GameOverWin

    /**
     * start a new game
     */
    fun startNewGame(context: Context){
        tryCount = 0
        wordToGuess = generateWordToGuess(context)
        playedLetters.clear()
        // generate a new word to guess
        maskedWord = getMaskedWordToGuess(wordToGuess, playedLetters)
        gameState = GameState.Running
    }

    /**
     * Update the state of the game with a new letter.
     *
     * This will update properties playedLetters, maskedWord, gameState, and tryCount
     */
    fun playLetter(letter: Char){

        if (!playedLetters.contains(letter)) {

            // update tryCount, only if the letter has not been already played
            if (!wordToGuess.contains(letter)) {
                tryCount++
            }

            // add the letter to playedLetters property
            playedLetters.add(letter)

            // update maskedWord property
            maskedWord = getMaskedWordToGuess(wordToGuess, playedLetters)

            // if more than 6 attempts
            if (tryCount >= 6) {
                // it's game over
                gameState = GameState.GameOverLose
            }
            // if no more asterisks in the masked word -> the word is guessed
            if (!maskedWord.contains('*')) {
                // switch to win mode
                gameState = GameState.GameOverWin
            }
        }
    }

    /**
     * Return a word randomly chosen in a dictionary file
     */
    private fun generateWordToGuess(context : Context) : String {

        // open a file contained in assets directory
        val assetManager : AssetManager = context.assets
        val inputStream : InputStream = assetManager.open("dictionary.txt")

        // create a file reader
        val reader = BufferedReader(InputStreamReader(inputStream))
        val wordList : MutableList<String> = mutableListOf()
        // while the end of file is not reached
        while (reader.ready()) {
            // read a new line
            val line = reader.readLine()
            // store the new line in the MutableList
            wordList.add(line)
        }

        // chose a line randomly
        val randomIndex = Random.nextInt(wordList.count()-1)
        return wordList[randomIndex].toLowerCase()
    }

    /**
     * generate a word masked with asterisks (*), based on a word to guess, and on the
     * letters already used by the player
     * @param wordToGuess : the word the player must guess to win the game
     * @param playedLetters : letters already used by the player
     */
    private fun getMaskedWordToGuess(wordToGuess : String, playedLetters : List<Char>) : String{

        var maskedWord = ""
        for (letter in wordToGuess){

            if (playedLetters.contains(letter)){
                maskedWord += letter
            }
            else{
                maskedWord += '*'
            }
        }
        return maskedWord
    }
}