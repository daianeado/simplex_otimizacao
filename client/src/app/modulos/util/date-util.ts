export class DateUtil {

    public static LOCALE_PTBR = {
        firstDayOfWeek: 0,
        dayNames: ["Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"],
        dayNamesShort: ["D", "S", "T", "Q", "Q", "S", "S"],
        dayNamesMin: ["D", "S", "T", "Q", "Q", "S", "S"],
        monthNames: ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho",
            "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
        monthNamesShort: ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"]
    };

    yearRangeString(yearsBefore: number, yearsAfter: number) {
        let currentYear = new Date().getFullYear();
        let minYear = currentYear - yearsBefore;
        let maxYear = currentYear + yearsAfter;
        return "" + minYear + ":" + maxYear;
    }



}
