package xyz.iwashi1t.cleanrecords.scenes.showrecords

import xyz.iwashi1t.cleanrecords.R

interface ShowRecordsPresentationLogic {
  fun presentRecords(response: ShowRecords.FetchRecords.Response)
}

class ShowRecordsPresenter : ShowRecordsPresentationLogic {
  lateinit var fragment: ShowRecordsDisplayLogic

  override fun presentRecords(response: ShowRecords.FetchRecords.Response) {
    val name = response.student.name

    response.error?.let {
      val viewModel = ShowRecords.FetchRecords.ErrorViewModel(
        name,
        R.string.message_error_get_records
      )
      fragment.displayRecordsError(viewModel)
    } ?: run {
      val records = response.records

      val totalScore = (records?.englishScore?.let { it } ?: 0)
        .plus(records?.mathScore?.let { it } ?: 0)
        .plus(records?.scienceScore?.let { it } ?: 0)

      val recordsViewModel = ShowRecords.RecordsViewModel(
        totalScore.toString(),
        records?.englishScore?.toString() ?: "",
        records?.mathScore?.toString() ?: "",
        records?.scienceScore?.toString() ?: ""
      )

      val viewModel = ShowRecords.FetchRecords.ViewModel(
        name,
        recordsViewModel
      )
      fragment.displayRecords(viewModel)
    }
  }
}
