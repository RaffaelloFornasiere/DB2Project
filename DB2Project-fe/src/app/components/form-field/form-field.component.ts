import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from "@angular/forms";
import {F} from "@angular/cdk/keycodes";


export class SelectArg {
  multiple: boolean = false;
  element: string[] = [];

  constructor(multiple: boolean, element: string[]) {
    this.multiple = multiple;
    this.element = element
  }
}


@Component({
  selector: 'app-form-field',
  templateUrl: './form-field.component.html',
  styleUrls: ['./form-field.component.scss']
})
export class FormFieldComponent implements OnInit {
  // private property: string, private type: string,
  // private selectionArgs?: SelectArgs[]
  @Input() propertyName: { name: string, value: string } = {name: "", value: ""};
  @Input() propertyType: string = "string";
  @Input() selectionArgs: { options: string[], selected: string[] } = {options: [], selected: []};
  @Input() multipleSelection: boolean = false;
  selectionFormControl: FormControl = new FormControl(this.selectionArgs.selected);
  nameFormField: FormControl = new FormControl(this.propertyName.value)
  value: string = "";


  constructor() {

  }

  getData() {
    if (this.propertyType === "string")
      return this.nameFormField.value;
    else
      return this.selectionFormControl.value;
  }

  ngOnInit(): void {
    this.value = this.propertyName.value;
  }

}
