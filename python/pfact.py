#!/usr/bin/env python3
from math import sqrt
import datetime


def factorize():
    global number
    numbers_list = []
    factors_list = []
    numbers_list.append(number)

    factorizing = True
    while factorizing:
        for i in range(int(sqrt(number)) + 1):
            if not i == 0 and not i == 1:
                if i == number:
                    factors_list.append(i)
                    numbers_list.append(1)
                    factorizing = False
                    break
                if number % i == 0:
                    number //= i
                    numbers_list.append(number)
                    factors_list.append(i)
                    break
        else:
            factors_list.append(number)
            numbers_list.append(1)
            factorizing = False

    return factors_list, numbers_list


def get_answer(factors_list, numbers_list):
    answer = '\n'
    i = 0
    for f in factors_list:
        answer += f'    {numbers_list[i]}   |   {f}\n'
        i += 1
    answer += f'    {numbers_list[i]}   |\n\n   '

    already_done_fs = []
    for f in factors_list:
        if f not in already_done_fs:
            number_of_fs = factors_list.count(f)
            if number_of_fs == 1:
                answer += f'{f} * '
            else:
                answer += f'{f}^({number_of_fs}) * '

            already_done_fs.append(f)

    answer = answer[:len(answer) - 2]
    return answer




run = True

while run:
    command = input('Enter your number: ').strip()
    if command == 'exit':
        exit(0)
    try:
        global number
        number = int(command)
    except TypeError:
        print(f'Number not valid: {command}')

    start = datetime.datetime.now()
    l = [factors_list, numbers_list] = factorize()
    end = datetime.datetime.now()

    answer = get_answer(l[0], l[1])
    print(answer)
    print(f'It took {end - start}\n')

