import {Package} from "./package";
import {OptionalPackage} from "./OptionalPackage";
import {ValidityPeriod} from "./ValidityPeriod";

export interface PackageDetails {
  package: Package,
  optionalPackages: OptionalPackage[],
  validityPeriods: ValidityPeriod[]
}
