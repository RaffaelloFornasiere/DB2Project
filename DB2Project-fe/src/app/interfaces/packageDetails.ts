import {Package} from "./package";

export interface PackageDetails {
  package: Package,
  optionalPackages: { id: number, name: string, description: string, cost: number }[],
  validityPeriods: { id: number, name: string, period: string }[]
}
