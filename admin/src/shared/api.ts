import axios from 'axios'

export const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8000/api'

export const http = axios.create({ baseURL: API_BASE })
