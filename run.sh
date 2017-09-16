javac *.java && java Main > output
echo $(cat output | wc -l) "lines written"
