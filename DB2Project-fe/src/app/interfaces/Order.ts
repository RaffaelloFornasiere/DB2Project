import {Package} from "./package";

export type Order = {
  package: Package,
  additionalPackages: {id: number, name: string}[],
  validityPeriod: {id:number},

}
