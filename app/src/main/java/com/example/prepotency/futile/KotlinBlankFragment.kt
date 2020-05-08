package com.example.prepotency.futile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prepotency.main.home.container.HomeContainerFragment
import com.example.prepotency.R
import com.example.prepotency.bean.result.HotResult
import com.example.prepotency.main.home.container.ClassContract
import com.example.prepotency.main.home.container.ClassPresenter
import kotlinx.android.synthetic.main.fragment_kotlin_blank.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KotlinBlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KotlinBlankFragment : HomeContainerFragment() {

    override fun createPresenter(): ClassContract.Presenter<ClassContract.View>? {
        return ClassPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kotlin_blank, container, false)
    }

    override fun init() {
        super.init()
        textView.append("\ninit()")
    }

    override fun onLazyLoad() {
        textView.append("\nonLazyLoad()")
    }

    override fun showResult(hotResult: HotResult) {
        textView.append("\nshowResult()")
    }

    override fun onError(error: String) {
        textView.append("\nonError()")
    }

}
