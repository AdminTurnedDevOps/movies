import { Component, OnInit } from '@angular/core';
import {Movie} from "../movie";
import { FormBuilder } from '@angular/forms';
import {MovieService} from "../movie.service";

@Component({
  selector: 'movies-list',
  templateUrl: './movies-list.component.html',
  styleUrls: ['./movies-list.component.css']
})
export class MoviesListComponent implements OnInit {

  searchForm;
  allMovies: Movie[];
  displayedMovies: Movie[];
  errorLoadingMovies = false;
  selectedMovieId: number;

  constructor(private fb: FormBuilder, private movieService: MovieService) {
    this.searchForm = fb.group({
      'movieTitle': []
    });
  }

  ngOnInit() {
    this.movieService.loadMovies()
      .subscribe(movies => {
        this.allMovies = movies;
        this.displayedMovies = movies;
      },error => {
        this.errorLoadingMovies = true;
      });
  }

  onSearch() {
    this.selectedMovieId = undefined;
    this.applyFilter(this.searchForm.controls.movieTitle.value);
  }

  showMovieDetails(movieId: number) {
    this.selectedMovieId = movieId;
  }

  private applyFilter(movieTitle) {
    this.displayedMovies = this.allMovies.filter(movie => !movieTitle || movie.title.toLowerCase().includes(movieTitle.toLowerCase()));
  }

}
