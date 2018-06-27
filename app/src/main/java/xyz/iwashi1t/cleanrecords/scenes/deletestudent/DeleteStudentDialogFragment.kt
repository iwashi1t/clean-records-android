package xyz.iwashi1t.cleanrecords.scenes.deletestudent

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import xyz.iwashi1t.cleanrecords.R

interface DeleteStudentDisplayLogic {
  fun displayStudent(viewModel: DeleteStudentModel.GetStudent.ViewModel)
  fun displayDeleteStudent(viewModel: DeleteStudentModel.DeleteStudent.ViewModel)
}

class DeleteStudentDialogFragment : DialogFragment(), DeleteStudentDisplayLogic {
  private lateinit var interactor: DeleteStudentBusinessLogic
  lateinit var router: DeleteStudentRouterInterface

  // MARK: - Constructor

  init {
    setup()
  }

  private fun setup() {
    val interactor = DeleteStudentInteractor()
    val presenter = DeleteStudentPresenter()
    val router = DeleteStudentRouter()

    this.interactor = interactor
    this.router = router
    interactor.presenter = presenter
    presenter.fragment = this
    router.fragment = this
    router.dataStore = interactor
  }

  // MARK: - Fragment

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    isCancelable = false

    return AlertDialog.Builder(requireContext())
      .setPositiveButton(R.string.ok) { _, _ ->
        deleteStudent()
      }
      .setNegativeButton(R.string.cancel, null)
      .create()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    getStudent()

    return super.onCreateView(inflater, container, savedInstanceState)
  }

  // MARK: - GetStudent

  private fun getStudent() {
    val request = DeleteStudentModel.GetStudent.Request()
    interactor.getStudent(request)
  }

  override fun displayStudent(viewModel: DeleteStudentModel.GetStudent.ViewModel) {
    (this.dialog as AlertDialog).setMessage(getString(R.string.message_confirm_delete_student, viewModel.name))
  }

  // MARK: - DeleteStudent

  private fun deleteStudent() {
    val request = DeleteStudentModel.DeleteStudent.Request()
    interactor.deleteStudent(request)
  }

  override fun displayDeleteStudent(viewModel: DeleteStudentModel.DeleteStudent.ViewModel) {
    router.routeToListStudents()
  }
}
