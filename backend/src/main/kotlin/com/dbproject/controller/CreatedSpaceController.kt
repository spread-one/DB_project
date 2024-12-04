package com.dbproject.controller

import com.dbproject.dto.CreateSpaceRequest
import com.dbproject.dto.CreatedSpaceResponse
import com.dbproject.service.CreatedSpaceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/spaces")  // API 기본 경로
class CreatedSpaceController(private val createdSpaceService: CreatedSpaceService) {

    // 모든 공간 조회
    @GetMapping
    fun getAllSpaces(): ResponseEntity<List<CreatedSpaceResponse>> {
        val spaces = createdSpaceService.getAllSpaces()
        return ResponseEntity.ok(spaces)
    }

    // 특정 설명으로 공간 검색
    @GetMapping("/search")
    fun findSpaceByDescription(@RequestParam description: String): ResponseEntity<List<CreatedSpaceResponse>> {
        val spaces = createdSpaceService.findSpaceByDescription(description)
        return ResponseEntity.ok(spaces)
    }

    // 새로운 공간 생성
    @PostMapping
    fun createSpace(@RequestBody request: CreateSpaceRequest): ResponseEntity<CreatedSpaceResponse> {
        val createdSpace = createdSpaceService.createSpace(request)
        return ResponseEntity.ok(createdSpace)
    }
}
