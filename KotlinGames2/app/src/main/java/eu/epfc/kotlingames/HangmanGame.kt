package eu.epfc.kotlingames
import java.lang.Thread.sleep
import java.io.File
import kotlin.random.Random

fun main(){
    for (i in 2 downTo 0){
        println("Start in ${3-i} seconds")
        sleep(1000)
    }
    println("Game start")

    var wordToGuess = generateWordToGuess()

    // initialise empty array of letters
    var lettersPlayedByUsers : MutableList<Char> = mutableListOf()


    val maskedWord = getMaskedWordToGuess(wordToGuess,lettersPlayedByUsers)
    println("$maskedWord")

    // 8 turns
    var hasWon: Boolean = false

    for(turns in 0..7){
        // ask the user to enter a letter
        println("Type a letter : ")
        val stringInput = readLine()
        if (stringInput != null){
            lettersPlayedByUsers += stringInput[0];

            // display the updated masked word
            val maskedWord = getMaskedWordToGuess(wordToGuess, lettersPlayedByUsers)
            println("$maskedWord")

            if(maskedWord.equals(wordToGuess)){
                hasWon = true;
                break
            }
        }
    }

    if(hasWon){
        println("YOU WON")
    } else {
        println("GAME OVER. The word was $wordToGuess")
    }
}

fun generateWordToGuess() : String {
    val dictionaryFile = File("dictionary.txt")
    val dictionary = dictionaryFile.readLines();
    val randomIndex = Random.nextInt(dictionary.count()-1)

    return dictionary[randomIndex].lowercase()
}

fun getMaskedWordToGuess(wordToGuess: String, playedLetters: List<Char>) : String {
    var maskedWord: String = "";

    for (i in 0 until wordToGuess.length) {

        var letter = wordToGuess[i];

        if (playedLetters.contains(letter)){
            maskedWord += letter
        } else {
            maskedWord += "*"
        }
    }

    return maskedWord;
}