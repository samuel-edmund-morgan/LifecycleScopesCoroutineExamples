package com.example.lifecyclescopescoroutineexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.example.lifecyclescopescoroutineexamples.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val activityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val TAG = "ActivityMain"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.btnStartActivity.setOnClickListener {

            //If we use here GlobalScope instead of lifecycleScope there will
            //be memory leak and this coroutine will be infinite even though
            //we finish() our first Activity.

            //Also we can use in the same way viewModelScope it will keep my couruootine alive as long as my ViewModel is alive

            lifecycleScope.launch(Dispatchers.IO){
                while (true){
                    delay(1000)
                    Log.d(TAG, "Coroutine running...")
                }
            }
            GlobalScope.launch(Dispatchers.IO) {
                delay(5000)
                Intent(this@MainActivity, SecondActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

        }
    }

}