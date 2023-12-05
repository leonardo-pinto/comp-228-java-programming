import axios from "axios";

// Basic aW4yOG1pbnV0ZXM6ZHVtbXk=

export const apiClient = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
  },
});
