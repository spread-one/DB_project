import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.boot.autoconfigure.domain.EntityScan
import javax.sql.DataSource
import com.zaxxer.hikari.HikariDataSource


@Configuration
@EnableJpaRepositories(basePackages = ["com.dbproject.repository"])
@EntityScan(basePackages = ["com.dbproject.entity"])
class DatabaseConfig {
    
    @Bean
    fun dataSource(): DataSource {
        val dataSource = HikariDataSource()
        dataSource.jdbcUrl = "jdbc:mysql://127.0.0.1:3306/?user=root"  // 데이터베이스 URL
        dataSource.username = "root"  // DB 사용자 이름
        dataSource.password = "root"  // DB 비밀번호
        dataSource.driverClassName = "com.mysql.cj.jdbc.Driver"
        return dataSource
    }
}
//데이터베이스 연결 설정은 JPA와 데이터베이스 통신을 위한 기본 설정을 포함합니다.