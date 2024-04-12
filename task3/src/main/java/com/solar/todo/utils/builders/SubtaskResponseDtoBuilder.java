package com.solar.todo.utils.builders;

import com.solar.todo.api.models.responses.SubtaskResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public final class SubtaskResponseDtoBuilder {
    private Long id;
    private String description;
    private Boolean closed;
    private LocalDateTime expiration;

    private SubtaskResponseDtoBuilder() {
    }

    public static SubtaskResponseDtoBuilder aSubtaskResponseDto() {
        return new SubtaskResponseDtoBuilder();
    }

    public SubtaskResponseDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SubtaskResponseDtoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public SubtaskResponseDtoBuilder withClosed(Boolean closed) {
        this.closed = closed;
        return this;
    }

    public SubtaskResponseDtoBuilder withExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
        return this;
    }

    public SubtaskResponseDto build() {
        return new SubtaskResponseDto(id, description, closed, expiration);
    }
}
