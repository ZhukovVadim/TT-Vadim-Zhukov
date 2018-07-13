package  com.vadim.ttvadimzhukov.base


import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.vadim.ttvadimzhukov.App

abstract class BaseActivity : MvpAppCompatActivity() {

    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        App.appComponent.inject(this)
    }

}