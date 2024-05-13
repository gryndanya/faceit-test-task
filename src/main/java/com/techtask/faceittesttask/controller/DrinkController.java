package com.techtask.faceittesttask.controller;

import com.techtask.faceittesttask.dto.DrinkDto;
import com.techtask.faceittesttask.exception.BadRequestException;
import com.techtask.faceittesttask.service.DrinkService;
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
@RequestMapping("/drinks")
public class DrinkController {
    private final DrinkService drinkService;

    @Operation(summary = "Get all drinks")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = DrinkDto.class), mediaType = "application/json")}
                    ,description = "Endpoint to get all current drinks")})
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DrinkDto>> getAllDrinks() {
        return ResponseEntity.ok(drinkService.getAllDrinks());
    }

    @Operation(summary = "Get drink by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = DrinkDto.class), mediaType = "application/json")}
                    ,description = "Endpoint to get drink by id"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = DrinkDto.class), mediaType = "application/json")}
                    ,description = "Invalid id and drink not founded")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DrinkDto> getDrinkById(@PathVariable("id") Long drinkId) {
        return ResponseEntity.ok(drinkService.getDrinkById(drinkId));
    }

    @Operation(summary = "Create drink")
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = DrinkDto.class), mediaType = "application/json")},
            description = "Endpoint to create drink")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DrinkDto> createDrink(@Valid @RequestBody DrinkDto drinkDto,
                                                    BindingResult bindingResult) {
        if(drinkDto == null || bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getFieldError());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(drinkService.createDrink(drinkDto));
    }

    @Operation(summary = "Update drink")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = DrinkDto.class), mediaType = "application/json")},
                    description = "Endpoint to update current drink"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = DrinkDto.class), mediaType = "application/json")},
                    description = "Invalid id and drink not founded")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DrinkDto> updateDrink(@PathVariable("id") Long drinkId,
                                                    @Valid @RequestBody DrinkDto drinkDto,
                                                    BindingResult bindingResult) {

        if(drinkDto == null || bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getFieldError());
        }

        return ResponseEntity.ok(drinkService.updateDrink(drinkId, drinkDto));
    }

    @Operation(summary = "Delete drink")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {
                    @Content(schema = @Schema(implementation = DrinkDto.class), mediaType = "application/json")},
                    description = "Endpoint to delete current drink"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = DrinkDto.class), mediaType = "application/json")},
                    description = "Invalid id and drink not founded")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrinkById(@PathVariable("id") Long id) {
        drinkService.deleteDrinkById(id);
        return ResponseEntity.noContent().build();
    }
}
