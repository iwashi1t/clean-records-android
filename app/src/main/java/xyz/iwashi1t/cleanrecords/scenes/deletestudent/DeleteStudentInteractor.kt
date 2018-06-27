package xyz.iwashi1t.cleanrecords.scenes.deletestudent

import xyz.iwashi1t.cleanrecords.models.Student
import xyz.iwashi1t.cleanrecords.providers.StudentProvider
import xyz.iwashi1t.cleanrecords.services.StudentRealmStore

interface DeleteStudentBusinessLogic {
  fun getStudent(request: DeleteStudentModel.GetStudent.Request)
  fun deleteStudent(request: DeleteStudentModel.DeleteStudent.Request)
}

interface DeleteStudentDataStore {
  var student: Student
}

class DeleteStudentInteractor : DeleteStudentBusinessLogic, DeleteStudentDataStore {
  lateinit var presenter: DeleteStudentPresentationLogic

  private val studentProvider = StudentProvider(StudentRealmStore())

  // MARK: - DeleteStudentDataStore

  override lateinit var student: Student

  // MARK: - DeleteStudentBusinessLogic

  override fun getStudent(request: DeleteStudentModel.GetStudent.Request) {
    val response = DeleteStudentModel.GetStudent.Response(student)
    presenter.presentStudent(response)
  }

  override fun deleteStudent(request: DeleteStudentModel.DeleteStudent.Request) {
    studentProvider.deleteStudent(student.studentId)

    val response = DeleteStudentModel.DeleteStudent.Response()
    presenter.presentDeleteStudent(response)
  }
}
