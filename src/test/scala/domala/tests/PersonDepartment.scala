package domala.tests

import domala.Entity

@Entity
case class PersonDepartment(
  id: ID,
  name: Name,
  departmentId: Option[ID],
  departmentName:Option[Name]
)
