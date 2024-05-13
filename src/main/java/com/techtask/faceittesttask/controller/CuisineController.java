package com.techtask.faceittesttask.controller;

import com.techtask.faceittesttask.dto.CuisineDto;
import com.techtask.faceittesttask.exception.BadRequestException;
import com.techtask.faceittesttask.service.CuisineService;
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
@RequestMapping("/cuisines")
public class CuisineController {
    private final CuisineService cuisineService;

    @Operation(summary = "Get all cuisines")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CuisineDto.class), mediaType = "application/json")}
                    ,description = "Endpoint to get all current cuisines")})
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CuisineDto>> getAllCuisines() {
        return ResponseEntity.ok(cuisineService.getAllCuisines());
    }

    @Operation(summary = "Get cuisine by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CuisineDto.class), mediaType = "application/json")}
                    ,description = "Endpoint to get cuisine by id"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = CuisineDto.class), mediaType = "application/json")}
                    ,description = "Invalid id and cuisine not founded")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CuisineDto> getCuisineById(@PathVariable("id") Long cuisineId) {
        return ResponseEntity.ok(cuisineService.getCuisineById(cuisineId));
    }

    @Operation(summary = "Create cuisine")
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = CuisineDto.class), mediaType = "application/json")},
            description = "Endpoint to create cuisine")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CuisineDto> createCuisine(@Valid @RequestBody CuisineDto cuisineDto,
                                              BindingResult bindingResult) {
        if(cuisineDto == null || bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getFieldError());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(cuisineService.createCuisine(cuisineDto));
    }

    @Operation(summary = "Update cuisine")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CuisineDto.class), mediaType = "application/json")},
                    description = "Endpoint to update current cuisine"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = CuisineDto.class), mediaType = "application/json")},
                    description = "Invalid id and cuisine not founded")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CuisineDto> updateCuisine(@PathVariable("id") Long cuisineId,
                                              @Valid @RequestBody CuisineDto cuisineDto,
                                              BindingResult bindingResult) {

        if(cuisineDto == null || bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getFieldError());
        }

        return ResponseEntity.ok(cuisineService.updateCuisine(cuisineId, cuisineDto));
    }

    @Operation(summary = "Delete cuisine")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {
                    @Content(schema = @Schema(implementation = CuisineDto.class), mediaType = "application/json")},
                    description = "Endpoint to delete current cuisine"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = CuisineDto.class), mediaType = "application/json")},
                    description = "Invalid id and cuisine not founded")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuisineById(@PathVariable("id") Long id) {
        cuisineService.deleteCuisineById(id);
        return ResponseEntity.noContent().build();
    }
}
