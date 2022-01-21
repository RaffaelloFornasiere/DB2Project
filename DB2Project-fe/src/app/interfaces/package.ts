import {telecoServices} from "./TelecoServices";

export type Package = {
  id: number;
  name: string;
  telecoServices: telecoServices[];
}
