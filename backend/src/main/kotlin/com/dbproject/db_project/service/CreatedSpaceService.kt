package com.yourproject.service

import com.yourproject.entity.CreatedSpace
import com.yourproject.repository.CreatedSpaceRepository
import org.springframework.stereotype.Service

@Service
class CreatedSpaceService(private val createdSpaceRepository: CreatedSpaceRepository) {

    // 모든 공간 조회
    fun getAllSpaces(): List<CreatedSpace> {
        return createdSpaceRepository.findAll()
    }

    // 특정 설명으로 공간 검색
    fun findSpaceByDescription(description: String): List<CreatedSpace> {
        return createdSpaceRepository.findByDescription(description)
    }
}
