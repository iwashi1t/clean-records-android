package xyz.iwashi1t.cleanrecords.scenes.liststudents

import xyz.iwashi1t.cleanrecords.models.Student

object ListStudents {
  object FetchStudents {
    class Request
    data class Response(
      val students: List<Student>?
    )
    data class ViewModel(
      val totalCount: String,
      val students: List<StudentViewModel>
    )
  }

  data class StudentViewModel(
    val studentId: String,
    val iconRId: Int,
    val name: String
  )
}
