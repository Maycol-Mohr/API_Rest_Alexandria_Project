package com.betrybe.alexandria2.dtos;

public record ResponseDTO<T>(String message, T data) {
}