import { Directive, forwardRef, Attribute } from '@angular/core';
import { Validator, AbstractControl, NG_VALIDATORS } from '@angular/forms';
import * as moment from 'moment';

@Directive({
  selector: '[dateBefore][formControlName],[dateBefore][formControl],[dateBefore][ngModel]',
  providers: [
    { provide: NG_VALIDATORS, useExisting: forwardRef(() => DateBeforeValidator), multi: true }
  ]
})
export class DateBeforeValidator implements Validator {

  constructor( @Attribute('dateBefore') public dateBefore: string,
    @Attribute('isStartDate') public isStartDate: boolean) { }

  validate(c: AbstractControl): { [key: string]: any } {
    // self value
    const v = c.value;

    // control vlaue
    const e = c.root.get(this.dateBefore);

    const dateBeforeValue = e ? moment(e.value, 'DD/MM/YYYY') : null;

    if (!this.isStartDate) {
      // value before
      if (e && moment(v, 'DD/MM/YYYY').isSameOrBefore(dateBeforeValue)) {
        return {
          dateBefore: true
        };
      }

      // value after
      if (e && moment(v, 'DD/MM/YYYY').isAfter(dateBeforeValue)) {
        if (e.errors) {
          delete e.errors['dateBefore'];
          if (!Object.keys(e.errors).length) {
            e.setErrors(null);
          }
        }
      }
    } else {
      // value after
      if (e && moment(v, 'DD/MM/YYYY').isSameOrAfter(dateBeforeValue)) {
        e.setErrors({
          dateBefore: true
        });
        return {
          dateBefore: true
        };
      }

      // value before
      if (e && moment(v, 'DD/MM/YYYY').isBefore(dateBeforeValue)) {
        if (e.errors) {
          delete e.errors['dateBefore'];
          if (!Object.keys(e.errors).length) {
            e.setErrors(null);
          }
        }
      }
    }

    return null;
  }
}