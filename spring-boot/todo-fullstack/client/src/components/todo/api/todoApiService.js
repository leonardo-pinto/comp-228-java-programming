import { apiClient } from "./httpClient";

export function retrieveTodosByUser(username) {
  return apiClient.get(`/users/${username}/todos`);
}

export function retrieveTodo(username, id) {
  return apiClient.get(`/users/${username}/todos/${id}`);
}

export function deleteTodoById(username, id) {
  return apiClient.delete(`/users/${username}/todos/${id}`);
}

export function updateTodo(username, id, todo) {
  return apiClient.put(`/users/${username}/todos/${id}`, todo);
}

export function createTodo(username, todo) {
  return apiClient.post(`/users/${username}/todos`, todo);
}
