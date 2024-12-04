import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus


@ControllerAdvice
class ExceptionHandlerConfig {

//    @ExceptionHandler(ReservationNotFoundException::class)
//    fun handleReservationNotFound(ex: ReservationNotFoundException): ResponseEntity<Map<String, String>> {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
//    }
//
//    @ExceptionHandler(IllegalArgumentException::class)
//    fun handleIllegalArgument(ex: IllegalArgumentException): ResponseEntity<Map<String, String>> {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to ex.message))
//    }
}
// 전역 예외 처리를 설정하여, 예외가 발생했을 때 일관된 응답을 반환합니다.