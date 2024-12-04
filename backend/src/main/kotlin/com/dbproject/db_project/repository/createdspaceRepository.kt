package com.yourproject.repository

import com.yourproject.entity.CreatedSpace
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreatedSpaceRepository : JpaRepository<CreatedSpace, Int> {
    // 특정 설명으로 공간 검색
    fun findByDescription(description: String): List<CreatedSpace>
}
