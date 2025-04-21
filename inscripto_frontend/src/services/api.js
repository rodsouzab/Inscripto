import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhot:8080'
});

export default api;