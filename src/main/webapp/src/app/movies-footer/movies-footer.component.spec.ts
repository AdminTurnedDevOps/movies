import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoviesFooterComponent } from './movies-footer.component';

describe('MoviesFooterComponent', () => {
  let component: MoviesFooterComponent;
  let fixture: ComponentFixture<MoviesFooterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoviesFooterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoviesFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
