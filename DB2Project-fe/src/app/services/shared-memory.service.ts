import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedMemoryService {
  data: any;
  constructor() {
    this.data = {}
  }
}
