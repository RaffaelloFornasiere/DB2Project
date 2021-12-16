import {Package} from "./package";

export type Purchase = {
  package: Package,
  additionalPackages: {id: number, name: string}[],
  validityPeriod: {id:number},

}
