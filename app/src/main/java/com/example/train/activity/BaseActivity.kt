package com.example.train.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.DialogTitle
import androidx.appcompat.widget.Toolbar
import com.example.train.R
import kotlinx.android.synthetic.main.my_toolbar.*

open class BaseActivity : AppCompatActivity() {

    val my_toolbar
    get() = toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    protected fun Toolbar.init(title: String, @DrawableRes icon: Int = R.drawable.back_icon,
                               listener: View.OnClickListener ?= View.OnClickListener { finish() }) {

        this.title = title
        setSupportActionBar(this)
        if (listener == null) {
            navigationIcon = null
        } else {
            setNavigationIcon(icon)
            setNavigationOnClickListener(listener)
        }
    }

}
