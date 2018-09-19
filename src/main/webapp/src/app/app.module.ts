import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MoviesOverviewComponent } from './movies-overview/movies-overview.component';
import { MoviesHeaderComponent } from './movies-header/movies-header.component';
import { MoviesFooterComponent } from './movies-footer/movies-footer.component';

@NgModule({
  declarations: [
    AppComponent,
    MoviesOverviewComponent,
    MoviesHeaderComponent,
    MoviesFooterComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
