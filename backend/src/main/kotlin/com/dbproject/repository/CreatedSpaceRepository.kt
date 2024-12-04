package com.dbproject.repository  // 변경된 경로에 맞춰 패키지 수정

import com.dbproject.entity.CreatedSpace  // 엔터티의 경로도 수정
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreatedSpaceRepository : JpaRepository<CreatedSpace, Int> {
    // 특정 설명으로 공간 검색
    fun findByDescription(description: String): List<CreatedSpace>
}
