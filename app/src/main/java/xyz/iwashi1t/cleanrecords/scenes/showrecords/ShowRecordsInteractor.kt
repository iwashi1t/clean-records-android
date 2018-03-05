package xyz.iwashi1t.cleanrecords.scenes.showrecords

import xyz.iwashi1t.cleanrecords.models.Student
import xyz.iwashi1t.cleanrecords.providers.RecordsProvider
import xyz.iwashi1t.cleanrecords.services.RecordsMemoryTest

interface ShowRecordsBusinessLogic {
  fun fetchRecords(request: ShowRecords.FetchRecords.Request)
}

interface ShowRecordsDataStore {
  var student: Student
}

class ShowRecordsInteractor : ShowRecordsBusinessLogic, ShowRecordsDataStore {
  lateinit var presenter: ShowRecordsPresentationLogic

  private val recordsProvider = RecordsProvider(
    RecordsMemoryTest()
//    RecordsWebApi()
  )

  // MARK: - ShowRecordsDataStore

  override lateinit var student: Student

  // MARK: - ShowRecordsBusinessLogic

  override fun fetchRecords(request: ShowRecords.FetchRecords.Request) {
    val student = this.student

    recordsProvider.fetchRecords(student.studentId) { records, error ->
      val response = ShowRecords.FetchRecords.Response(
        records,
        student,
        error
      )
      presenter.presentRecords(response)
    }
  }
}
