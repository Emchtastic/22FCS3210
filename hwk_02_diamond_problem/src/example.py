'''
 * CS3210 - Principles of Programming Languages - Fall 2022
 * Instructor: Thyago Mota
 * Student: 
 * Description: Homework 02 - Diamond Problem
'''

class Device: 

    def __init__(self, manufacturer):
        self.manufacturer = manufacturer
        pass

    def __str__(self) -> str:
        return self.manufacturer

class Printer(Device): 

    def __init__(self, manufacturer, type, **throwAway):
        super(Printer, self).__init__(manufacturer, **throwAway)
        self.type = type

    def __str__(self) -> str:
        return super(Printer, self).__str__() + " " + self.type

class Scanner(Device):

    def __init__(self, manufacturer, dpi, **throwAway):
        super(Scanner, self).__init__(manufacturer, **throwAway)
        self.dpi = dpi

    def __str__(self) -> str:
        return super(Scanner, self).__str__() + " " + self.dpi



class MultifunctionPrinter(Scanner, Printer):

    def __init__(self, manufacturer, type, dpi):
        super(MultifunctionPrinter, self).__init__(manufacturer=manufacturer, type=type, dpi=dpi)

    def __str__(self) -> str:
        return super(MultifunctionPrinter, self).__str__()



if __name__ == '__main__':
    ts6300 = MultifunctionPrinter("Canon", "Inkjet", "600")
    print(ts6300)

