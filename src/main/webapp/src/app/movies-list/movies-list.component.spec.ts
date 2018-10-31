import {TestBed} from '@angular/core/testing';
import {MoviesListComponent} from './movies-list.component';
import {FormBuilder, ReactiveFormsModule} from "@angular/forms";
import {MovieService} from "../movie.service";
import {of} from "rxjs/index";
import {Movie} from "../movie";
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {By} from "@angular/platform-browser";


const movie1: Movie = {id: 1, title: "Batman Begins", posterUrl: "https://batman", ratings: []};
const movie2: Movie = {id: 2, title: "Ted", posterUrl: "https://ted", ratings: []};

const movieServiceMock = {
  loadMovies: () => {
    return of([movie1, movie2]);
  }
};

describe('MoviesListComponent', () => {

  it('should apply the search filter correctly', () => {
    TestBed.configureTestingModule({
      providers: [MoviesListComponent, FormBuilder, {provide: MovieService, useValue: movieServiceMock}]
    });

    const moviesListComponent = TestBed.get(MoviesListComponent);
    moviesListComponent.ngOnInit();
    moviesListComponent.onSearch();

    expect(moviesListComponent.displayedMovies.length).toBe(2);
    expect(moviesListComponent.displayedMovies[0].title).toBe('Batman Begins');

    moviesListComponent.searchForm.controls.movieTitle.value = 'te';
    moviesListComponent.onSearch();

    expect(moviesListComponent.displayedMovies.length).toBe(1);
    expect(moviesListComponent.displayedMovies[0].title).toBe('Ted');

    moviesListComponent.searchForm.controls.movieTitle.value = '';
    moviesListComponent.onSearch();

    expect(moviesListComponent.displayedMovies.length).toBe(2);
    expect(moviesListComponent.displayedMovies[0].title).toBe('Batman Begins');
  });

  it('should write the movies into the DOM', () => {
    TestBed.configureTestingModule({
      providers: [{provide: MovieService, useValue: movieServiceMock}],
      declarations: [MoviesListComponent],
      imports: [ReactiveFormsModule],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    });

    const fixture = TestBed.createComponent(MoviesListComponent);
    fixture.detectChanges();

    const movieEntries = fixture.debugElement.queryAll(By.css('.movie-header'));

    expect(movieEntries.length).toBe(2);
    expect(movieEntries[0].childNodes[0].nativeNode.textContent).toBe('Batman Begins');
  });
});
