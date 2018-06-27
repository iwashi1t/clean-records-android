package xyz.iwashi1t.cleanrecords.scenes.deletestudent

import xyz.iwashi1t.cleanrecords.models.Student

object DeleteStudentModel {
  object GetStudent {
    class Request
    data class Response(
      val student: Student
    )
    data class ViewModel(
      val name: String
    )
  }

  object DeleteStudent {
    class Request
    class Response
    class ViewModel
  }
}
