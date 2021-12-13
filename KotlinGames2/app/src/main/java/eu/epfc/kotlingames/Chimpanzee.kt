package eu.epfc.kotlingames

class Chimpanzee(override val name: String): Animal() {
    override val weight: Float = 0.1f;
    override val speed: Float = 0.2f;
    override val agility: Float = 0.7f;
    override val force: Float = 0.4f;

    override fun makeSound(){
        println("$name Grrrrrrr\n")
    }
}