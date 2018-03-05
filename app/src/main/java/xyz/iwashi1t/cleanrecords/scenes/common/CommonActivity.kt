package xyz.iwashi1t.cleanrecords.scenes.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import xyz.iwashi1t.cleanrecords.R

interface CommonActivityListener {
  fun onBackPressed()
}

interface CommonDisplayLogic {
}

class CommonActivity : AppCompatActivity(), CommonDisplayLogic {
  private lateinit var interactor: CommonBusinessLogic
  lateinit var router: CommonRouterInterface

  // MARK: - Constructor

  init {
    setup()
  }

  private fun setup() {
    val interactor = CommonInteractor()
    val presenter = CommonPresenter()
    val router = CommonRouter()

    this.interactor = interactor
    this.router = router
    interactor.presenter = presenter
    presenter.activity = this
    router.activity = this
    router.dataStore = interactor
  }

  // MARK: - AppCompatActivity

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_common)

    router.routeToListStudents()
  }

  override fun onBackPressed() {
    (supportFragmentManager.fragments.last() as? CommonActivityListener)?.run {
      onBackPressed()
    } ?: moveTaskToBack(true)
  }
}
