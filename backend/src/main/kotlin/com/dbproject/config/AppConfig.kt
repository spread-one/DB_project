import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

@Configuration
class AppConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

//    @Bean
//    fun reservationValidator(): ReservationValidator {
//        return ReservationValidator()  // 예약 검증 로직
//    }
}
// 공통으로 사용할 Bean을 등록합니다. 예를 들어, 날짜/시간 처리를 위한 ObjectMapper나 예약 시간 검증 로직 등을 넣을 수 있습니다.