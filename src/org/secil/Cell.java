package org.secil;

class Cell {
    private CellValue value;

    Cell() {
        this.value = CellValue.EMPTY;
    }

    CellValue getCellValue() {
        return value;
    }

    void setCellValue(CellValue value) {
        this.value = value;
    }

    void printValue() {
        switch (value) {
            case S:
                System.out.print(" S ");
                break;
            case O:
                System.out.print(" O ");
                break;
            case EMPTY:
            default:
                System.out.print("   ");
                break;
        }
    }

    static void printCellValue(String value) {
        System.out.print(" "+value+" ");
    }
}
