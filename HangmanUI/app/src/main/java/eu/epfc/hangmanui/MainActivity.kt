package eu.epfc.hangmanui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    private lateinit var rectangleViews : MutableList<View>
    private var tryCount = 0

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

        for(indicatorView in rectangleViews){
            indicatorView.alpha = 0.2f
        }
    }

    fun onClickOkButton(view : View){
        tryCount++

        updateTryCountViews()
    }

    fun updateTryCountViews(){
        for (i in 0 until tryCount){
            rectangleViews[i].alpha = 1f
        }
        for (i in tryCount until rectangleViews.size){
            rectangleViews[i].alpha = 0.2f
        }
    }
}