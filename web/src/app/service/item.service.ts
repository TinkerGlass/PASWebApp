import { Injectable } from '@angular/core';
import {map, tap} from 'rxjs/operators';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Item} from '../models/Item';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  baseUrl = 'https://localhost:8443/pas/api';
  headers = new HttpHeaders({Authorization: 'Basic ' + btoa('Superuser:admin')});

  constructor(private http: HttpClient) { }

  getItems() {
   return this.http.get(`${this.baseUrl}/items`, {headers: this.headers})
     .pipe(
       tap((data: Item[]) => {
         data.forEach((item: Item) => {
           item.releaseDate = new Date(item.releaseDate);
         });
       })
     );
  }

  getItem(id: number) {
    return this.http.get(`${this.baseUrl}/items/${id}`, {headers: this.headers})
      .pipe(
        tap((data: Item) => {
          data.releaseDate = new Date(data.releaseDate);
        })
      );
  }
  deletItem(id: number) {
    return this.http.delete(`${this.baseUrl}/items/${id}`, {headers: this.headers});
  }

  saveItem(item: Item) {
    if (item.id == null) {
      return this.http.post(`${this.baseUrl}/items`, item, {headers: this.headers});
    } else {
      return this.http.put(`${this.baseUrl}/items/${item.id}`, item, {headers: this.headers});
    }
  }
}
