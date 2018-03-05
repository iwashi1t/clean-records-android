package xyz.iwashi1t.cleanrecords.services

import xyz.iwashi1t.cleanrecords.models.Error
import xyz.iwashi1t.cleanrecords.models.Records
import xyz.iwashi1t.cleanrecords.providers.RecordsStoreInterface

class RecordsWebApi : RecordsStoreInterface {
  override fun fetchRecords(studentId: String, completionHandler: (Records?, Error?) -> Unit) {

    // TODO: Get data from Web API by Retrofit2
/*
    service.fetchRecords(studentId).enqueue(object : Callback<RecordApi>) {
      override fun onResponse(call: Call<RecordApi>, response: Response<RecordApi>) {
        val body = response.body()
        val record = body?.record
        val error = record?.let { null } ?: Error.NOT_FOUND
        completionHandler(record, error)
      }

      override fun onFailure(call: Call<RecordApi>, t: Throwable?) {
        val error = Error.UNKNOWN
        completionHandler(null, error)
      }
    }
*/
  }

  data class RecordApi(
    val studentId: String?,
    val english: Int?,
    val math: Int?,
    val science: Int?
  ) {
    val record: Records?
      get() = studentId?.let {
        Records(
          it,
          english,
          math,
          science
        )
      }
  }
}
