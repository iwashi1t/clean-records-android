package xyz.iwashi1t.cleanrecords.scenes.createstudent

import xyz.iwashi1t.cleanrecords.models.Gender
import xyz.iwashi1t.cleanrecords.models.Student

object CreateStudent {
  object GetStudent {
    class Request
    data class Response(
      val student: Student?
    )
    data class ViewModel(
      val student: StudentViewModel,
      val deleteButtonVisibility: Int
    )
  }

  object UpdateStudent {
    data class Request(
      val student: StudentViewModel
    )
    class Response
    class ViewModel
  }

  object DeleteStudent {
    class Request
    class Response
    class ViewModel
  }

  data class StudentViewModel(
    val name: String,
    val gender: Gender?
  )
}
