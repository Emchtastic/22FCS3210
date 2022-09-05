'''
 * CS3210 - Principles of Programming Languages - Fall 2022
 * Instructor: Thyago Mota
 * Student: 
 * Description: Homework 02 - Diamond Problem
'''

class Device: 

    def __init__(self, manufacturer) -> None:
        self.manufacturer = manufacturer
        pass

    def __str__(self) -> str:
        return self.manufacturer

class Printer(Device): 

    def __init__(self, manufacturer, type) -> None:
        self.manufacturer = manufacturer
        self.type = type
        pass

    def __str__(self) -> str:
        return self.manufacturer+" "+self.type

class Scanner(Device):

    def __init__(self, manufacturer, dpi) -> None:
        self.manufacturer = manufacturer
        self.dpi = dpi
        pass

    def __str__(self) -> str:
        return self.manufacturer+" "+" "+self.dpi


class MultifunctionPrinter(Printer, Scanner): 

    def __init__(self, manufacturer, type, dpi):
        self.manufacturer = manufacturer
        self.type = type
        self.dpi = dpi
        pass

    def __str__(self) -> str:
        return self.manufacturer+" "+self.type+" "+self.dpi

if __name__ == '__main__':
    ts6300 = MultifunctionPrinter("Canon", "Inkjet", "600")
    print(ts6300)