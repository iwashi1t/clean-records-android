package xyz.iwashi1t.cleanrecords.services

import xyz.iwashi1t.cleanrecords.models.Error
import xyz.iwashi1t.cleanrecords.models.Records
import xyz.iwashi1t.cleanrecords.providers.RecordsStoreInterface
import java.util.Random

class RecordsMemoryTest : RecordsStoreInterface {
  companion object {
    const val SCORE_MAX = 100
  }

  override fun fetchRecords(studentId: String, completionHandler: (Records?, Error?) -> Unit) {
    val random = Random()

    val englishScore = random.nextInt(SCORE_MAX)
    val mathScore = random.nextInt(SCORE_MAX)
    val scienceScore = random.nextInt(SCORE_MAX)

    // Makes to return an error occasionally.
    val error = if ((englishScore + mathScore + scienceScore) % 5 == 0) Error.NOT_FOUND else null

    val records = error?.let { null } ?: Records(
      studentId,
      englishScore,
      mathScore,
      scienceScore
    )

    completionHandler(records, error)
  }
}
