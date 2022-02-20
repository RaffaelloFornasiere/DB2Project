import {Package} from "./package";
import {OptionalPackage} from "./OptionalPackage";
import {ValidityPeriod} from "./ValidityPeriod";

export interface PackageDetails {
  package: Package | undefined,
  optionalPackages: OptionalPackage[],
  validityPeriods: ValidityPeriod[]
}
