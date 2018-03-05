package xyz.iwashi1t.cleanrecords.scenes.liststudents

import xyz.iwashi1t.cleanrecords.models.Student
import xyz.iwashi1t.cleanrecords.providers.StudentProvider
import xyz.iwashi1t.cleanrecords.services.StudentRealmStore

interface ListStudentsBusinessLogic {
  fun fetchStudents(request: ListStudents.FetchStudents.Request)
}

interface ListStudentsDataStore {
  var students: List<Student>?
}

class ListStudentsInteractor : ListStudentsBusinessLogic, ListStudentsDataStore {
  lateinit var presenter: ListStudentsPresentationLogic

  private val studentProvider = StudentProvider(StudentRealmStore())

  // MARK: - ListStudentsDataStore

  override var students: List<Student>? = null

  // MARK: - ListStudentsBusinessLogic

  override fun fetchStudents(request: ListStudents.FetchStudents.Request) {
    val students = studentProvider.fetchStudents()
    this.students = students

    val response = ListStudents.FetchStudents.Response(students)
    presenter.presentStudents(response)
  }
}
