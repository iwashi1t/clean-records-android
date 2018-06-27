package xyz.iwashi1t.cleanrecords.scenes.deletestudent

import xyz.iwashi1t.cleanrecords.R
import xyz.iwashi1t.cleanrecords.scenes.liststudents.ListStudentsFragment

interface DeleteStudentRoutingLogic {
  fun routeToListStudents()
}

interface DeleteStudentDataPassing {
  var dataStore: DeleteStudentDataStore
}

interface DeleteStudentRouterInterface : DeleteStudentRoutingLogic, DeleteStudentDataPassing

class DeleteStudentRouter: DeleteStudentRouterInterface {
  lateinit var fragment: DeleteStudentDialogFragment
  override lateinit var dataStore: DeleteStudentDataStore

  // MARK: - DeleteStudentRoutingLogic

  override fun routeToListStudents() {
    val listStudentsFragment = ListStudentsFragment()

    fragment.requireFragmentManager()
      .beginTransaction()
      .replace(R.id.container_main, listStudentsFragment)
      .commit()
  }
}
