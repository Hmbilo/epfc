package eu.epfc.kotlingames

class Elephant(override val name: String) : Animal() {
    override val weight: Float = 1.0f;
    override val speed: Float = 0.3f;
    override val agility: Float = 0.3f;
    override val force: Float = 0.7f;

    override fun makeSound() {
        TODO("Not yet implemented")
    }
}