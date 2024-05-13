package com.techtask.faceittesttask.controller;

import com.techtask.faceittesttask.dto.CuisineDto;
import com.techtask.faceittesttask.dto.DessertDto;
import com.techtask.faceittesttask.exception.BadRequestException;
import com.techtask.faceittesttask.service.DessertService;
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
@RequestMapping("/desserts")
public class DessertController {
    private final DessertService dessertService;

    @Operation(summary = "Get all desserts")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = DessertDto.class), mediaType = "application/json")}
                    ,description = "Endpoint to get all current desserts")})
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DessertDto>> getAllDesserts() {
        return ResponseEntity.ok(dessertService.getAllDesserts());
    }

    @Operation(summary = "Get dessert by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = DessertDto.class), mediaType = "application/json")}
                    ,description = "Endpoint to get dessert by id"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = DessertDto.class), mediaType = "application/json")}
                    ,description = "Invalid id and dessert not founded")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DessertDto> getDessertById(@PathVariable("id") Long dessertId) {
        return ResponseEntity.ok(dessertService.getDessertById(dessertId));
    }

    @Operation(summary = "Get dessert by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = DessertDto.class), mediaType = "application/json")}
                    ,description = "Endpoint to get dessert by id"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = DessertDto.class), mediaType = "application/json")}
                    ,description = "Invalid id and dessert not founded")
    })
    @GetMapping("/cuisine/{cuisine}")
    public ResponseEntity<List<DessertDto>> getDessertByCousine(@PathVariable("cuisine") CuisineDto cuisineDto) {
        return ResponseEntity.ok(dessertService.getAllDessertsByCuisine(cuisineDto));
    }

    @Operation(summary = "Create dessert")
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = DessertDto.class), mediaType = "application/json")},
            description = "Endpoint to create dessert")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DessertDto> createDessert(@Valid @RequestBody DessertDto dessertDto,
                                                          BindingResult bindingResult) {
        if(dessertDto == null || bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getFieldError());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(dessertService.createDessert(dessertDto));
    }

    @Operation(summary = "Update dessert")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = DessertDto.class), mediaType = "application/json")},
                    description = "Endpoint to update current dessert"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = DessertDto.class), mediaType = "application/json")},
                    description = "Invalid id and dessert not founded")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DessertDto> updateDessert(@PathVariable("id") Long dessertId,
                                                          @Valid @RequestBody DessertDto dessertDto,
                                                          BindingResult bindingResult) {

        if(dessertDto == null || bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getFieldError());
        }

        return ResponseEntity.ok(dessertService.updateDessert(dessertId, dessertDto));
    }

    @Operation(summary = "Delete dessert")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {
                    @Content(schema = @Schema(implementation = DessertDto.class), mediaType = "application/json")},
                    description = "Endpoint to delete current dessert"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = DessertDto.class), mediaType = "application/json")},
                    description = "Invalid id and dessert not founded")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDessertById(@PathVariable("id") Long id) {
        dessertService.deleteDessertById(id);
        return ResponseEntity.noContent().build();
    }
}

