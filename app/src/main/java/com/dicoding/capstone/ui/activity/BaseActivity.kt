package com.dicoding.capstone.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.dicoding.capstone.BaseApplication
import com.dicoding.capstone.R

/**
 * Created by Vicky Setiady on 2020
 */
abstract class BaseActivity : AppCompatActivity() {

    private var mainContent: View? = null
    private var infoContent: View? = null
    private var titleText: TextView? = null
    private var progressBar: View? = null

    abstract fun getLayoutId(): Int

    abstract fun setupData()

    abstract fun setupComponent()

    abstract fun setupObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        BaseApplication.setCurrentActivity(this)
        (application as BaseApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        /* Force Disable Dark Theme */
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(getLayoutId())
        setupData()
        setupComponent()
        setupObserver()
    }

    override fun onResume() {
        if (BaseApplication.getCurrentActivity() == null)
            BaseApplication.setCurrentActivity(this)
        super.onResume()
    }

    override fun onPause() {
        clearCurrentActivity()
        super.onPause()
    }

    override fun onDestroy() {
        clearCurrentActivity()
        super.onDestroy()
    }

    private fun clearCurrentActivity() {
        if (this == BaseApplication.getCurrentActivity())
            BaseApplication.setCurrentActivity(null)
    }

    fun setContent(
        mainContent: View?,
        infoContent: View?,
        titleText: TextView?,
        progressBar: View?
    ) {
        this.mainContent = mainContent
        this.infoContent = infoContent
        this.titleText = titleText
        this.progressBar = progressBar
    }

    fun enableBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun setZeroToolbarElevation() {
        supportActionBar?.elevation = 0F
    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    fun getErrorMessage(code: String): String {
        return when (code) {
            "400" -> resources.getString(R.string.error_400_toast)
            "401" -> resources.getString(R.string.error_401_toast)
            "403" -> resources.getString(R.string.error_403_toast)
            "404" -> resources.getString(R.string.error_404_toast)
            "500" -> resources.getString(R.string.error_500_toast)
            else -> resources.getString(R.string.general_error_toast)
        }
    }

    fun showInformation(
        isShow: Boolean,
        message: String = resources.getString(R.string.empty_list)
    ) {
        progressBar?.visibility = View.GONE
        if (isShow) {
            infoContent?.visibility = View.VISIBLE
            titleText?.text = message
            mainContent?.visibility = View.GONE
        } else {
            infoContent?.visibility = View.GONE
            mainContent?.visibility = View.VISIBLE
        }

    }

    fun showLoading(state: Boolean) {
        showInformation(false)
        if (state) {
            progressBar?.visibility = View.VISIBLE
            mainContent?.visibility = View.GONE
        } else {
            progressBar?.visibility = View.GONE
            mainContent?.visibility = View.VISIBLE
        }
    }
}