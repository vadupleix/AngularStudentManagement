export interface Ship {
    id: number;
    shipName: string;
    country: string;
    type: string;
    freqVeryLow: number;
    freqLow: number;
    freqMed: number;
    freqHigh: number;
    freqVeryHigh: number;
    freqActive: number;
    tpk: number;
    numBlades: number;
}
