@Configuration
class CorsConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")  // 모든 경로에 대해 CORS 허용
            .allowedOrigins("http://localhost:3000")  // 프론트엔드 도메인
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 허용 HTTP 메서드
            .allowCredentials(true)  // 인증 정보 허용 (필요 시)
    }
}
// CORS(Cross-Origin Resource Sharing) 설정은 프론트엔드와 백엔드가 다른 도메인에서 작동할 경우 필요한 설정입니다.