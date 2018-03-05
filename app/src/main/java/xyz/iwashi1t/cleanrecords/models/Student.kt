package xyz.iwashi1t.cleanrecords.models

data class Student(
  val studentId: String,
  var name: String,
  var gender: Gender?
)

enum class Gender {
  MALE,
  FEMALE;

  companion object {
    fun from(name: String): Gender? {
      return Gender.values().find { it.name == name }
    }
  }
}
