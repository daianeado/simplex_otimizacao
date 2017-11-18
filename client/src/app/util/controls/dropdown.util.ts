import { SelectItem } from 'primeng/primeng';
import * as _ from "lodash";
/**
 * Created by raniere.lacerda on 03/05/2017.
 */
export class DropDownUtils {

    static LABEL_DESCRICAO = 'descricao';
    static LABEL_NOME = 'nome';

    static LABEL_NACIONALIDADE = 'nacionalidade';
    static VALUE_ID = 'id';
    static VALUE_CODIGO = 'codigo';

    static buildDropDown(label: string, value: string, array: any[], nullValue: boolean = false): SelectItem[] {
        const sortedArray: string[] = array.sort((n1, n2) => {
            if (n1[label].toLowerCase() > n2[label].toLowerCase()) {
                return 1;
            }
            if (n1[label].toLowerCase() < n2[label].toLowerCase()) {
                return -1;
            }
            return 0;
        });
        const itens: SelectItem[] = [];
        if (nullValue) {
            itens.push({ label: "", value: null });
        }
        sortedArray.forEach(item => {
            itens.push({ label: item[label], value: item[value] });
        });
        return itens;
    }

    static sortArray(array: any[], label: string): any[] {
        return array.sort((n1, n2) => {
            if (n1[label] > n2[label]) {
                return 1;
            }
            if (n1[label] < n2[label]) {
                return -1;
            }
            return 0;
        });
    }

   

    static buildDropDownEntity(valueList, label: string, nullValue: boolean = false): SelectItem[] {
        const sortedArray: string[] = valueList.sort((n1, n2) => {
            if (n1[label].toLowerCase() > n2[label].toLowerCase()) {
                return 1;
            }
            if (n1[label].toLowerCase() < n2[label].toLowerCase()) {
                return -1;
            }

            return 0;
        })

        const items: SelectItem[] = [];
        if (nullValue) {
            items.push({ label: "", value: null });
        }

        sortedArray.forEach(item => {
            items.push({ label: item[label], value: item });

        });

        return items;
    }
}
