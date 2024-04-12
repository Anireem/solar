package com.solar.todo.api.constants;

/**
 * Represents routes for all API
 */
public final class ApiRoutes {
    private ApiRoutes() {
    }

    /**
     * Represents routes for Task management APIs.
     */
    public final class Task {
        private Task() {
        }
        public static final String TASK_CONTEXT_PATH = "/api/tasks";
    }
}
