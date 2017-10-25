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
json_lib=json.load(open('/media/dtm/wikidata/wikidata-20171016-all.json'))
count=0
langs={'zh','zh-hans','zh-hant','zh-hk','zh-mo','zh-my','zh-sg','zh-tw'}

for i in json_lib:
    if i == None:
        continue
    str=''
    for lang in langs:
        str= str + getLangString(lang,i) + DELIMITER
    if str.isspace():#this only applies while delimiter is a tab **change this**
        continue
    str=str+getLangString('en',i)+DELIMITER
    print(str+DELIMITER+getDesc('en',i))
