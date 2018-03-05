package xyz.iwashi1t.cleanrecords.scenes.common

import xyz.iwashi1t.cleanrecords.R
import xyz.iwashi1t.cleanrecords.scenes.liststudents.ListStudentsFragment

interface CommonRoutingLogic {
  fun routeToListStudents()
}

interface CommonDataPassing {
  var dataStore: CommonDataStore
}

interface CommonRouterInterface : CommonRoutingLogic, CommonDataPassing

class CommonRouter: CommonRouterInterface {
  lateinit var activity: CommonActivity
  override lateinit var dataStore: CommonDataStore

  // MARK: - CommonRoutingLogic

  override fun routeToListStudents() {
    val listStudentsFragment = ListStudentsFragment()

    activity.supportFragmentManager
      .beginTransaction()
      .replace(R.id.container_main, listStudentsFragment)
      .commit()
  }
}
