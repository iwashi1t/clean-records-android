package xyz.iwashi1t.cleanrecords.scenes.liststudents

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import xyz.iwashi1t.cleanrecords.R

interface ListStudentsDisplayLogic {
  fun displayStudents(viewModel: ListStudents.FetchStudents.ViewModel)
}

class ListStudentsFragment : Fragment(), ListStudentsDisplayLogic {
  private lateinit var interactor: ListStudentsBusinessLogic
  private lateinit var router: ListStudentsRouterInterface

  private lateinit var totalTextView: TextView
  private lateinit var recyclerView: RecyclerView

  // MARK: - Constructor

  init {
    setup()
  }

  // MARK: - Fragment

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)

    return inflater.inflate(R.layout.fragment_list_students, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setupViews(view)

    fetchStudents()
  }

  // MARK: - Private

  private fun setup() {
    val interactor = ListStudentsInteractor()
    val presenter = ListStudentsPresenter()
    val router = ListStudentsRouter()

    this.interactor = interactor
    this.router = router
    interactor.presenter = presenter
    presenter.fragment = this
    router.fragment = this
    router.dataStore = interactor
  }

  private fun setupViews(view: View) {
    totalTextView = view.findViewById(R.id.text_view_total) as TextView

    (view.findViewById(R.id.button_add) as Button).run {
      setOnClickListener {
        router.routeToCreateStudent()
      }
    }

    recyclerView = (view.findViewById(R.id.recycler_view) as RecyclerView).apply {
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
  }

  // MARK: - FetchStudents

  private fun fetchStudents() {
    val request = ListStudents.FetchStudents.Request()
    interactor.fetchStudents(request)
  }

  override fun displayStudents(viewModel: ListStudents.FetchStudents.ViewModel) {
    totalTextView.text = viewModel.totalCount
    recyclerView.adapter = StudentAdapter(viewModel.students)
  }

  // MARK: - RecyclerView

  inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var genderImageView: ImageView = view.findViewById(R.id.image_view_gender) as ImageView
    var nameTextView: TextView = view.findViewById(R.id.text_view_name) as TextView
    var editButton: Button = (view.findViewById(R.id.button_edit) as Button)
    var recordButton: Button = (view.findViewById(R.id.button_record) as Button)
  }

  inner class StudentAdapter(private val students: List<ListStudents.StudentViewModel>)
      : RecyclerView.Adapter<StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val view = inflater.inflate(R.layout.list_item_student, parent, false)

      return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
      val student = students[position]

      holder.run {
        genderImageView.setImageResource(student.iconRId)
        nameTextView.text = student.name

        editButton.setOnClickListener {
          router.routeToCreateStudent(student.studentId)
        }

        recordButton.setOnClickListener {
          router.routeToShowRecords(student.studentId)
        }
      }
    }

    override fun getItemCount(): Int {
      return students.size
    }
  }
}
