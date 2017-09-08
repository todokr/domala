package domala.internal.macros

import scala.meta.{Term, Type}

sealed trait DomaType

object DomaType {
  case object Map extends DomaType
  case class Seq(elementDomaType: DomaType, elementType: Type) extends DomaType
  case class Option(elementDomaType: DomaType, elementType: Type) extends DomaType
  case class Basic(originalType: Type, convertedType: Type, wrapperSupplier: Term.Function) extends DomaType
  case class Domain(value: Basic) extends DomaType
  case object Embeddable extends DomaType
  case class Entity(tpe: Type) extends DomaType
  case class EntityOrDomain(tpe: Type) extends DomaType
  case object Unknown extends DomaType
}