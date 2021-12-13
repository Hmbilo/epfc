package eu.epfc.kotlingames

import kotlin.random.Random

fun main(){
    var player = Tiger("King Tiger", true)
    val enemies : List<Animal> = createEnemies()

    var gameStatus = ""
    for (enemy in enemies) {
        println("Next enemy : ${enemy.name}")

        while (enemy.lifePoints > 0 && player.lifePoints > 0) {
            println("Click on the 'a' button to start fighting !")
            val askForLetter = readLine()

            if(askForLetter == "a"){
                player.attack(enemy)
                println("${player.name} lifePoints left : ${player.lifePoints}, " + if(enemy.lifePoints > 0) "${enemy.name} lifePoints left : ${enemy.lifePoints}" else "${enemy.name} is dead!")
            }

        }

        if(player.lifePoints <= 0 ) {
            gameStatus = "Game Over !"
            break
        }
    }

    if(gameStatus == ""){
        gameStatus = "You win the game !"
    }

    println(gameStatus)
}

fun  createEnemies() : List<Animal>{

    //List of types
    val types = listOf(Tiger("Tiger", true), Elephant("Elephant"), Chimpanzee("Chimpanzee"), Snake("Snack"))


    //Empty list
    var animalsList : MutableList<Animal> = mutableListOf<Animal>()

    for (i in 5 downTo 0){
        animalsList.add(enemyCreator(Random.nextInt(4), i))
    }
    return animalsList;
}

fun enemyCreator(numEnemey: Int, loopNum : Int) : Animal{
    var createdAnimal : Animal = Tiger("Tiger", true)

    when(numEnemey){
        0 -> createdAnimal = Tiger("Tiger_$loopNum", true)
        1 -> createdAnimal = Elephant("Elephant_$loopNum")
        2 -> createdAnimal = Chimpanzee("Chimpanzee_$loopNum")
        3 -> createdAnimal = Snake("Snack_$loopNum")
    }

    return createdAnimal
}

