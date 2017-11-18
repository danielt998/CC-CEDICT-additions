import sys
import string

orig_directory=sys.argv[1]

excludeList = None
with open('excludeList') as f:
    excludeList = f.readlines()
#for s in excludeList:
#    print("EXCLUDED:"+s)
excludeList = [string.split(element,'#')[0] for element in excludeList]
#all_types = []

the_list= open(orig_directory)
for line in the_list:
    types = string.split(line, '\t')
    types_list = string.split(types[11], ',')
    exclude = False
    for a_type in types_list:
        #---------
        #if a_type.strip() not in all_types:
            #all_types.append(a_type.strip)
        #----------
        for exclude_item in excludeList:
            #exclude_item = string.split(exclude_item,'#')[0]
            if a_type.strip() == exclude_item.strip():
                exclude = True
    if not exclude:
        print(line)
    


#for tp in all_types:
#    print(tp)
