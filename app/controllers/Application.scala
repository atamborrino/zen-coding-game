package controllers

import play.api.mvc._
import play.api.templates.Html
import util.Random
import base.TemplatePastes
import play.api.i18n.Messages

object Application extends Controller {

  def index = Action { implicit request =>
    val message = request.flash.get("error").map(Html(_)).getOrElse(Html(Messages("enter.code")))
    val paste = request.flash.get("paste").getOrElse(TemplatePastes.default.content.get)
    val id = request.flash.get("id").getOrElse(TemplatePastes.default.id) match {
      case s:String => s.toLong
      case l:Long => l
    }
    Ok(views.html.index(message, paste, id)).withCookies(Cookie("uid", uid, maxAge = Some(Int.MaxValue)))
  }

  def uid(implicit request: Request[AnyContent]): String = {
    request.cookies.get("uid").map(_.value).getOrElse(Random.alphanumeric.take(30).mkString)
  }
}