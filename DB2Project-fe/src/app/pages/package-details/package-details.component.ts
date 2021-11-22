import { Component, OnInit } from '@angular/core';
import {PackageComponent} from "../../components/package/package.component";
import {Package} from "../../interfaces/package";
import {AppRoutingModule} from "../../app-routing.module";
import {ActivatedRoute} from "@angular/router";
import {PackageService} from "../../services/package.service";

@Component({
  selector: 'app-package-details',
  templateUrl: './package-details.component.html',
  styleUrls: ['./package-details.component.scss']
})
export class PackageDetailsComponent implements OnInit {
  package!: Package;
  constructor(private route: ActivatedRoute,
              private packageService: PackageService) { }

  ngOnInit(): void {
    let id = Number(this.route.snapshot.paramMap.get('id'));
    this.packageService.getDetails(id).subscribe(
      {
        next: data=>{
          this.package = data;
        }
      }
    )
  }

}
