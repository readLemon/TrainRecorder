package com.example.train.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import com.example.train.R
import kotlinx.android.synthetic.main.my_toolbar.*

abstract class BaseActivity : AppCompatActivity() {

    val my_toolbar
    get() = toolbar
    @get:LayoutRes
    protected abstract val contentVieId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentVieId)
        initView(savedInstanceState)
    }

    protected open fun initView(savedInstanceState: Bundle?) {}

    protected fun Toolbar.init(title: String, @DrawableRes icon: Int = R.drawable.ic_arrow_back,
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

    fun showToast(msg :String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

}
