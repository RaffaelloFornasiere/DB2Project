import {Package} from "./package";
import {OptionalPackage} from "./OptionalPackage";
import {PackageService} from "../services/package.service";
import {ValidityPeriod} from "./ValidityPeriod";

export interface  Order  {
  id: number | null,
  orderDate: Date
  servicePackage: Package,
  user?: string,
  optionalPackages: OptionalPackage[],
  validityPeriod: ValidityPeriod,
  startDate: Date
  totalValue: number

}
