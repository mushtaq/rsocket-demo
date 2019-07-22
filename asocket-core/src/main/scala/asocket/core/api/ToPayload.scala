package asocket.core.api

import akka.stream.scaladsl.Source
import io.rsocket.Payload

import scala.concurrent.{ExecutionContext, Future}

trait ToPayload[T] {
  def toPayload(input: T): Payload
}

object ToPayload {
  implicit class RichInputTo[T](input: T) {
    def payload[S >: T: ToPayload]: Payload = implicitly[ToPayload[S]].toPayload(input)
  }

  implicit class RichFutureTo[T](input: Future[T]) {
    def payload[S >: T: ToPayload](implicit ec: ExecutionContext): Future[Payload] = input.map(_.payload[S])
  }

  implicit class RichSourceTo[T, Mat](input: Source[T, Mat]) {
    def payload[S >: T: ToPayload]: Source[Payload, Mat] = input.map(_.payload[S])
  }
}
