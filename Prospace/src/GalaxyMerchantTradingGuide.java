import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GalaxyMerchantTradingGuide {

	private static HashMap<String, String> inputMap = new HashMap<String, String>();
	private static HashMap<String, Double> metalMap = new HashMap<String, Double>(); //to map value of silver, gold, iron
	private static boolean isValid = true;
	public static void main(String[] args) {
		try {
			File file = new File("input.txt");
			Scanner scanner = new Scanner(file);
			if(!scanner.hasNextLine()) {
				System.out.println("File is empty");
			}
			while(scanner.hasNextLine() && isValid) {
				String input = scanner.nextLine();
				translate(input);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
	}
	
	/* processing the input to get the result */
	public static void translate(String s) {
		String[] inputArr = s.split(" ");
		List<String> inputList = Arrays.asList(inputArr);
		if(!s.contains("?")) {  // if it is not question, do the value mapping
			if(!containsMetal(inputList)) {
				inputMap.put(inputList.get(0), inputList.get(2)); // if the line doesn't contain metal, put directly to the map
			} else {
				mappingMetalValue(inputList); //mapping value containing silver, gold, iron
			}
		} else if (s.contains("?") && isValid) { // check if it is a question and if the values are already mapped/valid
			String result = calculateResult(inputList);
			System.out.println(result);
		}
	}
	
	/* check whether the input line contains word Silver, Gold, or Iron*/
	public static boolean containsMetal(List<String> inputList) {
		if(inputList.indexOf("Silver") > -1 ||
				inputList.indexOf("Gold") > -1 || inputList.indexOf("Iron") > -1) {
			return true;
		} 
		return false;
	}
	
	/* Calculate each line to get the result */
	public static String calculateResult(List<String> inputList) {
		String finalResult = "";
		int isPosition = getIsPosition(inputList); 
		int questionMarkPosition = inputList.indexOf("?");
		String metalName = "";
		int metalPosition = questionMarkPosition;
		
		//get position of the metal
		if(containsMetal(inputList)) {
			metalName = inputList.get(questionMarkPosition - 1);
			metalPosition = questionMarkPosition - 1;
		}
		
		String tmpRoman = "";
		for(int i = isPosition + 1; i < metalPosition; i++) {
			if(inputMap.containsKey(inputList.get(i))) {
				tmpRoman = tmpRoman + inputMap.get(inputList.get(i));
				finalResult = finalResult + inputList.get(i) + " ";
			} else {
				return "I have no idea what you are talking about";
			}
		}
		
		int resultRoman = romanToArabicConverter(tmpRoman);
		
		return resultFormatting(metalName, finalResult, resultRoman);
	}
	
	/* Formatting the result*/
	public static String resultFormatting(String metalName, String finalResult, int resultRoman) {
		if(!metalName.isEmpty()) {
			double resultCalculation = resultRoman * metalMap.get(metalName);
			return finalResult + metalName + " is " + (int)resultCalculation + " Credits";
		} else {
			return finalResult + "is " + resultRoman;
		}
	}
	/* calculate and mapping metal value (silver, gold, iron) */
	public static void mappingMetalValue(List<String> inputList) {
		String tmpRoman = "";
		int isPosition = getIsPosition(inputList);
		int result = Integer.parseInt(inputList.get(isPosition + 1));
		String metalName = inputList.get(isPosition - 1);
		int metalPosition = isPosition - 1;
		isValid = true;
		for(int i = 0; i < metalPosition; i++) {
			if(inputMap.containsKey(inputList.get(i))) {
				tmpRoman = tmpRoman + inputMap.get(inputList.get(i));
			} else {
				isValid = false;
				System.out.println("Error mapping the value, value " + inputList.get(i) + " is not exist");
				break;
			}	
		}
		if(isValid) {
			int resultRoman = romanToArabicConverter(tmpRoman);
			double metalValue = (double) result/resultRoman;
			metalMap.put(metalName, metalValue);
		}
	}
	
	/* Get the position of "is" */
	public static int getIsPosition(List<String> inputList) {
		int isPosition = inputList.indexOf("is"); 
		return isPosition;	
	}
	
	/* Convert roman to arabic */
	public static int romanToArabicConverter(String s) {
		int result = 0;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
        int i = 0; //pointer string
        while(i < s.length()){
            //MCMXCIV = 1994
            if(i < s.length() - 1 && map.containsKey(s.charAt(i)+""+s.charAt(i+1))){
                int conv = map.get(s.charAt(i)+""+s.charAt(i+1)); 
                result +=  conv;
                i+=2;
            } else {
                int tmp = map.get(s.charAt(i)+"");
                result += tmp; 
                i++;
            }
        }
        return result;
    }
}
