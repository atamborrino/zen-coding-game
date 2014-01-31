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
    Ok(views.html.index(message, paste)).withCookies(Cookie("uid", uid, maxAge = Some(Int.MaxValue)))
  }

  def uid(implicit request: Request[AnyContent]): String = {
    request.cookies.get("uid").map(_.value).getOrElse(Random.alphanumeric.take(30).mkString)
  }
}