package com.example.train.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.train.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView() {
        btn_goto_add_absent_activity.setOnClickListener(this)
        btn_goto_check_absent_activity.setOnClickListener(this)
    }


    override fun onClick(v: View) {

        when(v.id) {
            R.id.btn_goto_add_absent_activity -> {
                val intent = Intent(this, AddAbsentActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_goto_check_absent_activity -> {
                val intent = Intent(this, CheckAbsentActivity::class.java)
                startActivity(intent)
            }
        }

    }
}
