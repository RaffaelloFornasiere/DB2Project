import {Package} from "./package";
import {OptionalPackage} from "./OptionalPackage";

export interface PackageDetails {
  package: Package,
  optionalPackages: OptionalPackage[],
  validityPeriods: { id: number, name: string, period: string }[]
}
