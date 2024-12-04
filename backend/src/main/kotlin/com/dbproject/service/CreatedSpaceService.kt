package com.dbproject.service

import com.dbproject.dto.CreateSpaceRequest
import com.dbproject.dto.CreatedSpaceResponse
import com.dbproject.entity.CreatedSpace
import com.dbproject.repository.CreatedSpaceRepository
import org.springframework.stereotype.Service

@Service
class CreatedSpaceService(private val createdSpaceRepository: CreatedSpaceRepository) {

    // 모든 공간 조회
    fun getAllSpaces(): List<CreatedSpaceResponse> {
        val spaces = createdSpaceRepository.findAll()
        return spaces.map { it.toResponse() }
    }

    // 특정 설명으로 공간 검색
    fun findSpaceByDescription(description: String): List<CreatedSpaceResponse> {
        val spaces = createdSpaceRepository.findByDescription(description)
        return spaces.map { it.toResponse() }
    }

    // 새로운 공간 생성
    fun createSpace(request: CreateSpaceRequest): CreatedSpaceResponse {
        val space = CreatedSpace(
            description = request.description,
            availableStartTime = request.availableStartTime,
            availableEndTime = request.availableEndTime
        )
        val savedSpace = createdSpaceRepository.save(space)
        return savedSpace.toResponse()
    }

    // Extension function to convert CreatedSpace to CreatedSpaceResponse
    private fun CreatedSpace.toResponse(): CreatedSpaceResponse {
        return CreatedSpaceResponse(
            id = this.id,
            description = this.description,
            availableStartTime = this.availableStartTime,
            availableEndTime = this.availableEndTime
        )
    }
}
