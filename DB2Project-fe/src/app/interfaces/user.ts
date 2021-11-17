import {Admin} from "./admin";

export type User = {
  username: string;
  name: string;
  surname: string;
  billingAddress: string;
  token: string;
  birthdate: Date;
  roles: string[];
  referent: Admin;
}
