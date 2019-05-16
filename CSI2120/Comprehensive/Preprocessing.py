# Aethelind Racic
# 7686783
# CSI2120, Assignment 4

import json

# open json file ..
with open('wading-pools.json', encoding='utf-8') as ifile:
    poolFile = json.loads(ifile.read())

# add pools and coordinates to a list which will be used to create the appropriate output files ..
# the JSON file lists the coordinates as [longitude, latitude]
# convention is [latitude, longitude] elsewhere, so it will be recorded in that order
poolList = []
for p in poolFile['features']:
    tmp={ 'name':p['properties']['NAME'].replace('Wading Pool - ', ''),
          'ln': p['geometry']['coordinates'][0],
          'lt': p['geometry']['coordinates'][1] }
    poolList.append(tmp)

# output for java ..
with open('wading-pools-java.txt', 'w') as ofile:
    for p in poolList:
        ofile.write(p['name']+','),
        ofile.write(str(p['lt'])+','),
        ofile.write(str(p['ln'])+'\n')

# output for prolog ..
# writes the pool info 'on top of' what is already in the file,
# ie. prepends pools to my prolog implementation
with open('findroute.pl', 'r+') as ofile:
    old = ofile.read()
    ofile.seek(0)
    for p in poolList:
        ofile.write('pool("'),
        ofile.write(p['name'] + '",'),
        ofile.write(str(p['lt']) + ','),
        ofile.write(str(p['ln'])),
        ofile.write(').\n')

    ofile.write('\n\n' + old)

# output for scheme
with open('wading-pools-scheme.txt', 'w') as ofile:
    for p in poolList:
        ofile.write('("' + p['name']+'" '),
        ofile.write(str(p['lt'])+' '),
        ofile.write(str(p['ln'])+')\n')
#output for go
with open('wading-pools-go.txt', 'w') as ofile:
    for p in poolList:
        ofile.write(p['name']+','),
        ofile.write(str(p['lt'])+','),
        ofile.write(str(p['ln'])+'\n')










