import { Component, OnInit } from '@angular/core';
import { TokenStorageService} from "../../services/token-storage.service";
import {User} from "../../interfaces/user";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user: User | undefined;

  constructor(private token: TokenStorageService) {
  }

  ngOnInit(): void {
    this.user = this.token.getUser();
  }
}
