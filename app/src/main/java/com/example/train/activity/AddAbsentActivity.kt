package com.example.train.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.train.R
import com.example.train.retrofit.getCount
import kotlinx.android.synthetic.main.activity_add_absent.*

class AddAbsentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_absent)

        //输入的缺勤人员的名字
        val name = et_add_absent.text

        btn_add_absent.setOnClickListener {

            getCount("陈阳")

        }




    }
}
