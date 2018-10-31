import { TestBed } from '@angular/core/testing';
import { MovieDetailsComponent } from './movie-details.component';


describe('MovieDetailsComponent', () => {
  it('should create', () => {
    TestBed.configureTestingModule({
      providers: [MovieDetailsComponent]
    });

    const movieDetailsComponent = TestBed.get(MovieDetailsComponent);
    expect(movieDetailsComponent).toBeTruthy();
  });
});
