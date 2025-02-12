package com.example.workoutapp

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.workoutapp.data.HistoryDao
import com.example.workoutapp.data.HistoryEntity
import com.example.workoutapp.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class FinishActivity : AppCompatActivity() {

    private var binding: ActivityFinishBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =  ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarFinishActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(this@FinishActivity, "Back button pressed!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
        binding?.toolbarFinishActivity?.setNavigationOnClickListener {
            callback.handleOnBackPressed()
        }
        binding?.btnFinish?.setOnClickListener {
            finish()

        }
        val dao = (application as WorkOutApp).db.historyDao()
        addDateToDatabase(dao)

    }

    private fun addDateToDatabase(historyDao: HistoryDao){

        val c = Calendar.getInstance()
        val dateTime = c.time
        Log.e("Date: ",""+dateTime)

        val sdf = SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)
        Log.e("Formatted Date: ",""+date)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
            Log.e(
                "Date: ",
                "Added..."
            )
        }
    }
}