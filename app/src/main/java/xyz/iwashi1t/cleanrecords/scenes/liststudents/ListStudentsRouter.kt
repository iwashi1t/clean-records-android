package xyz.iwashi1t.cleanrecords.scenes.liststudents

import xyz.iwashi1t.cleanrecords.R
import xyz.iwashi1t.cleanrecords.scenes.createstudent.CreateStudentFragment
import xyz.iwashi1t.cleanrecords.scenes.showrecords.ShowRecordsFragment


interface ListStudentsRoutingLogic {
  fun routeToCreateStudent(studentId: String? = null)
  fun routeToShowRecords(studentId: String)
}

interface ListStudentsDataPassing {
  var dataStore: ListStudentsDataStore
}

interface ListStudentsRouterInterface : ListStudentsRoutingLogic, ListStudentsDataPassing

class ListStudentsRouter: ListStudentsRouterInterface {
  lateinit var fragment: ListStudentsFragment
  override lateinit var dataStore: ListStudentsDataStore

  // MARK: - ListStudentsRoutingLogic

  override fun routeToCreateStudent(studentId: String?) {
    val createStudentFragment = CreateStudentFragment().apply {
      router.dataStore.student = dataStore.students?.find { it.studentId == studentId }
    }

    fragment.requireFragmentManager()
      .beginTransaction()
      .replace(R.id.container_main, createStudentFragment)
      .commit()
  }

  override fun routeToShowRecords(studentId: String) {
    val student = dataStore.students?.find { it.studentId == studentId } ?: return

    val showRecordsFragment = ShowRecordsFragment().apply {
      router.dataStore.student = student
    }

    fragment.requireFragmentManager()
      .beginTransaction()
      .replace(R.id.container_main, showRecordsFragment)
      .commit()
  }
}
