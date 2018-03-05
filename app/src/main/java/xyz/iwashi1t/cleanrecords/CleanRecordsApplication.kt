package xyz.iwashi1t.cleanrecords

import android.app.Application
import io.realm.Realm

class CleanRecordsApplication : Application() {
  companion object {
    lateinit var instance: CleanRecordsApplication
      private set
  }

  // MARK: - Application

  override fun onCreate() {
    super.onCreate()
    instance = this

    Realm.init(this)
  }
}
