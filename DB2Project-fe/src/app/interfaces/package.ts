import {TelecomService} from "./TelecomService";

export type Package = {
  id?: number;
  name: string;
  telecomServices: TelecomService[];
}
