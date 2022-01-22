import {Package} from "./package";

export interface  Order  {
  id: number | null,
  orderDate: Date
  servicePackage: Package,
  optionalPackages: {id: number, name: string}[],
  validityPeriod: {id:number},
  startDate: Date

}
