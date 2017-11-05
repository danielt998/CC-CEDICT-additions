# CC-CEDICT-additions
A  collection of tools/scripts aimed to create some additions for CC-CEDICT automatically, for things such asplace names
This will be done by querying Wikidata, and possibly by other methods too
Note that as these are automatically generated, they are not 100% reliable, for example, many characters have multiple pinyin readings, also, some words/names do not necessarily use equivalent characters in the simplified and traditional words. For this reason, they cannot be included in CC-CEDICT.


new style:
either:
1) set up a Wikidata client to do things more programatically
OR
2)Download a JSON dump of Wikidata and parse this programatically

pseudocode:
foreach item
  if no Chinese label:
    ignore
  if no English label:
    ??comtemplate what to do here, maybe pull from other languages with a priority order, many
    e.g. Spanish, will probably be fine for e.g. place names/ people names, and better than no entry


  look at list of supported items, if exists in this list, then create the item,
    e.g. London, a city in the United Kingdom
      must also consider how to handle multiple types (some are subclasses of others, only want to
      include once)
  if not an instance of an item in this list:
    then just translate directly, e.g. 伦敦 - London
  add the English description as an additional definition (or maybe if one exists,
              use this instead of the existing one, and maybe also add the Chinese description)
    or maybe just get the name of the "subset of" item

also to consider:
  check for multiple Chineses, e.g. trad, simp, (maybe others, e.g. HK) and use these by default, if only one of simplified or traditional exists, then predict what the other should be


ideas for other translation sources:
  there must be some lists of place name translations, e.g. where does Facebook get these from
  extract from wikipedia? There may be some that have translations here but not in Wikidata, some
    html parsing/regex would be required here and could get ugly
  

also, would be nice to do some analytics, i.e. find the total number of instances of each type and sort these by frequency
