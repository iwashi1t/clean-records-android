package xyz.iwashi1t.cleanrecords.services

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import xyz.iwashi1t.cleanrecords.models.Gender
import xyz.iwashi1t.cleanrecords.models.Student
import xyz.iwashi1t.cleanrecords.providers.StudentStoreInterface

open class StudentObject(
  @PrimaryKey open var studentId: String = "",
  open var name: String = "",
  open var gender: String? = null
) : RealmObject() {
  companion object {
    fun from(student: Student): StudentObject {
      return StudentObject(
        student.studentId,
        student.name,
        student.gender?.name
      )
    }
  }

  val student: Student
    get() = Student(
      studentId,
      name,
      gender?.let { Gender.from(it) }
    )
}

class StudentRealmStore : StudentStoreInterface {
  override fun fetchStudents(): List<Student>? {
    return Realm.getDefaultInstance().use {
      it.where(StudentObject::class.java)
        .findAll()
        .map { it.student }
    }
  }

  override fun updateStudent(student: Student) {
    val studentObject = StudentObject.from(student)

    Realm.getDefaultInstance().use {
      it.run {
        beginTransaction()
        copyToRealmOrUpdate(studentObject)
        commitTransaction()
      }
    }
  }

  override fun deleteStudent(studentId: String) {
    Realm.getDefaultInstance().use {
      val studentObject = it.where(StudentObject::class.java)
        .equalTo("studentId", studentId)
        .findFirst()

      it.run {
        beginTransaction()
        studentObject.deleteFromRealm()
        commitTransaction()
      }
    }
  }
}
