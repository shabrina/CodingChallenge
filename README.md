# CodingChallenge
How to test the code:
I already put a file called input.txt for sample testing.

############################################################################
Assumptions:
Metal consists only of these tree: Silver, Gold, Iron
Other keyword to be mapped can be glob, prok, pish, tegj, and other words
For mapping the value, there is no other word after Metal words. It will print "There should not be any other words after Silver, Gold, Iron".
For example, input : glob glob Silver is 34 Credits is acceptable, but glob glob Silver glob is 34 Credits is not acceptable.
For mapping the value, if the word is not exist on the map, it will throw 
"Error mapping the value, value the value does not exist" , with the value is the value that does not exist.

############################################################################
List of functions:
translate(String s) ⇒ process input to get the result
containsMetal(List<String> inputList) ⇒ check whether the input line contains word Silver, Gold, or Iron
calculateResult(List<String> inputList) ⇒ Calculate each line to get the result
resultFormatting(String metalName, String finalResult, int resultRoman) ⇒ Formatting the result
mappingMetalValue(List<String> inputList) ⇒ calculate and mapping metal value (silver, gold, iron)
getIsPosition(List<String> inputList) ⇒ Get the position of "is"
romanToArabicConverter(String s) ⇒ Convert roman to arabic
