import {Service} from "./Service";

export type Package = {
  id: number;
  name: string;
  services: Service[];
}
