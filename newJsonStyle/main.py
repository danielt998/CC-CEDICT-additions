import json

def getDesc(lang_code, dict):
    if i == None:
        return ''
    descs = i.get('descriptions')
    if descs == None:
        return ''
    lang=descs.get(lang_code)
    if lang == None:
        return ''
    value=lang.get('value')
    if value == None:
        return ''
    return value

def getLangString(lang_code, dict):
    if i == None:
        return ''
    labels = i.get('labels')
    if labels == None:
        return ''
    lang=labels.get(lang_code)
    if lang == None:
        return ''
    value=lang.get('value')
    if value == None:
        return ''
    return value

DELIMITER='\t'
#FILE='/media/dtm/wikidata/wikidata-20171016-all.json'
FILE='/media/dtm/wikidata/first1000lines.json'
count=0
langs={'zh','zh-hans','zh-hant','zh-hk','zh-mo','zh-my','zh-sg','zh-tw'}

#for i in json_lib:
with open(FILE) as infile:
    count=count+1
    for line in infile:
        if line.startswith("[") or line.startswith("]"):
            continue
        i=''
        line=line.rstrip()
        if line.endswith(","):
            i=json.loads(line[:-1])
        else:
            i=json.loads(line)#TODO:test this works on the last line...
        if i == None:
            continue
        str=''
        for lang in langs:
            str= str + getLangString(lang,i) + DELIMITER
        if str.isspace():#this only applies while delimiter is a tab **change this**
            continue
        str=str+getLangString('en',i)+DELIMITER
        print(str+DELIMITER+getDesc('en',i))
