import ApiService from '@/api'

const BASE_URL = '/paths';

const PathService = {
  get(source, target, type) {
    return ApiService.get(`${BASE_URL}`, { source, target, type });
  }
}

export default PathService
