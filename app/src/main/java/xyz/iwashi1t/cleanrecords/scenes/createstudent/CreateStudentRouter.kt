package xyz.iwashi1t.cleanrecords.scenes.createstudent

import xyz.iwashi1t.cleanrecords.R
import xyz.iwashi1t.cleanrecords.scenes.deletestudent.DeleteStudentDialogFragment
import xyz.iwashi1t.cleanrecords.scenes.liststudents.ListStudentsFragment

interface CreateStudentRoutingLogic {
  fun routeToListStudents()
  fun routeToDeleteStudent()
}

interface CreateStudentDataPassing {
  var dataStore: CreateStudentDataStore
}

interface CreateStudentRouterInterface : CreateStudentRoutingLogic, CreateStudentDataPassing

class CreateStudentRouter: CreateStudentRouterInterface {
  lateinit var fragment: CreateStudentFragment
  override lateinit var dataStore: CreateStudentDataStore

  // MARK: - CreateStudentRoutingLogic

  override fun routeToListStudents() {
    val listStudentsFragment = ListStudentsFragment()

    fragment.requireFragmentManager()
      .beginTransaction()
      .replace(R.id.container_main, listStudentsFragment)
      .commit()
  }

  override fun routeToDeleteStudent() {
    val student = dataStore.student ?: return

    DeleteStudentDialogFragment().apply {
      router.dataStore.student = student
    }.show(fragment.fragmentManager, null)
  }
}
