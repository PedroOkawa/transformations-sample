package me.okawa.transformations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val factory: ViewModelFactory = ViewModelFactory()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupListeners()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java).apply {
            text.observe(this@MainActivity, Observer(::onTextChanged))
            textUpperCase.observe(this@MainActivity, Observer(::onTextUpperCaseChanged))
            textDistinct.observe(this@MainActivity, Observer(::onTextDistinctChanged))
            users.observe(this@MainActivity, Observer(::onUsersChanged))
        }
    }

    private fun setupListeners() {
        et_main_content.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                viewModel.setText(editable?.toString().orEmpty())
            }

            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Do nothing
            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Do nothing
            }
        })
    }

    private fun onTextChanged(value: String) {
        tv_main_normal.text = value
    }

    private fun onTextUpperCaseChanged(value: String) {
        tv_main_upper_case.text = value
    }

    private fun onTextDistinctChanged(value: String) {
        tv_main_distinct.text = value
    }

    private fun onUsersChanged(value: List<String>) {
        tv_main_users.text = value.joinToString()
    }
}
