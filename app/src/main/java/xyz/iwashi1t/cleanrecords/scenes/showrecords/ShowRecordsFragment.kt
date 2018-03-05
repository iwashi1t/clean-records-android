package xyz.iwashi1t.cleanrecords.scenes.showrecords

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import xyz.iwashi1t.cleanrecords.R
import xyz.iwashi1t.cleanrecords.scenes.common.CommonActivityListener

interface ShowRecordsDisplayLogic {
  fun displayRecords(viewModel: ShowRecords.FetchRecords.ViewModel)
  fun displayRecordsError(viewModel: ShowRecords.FetchRecords.ErrorViewModel)
}

class ShowRecordsFragment : Fragment(), ShowRecordsDisplayLogic, CommonActivityListener {
  private lateinit var interactor: ShowRecordsBusinessLogic
  lateinit var router: ShowRecordsRouterInterface

  private lateinit var hisScoreTextView: TextView
  private lateinit var totalScoreTextView: TextView
  private lateinit var englishScoreTextView: TextView
  private lateinit var mathScoreTextView: TextView
  private lateinit var scienceScoreTextView: TextView

  // MARK: - Constructor

  init {
    setup()
  }

  private fun setup() {
    val interactor = ShowRecordsInteractor()
    val presenter = ShowRecordsPresenter()
    val router = ShowRecordsRouter()

    this.interactor = interactor
    this.router = router
    interactor.presenter = presenter
    presenter.fragment = this
    router.fragment = this
    router.dataStore = interactor
  }

  // MARK: - Fragment

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)

    return inflater.inflate(R.layout.fragment_show_records, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setupViews(view)

    fetchRecords()
  }

  private fun setupViews(view: View) {
    hisScoreTextView = view.findViewById(R.id.text_view_his_score) as TextView
    totalScoreTextView = view.findViewById(R.id.text_view_total_score) as TextView
    englishScoreTextView = view.findViewById(R.id.text_view_english_score) as TextView
    mathScoreTextView = view.findViewById(R.id.text_view_math_score) as TextView
    scienceScoreTextView = view.findViewById(R.id.text_view_science_score) as TextView
  }

  // MARK: - CommonActivityListener

  override fun onBackPressed() {
    router.routeToListStudents()
  }

  // MARK: - FetchRecords

  private fun fetchRecords() {
    val request = ShowRecords.FetchRecords.Request()
    interactor.fetchRecords(request)
  }

  override fun displayRecords(viewModel: ShowRecords.FetchRecords.ViewModel) {
    hisScoreTextView.text = getString(R.string.his_total_score, viewModel.name)

    viewModel.records.let {
      totalScoreTextView.text = it.totalScore
      englishScoreTextView.text = it.englishScore
      mathScoreTextView.text = it.mathScore
      scienceScoreTextView.text = it.scienceScore
    }
  }

  override fun displayRecordsError(viewModel: ShowRecords.FetchRecords.ErrorViewModel) {
    hisScoreTextView.text = getString(R.string.his_total_score, viewModel.name)
    Toast.makeText(context, viewModel.errorMessageRId, Toast.LENGTH_LONG).show()
  }
}
