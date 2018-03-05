package xyz.iwashi1t.cleanrecords.scenes.common

import xyz.iwashi1t.cleanrecords.scenes.common.CommonPresentationLogic

interface CommonBusinessLogic {

}

interface CommonDataStore {

}

class CommonInteractor : CommonBusinessLogic, CommonDataStore {
  lateinit var presenter: CommonPresentationLogic

  // MARK: - CommonDataStore


  // MARK: - CommonBusinessLogic


}
