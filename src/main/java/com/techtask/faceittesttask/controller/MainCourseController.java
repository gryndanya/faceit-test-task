package com.techtask.faceittesttask.controller;

import com.techtask.faceittesttask.dto.CuisineDto;
import com.techtask.faceittesttask.dto.MainCourseDto;
import com.techtask.faceittesttask.exception.BadRequestException;
import com.techtask.faceittesttask.service.MainCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/maincourses")
public class MainCourseController {
    private final MainCourseService mainCourseService;

    @Operation(summary = "Get all mainCourses")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MainCourseDto.class), mediaType = "application/json")}
                    ,description = "Endpoint to get all current mainCourses")})
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MainCourseDto>> getAllMainCourses() {
        return ResponseEntity.ok(mainCourseService.getAllMainCourses());
    }

    @Operation(summary = "Get mainCourse by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MainCourseDto.class), mediaType = "application/json")}
                    ,description = "Endpoint to get mainCourse by id"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = MainCourseDto.class), mediaType = "application/json")}
                    ,description = "Invalid id and mainCourse not founded")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MainCourseDto> getMainCourseById(@PathVariable("id") Long mainCourseId) {
        return ResponseEntity.ok(mainCourseService.getMainCourseById(mainCourseId));
    }

    @Operation(summary = "Get mainCourse by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MainCourseDto.class), mediaType = "application/json")}
                    ,description = "Endpoint to get mainCourse by id"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = MainCourseDto.class), mediaType = "application/json")}
                    ,description = "Invalid id and mainCourse not founded")
    })
    @GetMapping("/cuisine/{cuisine}")
    public ResponseEntity<List<MainCourseDto>> getMainCourseByCousine(@PathVariable("cuisine") CuisineDto cuisineDto) {
        return ResponseEntity.ok(mainCourseService.getAllMainCoursesByCuisine(cuisineDto));
    }

    @Operation(summary = "Create mainCourse")
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = MainCourseDto.class), mediaType = "application/json")},
            description = "Endpoint to create mainCourse")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MainCourseDto> createMainCourse(@Valid @RequestBody MainCourseDto mainCourseDto,
                                                    BindingResult bindingResult) {
        if(mainCourseDto == null || bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getFieldError());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(mainCourseService.createMainCourse(mainCourseDto));
    }

    @Operation(summary = "Update mainCourse")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = MainCourseDto.class), mediaType = "application/json")},
                    description = "Endpoint to update current mainCourse"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = MainCourseDto.class), mediaType = "application/json")},
                    description = "Invalid id and mainCourse not founded")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MainCourseDto> updateMainCourse(@PathVariable("id") Long mainCourseId,
                                                    @Valid @RequestBody MainCourseDto mainCourseDto,
                                                    BindingResult bindingResult) {

        if(mainCourseDto == null || bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getFieldError());
        }

        return ResponseEntity.ok(mainCourseService.updateMainCourse(mainCourseId, mainCourseDto));
    }

    @Operation(summary = "Delete mainCourse")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {
                    @Content(schema = @Schema(implementation = MainCourseDto.class), mediaType = "application/json")},
                    description = "Endpoint to delete current mainCourse"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = MainCourseDto.class), mediaType = "application/json")},
                    description = "Invalid id and mainCourse not founded")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMainCourseById(@PathVariable("id") Long id) {
        mainCourseService.deleteMainCourseById(id);
        return ResponseEntity.noContent().build();
    }
}

