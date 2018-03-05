package xyz.iwashi1t.cleanrecords.providers

import xyz.iwashi1t.cleanrecords.models.Error
import xyz.iwashi1t.cleanrecords.models.Records

interface RecordsStoreInterface {
  fun fetchRecords(studentId: String, completionHandler: (Records?, Error?) -> Unit)
}

class RecordsProvider(private val recordsStore: RecordsStoreInterface) {
  fun fetchRecords(studentId: String, completionHandler: (Records?, Error?) -> Unit) {
    recordsStore.fetchRecords(studentId, completionHandler)
  }
}
