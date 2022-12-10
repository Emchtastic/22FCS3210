'''
CS3210 - Principles of Programming Languages - Fall 2022
Instructor: Thyago Mota
Description: Homework 12 - Dining Philosopher Problem
Student Name: Alex Emch
'''

import threading
import time
import random


class Philosopher(threading.Thread):

    def __init__(self, name, left_fork, right_fork):
        threading.Thread.__init__(self)
        self.name = name
        self.left_fork = left_fork
        self.right_fork = right_fork

    def think(self):
        time.sleep(
            random.randint(1, 10) / 1000
        )

    def eat(self):
        time.sleep(
            random.randint(1, 50) / 1000
        )

    def run(self):
        while True:
            self.left_fork.acquire()
            print(self.name + " got the left fork")
            self.right_fork.acquire()
            print(self.name + " got the right fork")
            self.think()
            self.eat()
            self.right_fork.release()
            print(self.name + " released the right fork")
            self.left_fork.release()
            print(self.name + " released the left fork")


if __name__ == "__main__":

    # number of philosopher
    n = 5

    # TODO #1: create n=5 forks 
    forks = [threading.Semaphore() for i in range(5)]

    # TODO #2: create n=5 philosophers, allocating the forks in a way to avoid the deadlock
    philosophers = []
    for i in range(n):
        if i % 2 == 0:
            philosopher = Philosopher(str(i + 1), forks[(i + 1) % 5], forks[i % 5])
        else:
            philosopher = Philosopher(str(i + 1), forks[i % 5], forks[(i + 1) % 5])
        philosophers.append(philosopher)

    # TODO #3: start the philosopher threads
    for p in philosophers: p.start()
