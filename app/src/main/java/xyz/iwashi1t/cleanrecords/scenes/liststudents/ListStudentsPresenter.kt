package xyz.iwashi1t.cleanrecords.scenes.liststudents

import xyz.iwashi1t.cleanrecords.R
import xyz.iwashi1t.cleanrecords.models.Gender

interface ListStudentsPresentationLogic {
  fun presentStudents(response: ListStudents.FetchStudents.Response)
}

class ListStudentsPresenter : ListStudentsPresentationLogic {
  lateinit var fragment: ListStudentsDisplayLogic

  override fun presentStudents(response: ListStudents.FetchStudents.Response) {
    val students = response.students?.map {
      val iconRId = when (it.gender) {
        Gender.MALE -> R.drawable.male
        Gender.FEMALE -> R.drawable.female
        else -> R.drawable.unknown
      }

      ListStudents.StudentViewModel(
        it.studentId,
        iconRId,
        it.name
      )
    } ?: listOf()

    val viewModel = ListStudents.FetchStudents.ViewModel(
      students.size.toString(),
      students
    )
    fragment.displayStudents(viewModel)
  }
}
