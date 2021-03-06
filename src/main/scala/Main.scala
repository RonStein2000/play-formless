package org.tksfz.formless

import play.api.data._
import play.api.data.Forms._
import shapeless._
import shapeless.labelled._
import shapeless.syntax.singleton._

object Main {

  case class Login(username: String, password: String)

  val record =
      ('password ->> nonEmptyText) :: ('username ->> nonEmptyText) :: HNil

  MkMapping.get(('username ->> nonEmptyText) :: HNil)

  val mapper = MkMapping.get(record)

  val request = Map("username" -> Seq("jane"), "password" -> Seq("123456"))

  def main(args: Array[String]): Unit = {
    val form = Form(mapper)
    val form2 = form.bindFromRequest(request)
    println(form2)

    two()
  }

  def two(): Unit = {
    val mapper = MkMapping.forCaseClass[Login].withMappings(record)
    val form = Form(mapper)
    println(form.bindFromRequest(request))
  }
}
