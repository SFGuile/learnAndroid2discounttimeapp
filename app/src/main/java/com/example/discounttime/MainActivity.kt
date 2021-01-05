package com.example.discounttime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.discounttime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
       // setContentView(R.layout.activity_main)
        setContentView(binding.root)
        binding.caculatButton.setOnClickListener {
             caldiscount()
        }
    }

    fun caldiscount(){
        val stringIntheTextField=binding.plainTextInput.text.toString()
        if (stringIntheTextField==null)
        {
            binding.discountResult.text=""
            return
        }

        val cost=stringIntheTextField.toDoubleOrNull()

        if (cost==null)
        {
            binding.discountResult.text=""
            return
        }


        val selectId=binding.discountOption.checkedRadioButtonId
        val disPercentage=when(selectId){
            R.id.option_20_percent->0.2
            R.id.option_18_percent->0.18
            R.id.option_15_percent->0.15
            else->0.0
        }

        if (disPercentage==0.0)
        {
            binding.discountResult.text=""
            return
        }

        var distcountamount=(1-disPercentage)*cost.toDouble()
        val roundup=binding.roundUpSwitch.isChecked

        if (roundup){
           distcountamount=kotlin.math.ceil((1-disPercentage)*cost.toDouble())
        }

        val formatteddiscountamount=NumberFormat.getCurrencyInstance().format(distcountamount)
        binding.discountResult.text=formatteddiscountamount
    }
}