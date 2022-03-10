import {Observable, of} from "rxjs";
import {stringify} from "@angular/compiler/src/util";

export default class Utils{
  static toJavaType(serviceType: string): string {
    let prefix = "it.polimi.db2.teleco_app.services.models.packagedetails."
    let suffix = "Details"
    return prefix + serviceType.replace(" ", "") + suffix;
  }

  static fromJavaType(serviceType: string): string {
    let prefix = "it.polimi.db2.teleco_app.services.models.packagedetails."
    let suffix = "Details"
    let res = this.camelToText(serviceType.substring(prefix.length, serviceType.indexOf(suffix)));
    console.log(res.trim())
    return res.trim();
  }

  static camelToText(camel: string): string {
    let result = camel.replace(/([A-Z])/g, " $1");
    return result.charAt(0).toUpperCase() + result.slice(1);
  }

  static toCamelCase(str:string) {
    return str.replace(/(?:^\w|[A-Z]|\b\w)/g, function(word, index) {
      return index === 0 ? word.toLowerCase() : word.toUpperCase();
    }).replace(/\s+/g, '');
  }


  static handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.log("error: " + JSON.stringify(error)); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  static log(... args : string[]){
    console.log(this.log.caller.name + ": " + args)
  }


}
