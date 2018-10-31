import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MOVIES} from "./mock-movies";
import {of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  constructor(private httpClient: HttpClient) {
  }

  loadMovies() {
    // TODO: load real movies from backend
    return of(MOVIES);
    //return this.httpClient.get<Movie[]>('api/movies');
  }
}
