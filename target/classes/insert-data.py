# -*- coding: utf-8 -*-
"""
Created on Wed Jul 10 08:50:08 2019

@author: akasza
"""
import requests
import csv

headers = {
    'Content-Type': 'application/x-www-form-urlencode',
}

file = open('mock.csv', newline='\n')
counter = 0
for row in csv.reader(file, delimiter=',', quotechar='|'):
    if(counter>0):
        data = '{firstName=%s&lastName=%s&university=%s&faculty=%s&degree=%s&answer0=%s&answer1=%s&answer2=%s}' % tuple(row)
        print(requests.post('http://localhost:8080/lbd/surveys', headers=headers, data=data))
