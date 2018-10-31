import {Rating} from "./rating";

export class Movie {
  id: number;
  title: string;
  posterUrl: string;
  ratings: Rating[];
}
