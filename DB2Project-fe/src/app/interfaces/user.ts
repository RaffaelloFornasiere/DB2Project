import {Admin} from "./admin";

export interface User {
  id: number;
  name: string;
  surname: string;
  location: string;
  referent: Admin;
}
