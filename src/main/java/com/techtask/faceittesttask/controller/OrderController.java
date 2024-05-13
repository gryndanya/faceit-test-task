package com.techtask.faceittesttask.controller;

import com.techtask.faceittesttask.dto.OrderDto;
import com.techtask.faceittesttask.exception.BadRequestException;
import com.techtask.faceittesttask.service.OrderService;
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
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Get all orders")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = OrderDto.class), mediaType = "application/json")}
                    ,description = "Endpoint to get all current orders")})
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @Operation(summary = "Get order by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = OrderDto.class), mediaType = "application/json")}
                    ,description = "Endpoint to get order by id"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = OrderDto.class), mediaType = "application/json")}
                    ,description = "Invalid id and order not founded")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @Operation(summary = "Create order")
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = OrderDto.class), mediaType = "application/json")},
            description = "Endpoint to create order")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto,
                                                    BindingResult bindingResult) {
        if(orderDto == null || bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getFieldError());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderDto));
    }

    @Operation(summary = "Update order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = OrderDto.class), mediaType = "application/json")},
                    description = "Endpoint to update current order"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = OrderDto.class), mediaType = "application/json")},
                    description = "Invalid id and order not founded")
    })
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("id") Long orderId,
                                                    @Valid @RequestBody OrderDto orderDto,
                                                    BindingResult bindingResult) {

        if(orderDto == null || bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getFieldError());
        }

        return ResponseEntity.ok(orderService.updateOrder(orderId, orderDto));
    }

    @Operation(summary = "Delete order")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {
                    @Content(schema = @Schema(implementation = OrderDto.class), mediaType = "application/json")},
                    description = "Endpoint to delete current order"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = OrderDto.class), mediaType = "application/json")},
                    description = "Invalid id and order not founded")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}

