javac *.java && java Main 2> errors.txt 1> output
echo $(cat output | wc -l) "lines written"
echo $(cat errors.txt | wc -l) "errors were encountered, see errors.txt for more details"

