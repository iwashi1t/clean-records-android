package xyz.iwashi1t.cleanrecords.scenes.createstudent

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import xyz.iwashi1t.cleanrecords.R
import xyz.iwashi1t.cleanrecords.models.Gender
import xyz.iwashi1t.cleanrecords.scenes.common.CommonActivityListener

interface CreateStudentDisplayLogic {
  fun displayStudent(viewModel: CreateStudent.GetStudent.ViewModel)
  fun displayUpdateStudent(viewModel: CreateStudent.UpdateStudent.ViewModel)
}

class CreateStudentFragment : Fragment(), CreateStudentDisplayLogic, CommonActivityListener, CreateStudentSavedDialogFragment.Listener {
  private lateinit var interactor: CreateStudentBusinessLogic
  lateinit var router: CreateStudentRouterInterface

  private lateinit var deleteButton: Button
  private lateinit var nameEditText: EditText
  private lateinit var genderRadioGroup: RadioGroup

  enum class GenderRadioButtonRId(val gender: Gender, val rId: Int) {
    MALE(Gender.MALE, R.id.radio_button_male),
    FEMALE(Gender.FEMALE, R.id.radio_button_female);

    companion object {
      fun from(gender: Gender): GenderRadioButtonRId? {
        return GenderRadioButtonRId.values().find { it.gender == gender }
      }

      fun from(rId: Int): GenderRadioButtonRId? {
        return GenderRadioButtonRId.values().find { it.rId == rId }
      }
    }
  }

  // MARK: - Constructor

  init {
    setup()
  }

  private fun setup() {
    val interactor = CreateStudentInteractor()
    val presenter = CreateStudentPresenter()
    val router = CreateStudentRouter()

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

    return inflater.inflate(R.layout.fragment_create_student, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setupViews(view)

    getStudent()
  }

  private fun setupViews(view: View) {
    deleteButton = (view.findViewById(R.id.button_delete) as Button).apply {
      setOnClickListener {
        router.routeToDeleteStudent()
      }
      visibility = View.GONE
    }

    (view.findViewById(R.id.button_save) as Button).run {
      setOnClickListener {
        updateStudent()
      }
    }

    nameEditText = view.findViewById(R.id.edit_text_name) as EditText

    genderRadioGroup = view.findViewById(R.id.radio_group_gender) as RadioGroup
  }

  // MARK: - CommonActivityListener

  override fun onBackPressed() {
    router.routeToListStudents()
  }

  // MARK: - GetStudent

  private fun getStudent() {
    val request = CreateStudent.GetStudent.Request()
    interactor.getStudent(request)
  }

  override fun displayStudent(viewModel: CreateStudent.GetStudent.ViewModel) {
    val student = viewModel.student

    nameEditText.setText(student.name, TextView.BufferType.EDITABLE)

    student.gender?.let {
      GenderRadioButtonRId.from(it)?.let {
        genderRadioGroup.check(it.rId)
      }
    }

    deleteButton.visibility = viewModel.deleteButtonVisibility
  }

  // MARK: - UpdateStudent

  private fun updateStudent() {
    val name = nameEditText.text.toString()

    val gender = GenderRadioButtonRId.from(genderRadioGroup.checkedRadioButtonId)?.gender

    val student = CreateStudent.StudentViewModel(
      name,
      gender
    )

    val request = CreateStudent.UpdateStudent.Request(student)
    interactor.updateStudent(request)
  }

  override fun displayUpdateStudent(viewModel: CreateStudent.UpdateStudent.ViewModel) {
    CreateStudentSavedDialogFragment.show(requireFragmentManager(), this)
  }

  // MARK: - CreateStudentSavedDialogFragment.Listener

  override fun onSavedDialogOkClick() {
    router.routeToListStudents()
  }
}

class CreateStudentSavedDialogFragment : DialogFragment() {
  interface Listener {
    fun onSavedDialogOkClick()
  }

  companion object {
    fun show(fragmentManager: FragmentManager, listener: Listener) {
      CreateStudentSavedDialogFragment().apply {
        this.listener = listener
      }.show(fragmentManager, null)
    }
  }

  private lateinit var listener: Listener

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    isCancelable = false

    return AlertDialog.Builder(requireContext())
      .setMessage(R.string.message_save_success)
      .setPositiveButton(R.string.ok) { _, _ ->
        listener.onSavedDialogOkClick()
      }
      .create()
  }
}
