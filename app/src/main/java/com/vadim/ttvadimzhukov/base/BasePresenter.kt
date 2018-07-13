package  com.vadim.ttvadimzhukov.base


import android.content.Context
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.vadim.ttvadimzhukov.data.DataManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BasePresenter<I : MvpView> : MvpPresenter<I>() {
    init {
        inject()
    }

    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var baseContext: Context
    private val disposables = CompositeDisposable()

    abstract fun inject()

    override fun destroyView(view: I) {
        super.destroyView(view)
        disposables.clear()
    }

    fun addToComposite(disposable: Disposable) = disposables.add(disposable)



}