import {User} from "./user";

export interface Admin {
  id: number;
  name: string;
  surname: string;
  location: string;
  associatedUsers: User[];
}
