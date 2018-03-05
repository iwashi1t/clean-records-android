package xyz.iwashi1t.cleanrecords.scenes.showrecords

import xyz.iwashi1t.cleanrecords.R
import xyz.iwashi1t.cleanrecords.scenes.liststudents.ListStudentsFragment

interface ShowRecordsRoutingLogic {
  fun routeToListStudents()
}

interface ShowRecordsDataPassing {
  var dataStore: ShowRecordsDataStore
}

interface ShowRecordsRouterInterface : ShowRecordsRoutingLogic, ShowRecordsDataPassing

class ShowRecordsRouter: ShowRecordsRouterInterface {
  lateinit var fragment: ShowRecordsFragment
  override lateinit var dataStore: ShowRecordsDataStore

  // MARK: - ShowRecordsRoutingLogic

  override fun routeToListStudents() {
    val listStudentsFragment = ListStudentsFragment()

    fragment.requireFragmentManager()
      .beginTransaction()
      .replace(R.id.container_main, listStudentsFragment)
      .commit()
  }
}
