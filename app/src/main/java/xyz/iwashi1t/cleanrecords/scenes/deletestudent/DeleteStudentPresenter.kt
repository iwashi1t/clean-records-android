package xyz.iwashi1t.cleanrecords.scenes.deletestudent

interface DeleteStudentPresentationLogic {
  fun presentStudent(response: DeleteStudentModel.GetStudent.Response)
  fun presentDeleteStudent(response: DeleteStudentModel.DeleteStudent.Response)
}

class DeleteStudentPresenter : DeleteStudentPresentationLogic {
  lateinit var fragment: DeleteStudentDisplayLogic

  override fun presentStudent(response: DeleteStudentModel.GetStudent.Response) {
    val viewModel = DeleteStudentModel.GetStudent.ViewModel(response.student.name)
    fragment.displayStudent(viewModel)
  }

  override fun presentDeleteStudent(response: DeleteStudentModel.DeleteStudent.Response) {
    val viewModel = DeleteStudentModel.DeleteStudent.ViewModel()
    fragment.displayDeleteStudent(viewModel)
  }
}
