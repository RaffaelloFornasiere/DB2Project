import {Package} from "./package";
import {User} from "./user";

export interface  Order  {
  id: number | null,
  orderDate: Date
  package: Package,
  user: User
  optionalPackages: {id: number, name: string}[],
  validityPeriod: {id:number},
  startDate: Date

}