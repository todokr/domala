package domala.jdbc.entity

import java.util.function.Supplier

import domala.jdbc.entity
import domala.jdbc.holder.AbstractHolderDesc
import org.seasar.doma.jdbc.domain.DomainType
import org.seasar.doma.jdbc.entity.EntityPropertyType
import org.seasar.doma.jdbc.entity.NamingType
import org.seasar.doma.jdbc.id.IdGenerator
import org.seasar.doma.wrapper.Wrapper

class GeneratedIdPropertyType[PARENT, ENTITY <: PARENT, BASIC <: Number, DOMAIN] (
  entityClass: Class[ENTITY],
  entityPropertyClass: Class[_],
  basicClass: Class[BASIC],
  wrapperSupplier: Supplier[Wrapper[BASIC]],
  parentEntityPropertyType: EntityPropertyType[PARENT, BASIC],
  domainType: DomainType[BASIC, DOMAIN],
  name: String,
  columnName: String,
  namingType: NamingType,
  quoteRequired: Boolean,
  idGenerator: IdGenerator
) extends org.seasar.doma.jdbc.entity.GeneratedIdPropertyType[PARENT, ENTITY, BASIC, DOMAIN] (
  entityClass,
  entityPropertyClass,
  basicClass,
  wrapperSupplier,
  parentEntityPropertyType,
  domainType,
  name,
  columnName,
  namingType,
  quoteRequired,
  idGenerator) {

 override def createProperty: entity.DefaultProperty[_, ENTITY, BASIC] = DefaultPropertyType.createPropertySupplier[ENTITY, BASIC, DOMAIN](field, entityPropertyClass, wrapperSupplier, domainType)()

}

object GeneratedIdPropertyType {
  def ofDomain[ENTITY, BASIC <: Number, DOMAIN](
    entityClass: Class[ENTITY],
    entityPropertyClass: Class[_],
    domainType: AbstractHolderDesc[BASIC, DOMAIN],
    name: String,
    columnName: String,
    namingType: NamingType,
    quoteRequired: Boolean,
    idGenerator: IdGenerator
  ): GeneratedIdPropertyType[ENTITY, ENTITY, BASIC, DOMAIN] = {
    new GeneratedIdPropertyType[ENTITY, ENTITY, BASIC, DOMAIN](
      entityClass,
      entityPropertyClass,
      domainType.getBasicClass.asInstanceOf[Class[BASIC]],
      domainType.wrapper,
      null,
      domainType,
      name,
      columnName,
      namingType,
      quoteRequired,
      idGenerator
    )
  }
}