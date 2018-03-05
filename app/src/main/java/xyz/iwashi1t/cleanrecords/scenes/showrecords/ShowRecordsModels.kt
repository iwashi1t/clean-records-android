package xyz.iwashi1t.cleanrecords.scenes.showrecords

import xyz.iwashi1t.cleanrecords.models.Error
import xyz.iwashi1t.cleanrecords.models.Records
import xyz.iwashi1t.cleanrecords.models.Student

object ShowRecords {
  object FetchRecords {
    class Request
    data class Response(
      val records: Records?,
      val student: Student,
      val error: Error?
    )
    data class ViewModel(
      val name: String,
      val records: RecordsViewModel
    )
    data class ErrorViewModel(
      val name: String,
      val errorMessageRId: Int
    )
  }

  data class RecordsViewModel(
    val totalScore: String,
    val englishScore: String,
    val mathScore: String,
    val scienceScore: String
  )
}
