'''
 * CS3210 - Principles of Programming Languages - Fall 2022
 * Instructor: Thyago Mota
 * Student: 
 * Description: Homework 02 - Diamond Problem
'''

class Device: 

    def __init__(self, manufacturer) -> None:
        pass

    def __str__(self) -> str:
        return ""

class Printer(Device): 

    def __init__(self, manufacturer, type) -> None:
        pass

    def __str__(self) -> str:
        return ""

class Scanner(Device): 

    def __init__(self, manufacturer, dpi) -> None:
        pass 

    def __str__(self) -> str:
        return ""

class MultifunctionPrinter(Printer, Scanner): 

    def __init__(self, manufacturer, type, dpi) -> None:
        pass

    def __str__(self) -> str:
        return ""

if __name__ == '__main__':
    ts6300 = MultifunctionPrinter("Canon", "Inkjet", "600")
    print(ts6300)