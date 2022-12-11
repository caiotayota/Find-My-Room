import axios from 'axios';
import { BASE_URL } from '../utils/request';

export default axios.create({
  baseURL: BASE_URL + '/api',
});
