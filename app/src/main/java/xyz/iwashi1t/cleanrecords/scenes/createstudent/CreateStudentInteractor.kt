package xyz.iwashi1t.cleanrecords.scenes.createstudent

import xyz.iwashi1t.cleanrecords.models.Student
import xyz.iwashi1t.cleanrecords.providers.StudentProvider
import xyz.iwashi1t.cleanrecords.services.StudentRealmStore
import java.util.UUID

interface CreateStudentBusinessLogic {
  fun getStudent(request: CreateStudent.GetStudent.Request)
  fun updateStudent(request: CreateStudent.UpdateStudent.Request)
}

interface CreateStudentDataStore {
  var student: Student?
}

class CreateStudentInteractor : CreateStudentBusinessLogic, CreateStudentDataStore {
  lateinit var presenter: CreateStudentPresentationLogic

  private val studentProvider = StudentProvider(StudentRealmStore())

  // MARK: - CreateStudentDataStore

  override var student: Student? = null

  // MARK: - CreateStudentBusinessLogic

  override fun getStudent(request: CreateStudent.GetStudent.Request) {
    val response = CreateStudent.GetStudent.Response(this.student)
    presenter.presentStudent(response)
  }

  override fun updateStudent(request: CreateStudent.UpdateStudent.Request) {
    val name = request.student.name
    val gender = request.student.gender

    val student = this.student?.let {
      it.apply {
        this.name = name
        this.gender = gender
      }
    } ?: Student(
      UUID.randomUUID().toString(),
      name,
      gender
    )

    studentProvider.updateStudent(student)

    val response = CreateStudent.UpdateStudent.Response()
    presenter.presentUpdateStudent(response)
  }
}
