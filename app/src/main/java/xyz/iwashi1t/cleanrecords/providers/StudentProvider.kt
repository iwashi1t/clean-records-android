package xyz.iwashi1t.cleanrecords.providers

import xyz.iwashi1t.cleanrecords.models.Student

interface StudentStoreInterface {
  fun fetchStudents(): List<Student>?
  fun updateStudent(student: Student)
  fun deleteStudent(studentId: String)
}

class StudentProvider(private val studentStore: StudentStoreInterface) {
  fun fetchStudents(): List<Student>? {
    return studentStore.fetchStudents()
  }

  fun updateStudent(student: Student) {
    studentStore.updateStudent(student)
  }

  fun deleteStudent(studentId: String) {
    studentStore.deleteStudent(studentId)
  }
}
