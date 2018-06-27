package xyz.iwashi1t.cleanrecords.scenes.createstudent

import android.view.View

interface CreateStudentPresentationLogic {
  fun presentStudent(response: CreateStudent.GetStudent.Response)
  fun presentUpdateStudent(response: CreateStudent.UpdateStudent.Response)
}

class CreateStudentPresenter : CreateStudentPresentationLogic {
  lateinit var fragment: CreateStudentDisplayLogic

  override fun presentStudent(response: CreateStudent.GetStudent.Response) {
    val student = response.student

    val studentViewModel = CreateStudent.StudentViewModel(
      student?.name ?: "",
      student?.gender
    )

    val deleteButtonVisibility = student?.let { View.VISIBLE } ?: View.GONE

    val viewModel = CreateStudent.GetStudent.ViewModel(
      studentViewModel,
      deleteButtonVisibility
    )
    fragment.displayStudent(viewModel)
  }

  override fun presentUpdateStudent(response: CreateStudent.UpdateStudent.Response) {
    val viewModel = CreateStudent.UpdateStudent.ViewModel()
    fragment.displayUpdateStudent(viewModel)
  }
}
