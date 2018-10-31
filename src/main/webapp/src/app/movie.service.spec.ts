import {of} from "rxjs/index";
import {MovieService} from "./movie.service";
import {Movie} from "./movie";
import {TestBed} from "@angular/core/testing";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";


const movie1: Movie = { id: 1, title: "Batman Begins", posterUrl: "https://batman", ratings: [] };
const movie2: Movie = { id: 2, title: "Ted", posterUrl: "https://ted", ratings: [] };

describe('MovieService', () => {

  it('should be tested in a simple way without a TestBed', () => {
    const fakeHttpClient = {
      get: () => of([movie1, movie2])
    };
    const movieService = new MovieService(<any>fakeHttpClient);

    movieService.loadMovies().subscribe(movies => {
      expect(movies[0].title).toBe('Batman Begins');
    });
  });

  it('should be tested with a jasmine spy', () => {
    let spyHttpClient = jasmine.createSpyObj('HttpClient', ['get']);
    spyHttpClient.get.and.returnValue(of([movie1, movie2]));
    const movieService = new MovieService(spyHttpClient);

    movieService.loadMovies().subscribe(movies => {
      expect(movies[0].title).toBe('Batman Begins');
      expect(spyHttpClient.get.calls.count()).toBe(1);
    });
  });

  it('should test the service using Angular TestBed', () => {
    TestBed.configureTestingModule(
      {
        imports: [HttpClientTestingModule],
        providers: [MovieService]
      }
    );

    TestBed.get(MovieService).loadMovies().subscribe(movies => {
      expect(movies[0].title).toBe('Batman Begins');
    });

    const httpMock = TestBed.get(HttpTestingController);
    let request = httpMock.expectOne('api/v1/movies');
    request.flush([movie1, movie2]);

    httpMock.verify();
  });
});
