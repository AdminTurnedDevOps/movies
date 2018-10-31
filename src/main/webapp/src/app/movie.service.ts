import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Movie} from "./movie";

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  constructor(private httpClient: HttpClient) {
  }

  loadMovies() {
    return this.httpClient.get<Movie[]>('api/v1/movies');
  }
}
