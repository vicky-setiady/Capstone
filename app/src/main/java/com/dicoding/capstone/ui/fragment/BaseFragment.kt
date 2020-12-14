package com.dicoding.capstone.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dicoding.capstone.BaseApplication
import com.dicoding.capstone.R
import com.dicoding.capstone.ui.ViewModelFactory
import javax.inject.Inject

/**
 * Created by Vicky Setiady on 2020
 */
abstract class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var mainContent: View? = null
    private var infoContent: View? = null
    private var titleText: TextView? = null
    private var progressBar: View? = null

    abstract fun getLayoutId(): Int

    abstract fun setupData()

    abstract fun setupComponent()

    abstract fun setupObserver()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BaseApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupComponent()
        setupObserver()
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