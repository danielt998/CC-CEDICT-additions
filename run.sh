javac *.java && java Main 2> errors 1> output
echo $(cat output | wc -l) "lines written"
echo $(cat errors | wc -l) "errors were encountered"

