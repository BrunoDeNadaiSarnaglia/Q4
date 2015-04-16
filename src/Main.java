import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Bruno on 4/15/2015.
 */
public class Main {

    private static ArrayList<Sample> trainingSet = new ArrayList<Sample>();
    private static ArrayList<Sample> testingSet = new ArrayList<Sample>();
    private static HashSet<String> workclassList = new HashSet<String>(Arrays.asList("Private", "Self-emp-not-inc", "Self-emp-inc", "Federal-gov", "Local-gov",
            "State-gov", "Without-pay", "Never-worked"));
    private static HashSet<String> educationList = new HashSet<String>(Arrays.asList("Bachelors", "Some-college", "11th", "HS-grad", "Prof-school", "Assoc-acdm",
            "Assoc-voc", "9th", "7th-8th", "12th", "Masters", "1st-4th", "10th", "Doctorate", "5th-6th",
            "Preschool"));
    private static HashSet<String> maritalStatusList = new HashSet<String>(Arrays.asList("Married-civ-spouse", "Divorced", "Never-married", "Separated", "Widowed",
            "Married-spouse-absent", "Married-AF-spouse"));
    private static HashSet<String> occupationList = new HashSet<String>(Arrays.asList("Tech-support", "Craft-repair", "Other-service", "Sales", "Exec-managerial",
            "Prof-specialty", "Handlers-cleaners", "Machine-op-inspct", "Adm-clerical", "Farming-fishing"
            , "Transport-moving", "Priv-house-serv", "Protective-serv", "Armed-Forces"));
    private static HashSet<String> relationshipList = new HashSet<String>(Arrays.asList("Wife", "Own-child", "Husband", "Not-in-family", "Other-relative", "Unmarried"));
    private static HashSet<String> raceList = new HashSet<String>(Arrays.asList("White", "Asian-Pac-Islander", "Amer-Indian-Eskimo", "Other", "Black"));
    private static HashSet<String> sexList = new HashSet<String>(Arrays.asList("Female", "Male"));
    private static HashSet<String> nativeCountryList = new HashSet<String>(Arrays.asList("United-States", "Cambodia", "England", "Puerto-Rico", "Canada",
            "Germany", "Outlying-US(Guam-USVI-etc)", "India", "Japan", "Greece", "South", "China",
            "Cuba", "Iran", "Honduras", "Philippines", "Italy", "Poland", "Jamaica", "Vietnam", "Mexico",
            "Portugal", "Ireland", "France", "Dominican-Republic", "Laos", "Ecuador", "Taiwan", "Haiti",
            "Columbia", "Hungary", "Guatemala", "Nicaragua", "Scotland", "Thailand", "Yugoslavia", "El-Salvador",
            "Trinadad&Tobago", "Peru", "Hong", "Holand-Netherlands"));
    private static HashSet<String> classLabelList = new HashSet<String>(Arrays.asList(">50K", "<=50K"));
    private static List<HashSet<String>> featuresList = Arrays.asList(workclassList, educationList, maritalStatusList,
            occupationList, relationshipList, raceList, sexList, nativeCountryList);
    private static HashMap<String, List<HashMap<String, Integer>>> dataStructure = new HashMap<String, List<HashMap<String, Integer>>>();

    private static HashMap<String, Double> maximumLikehood = new HashMap<String, Double>();

    public static void initializeDataStructure(){
        for(String str : classLabelList){
            ArrayList<HashMap<String, Integer>> arrayList = new ArrayList<HashMap<String, Integer>>();
            dataStructure.put(str, arrayList);
            for (HashSet<String> hashSet : featuresList){
                HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
                for (String str2 : hashSet){
                    hashMap.put(str2, 0);
                }
                arrayList.add(hashMap);
            }
        }
    }

    public static void addSampleToDataStructure(Sample sample){
        List<HashMap<String, Integer>> arrayList = dataStructure.get(sample.getClassLabel());
        HashMap<String, Integer> hashMap = arrayList.get(0);
        Integer count = hashMap.get(sample.getWorkclass());
        hashMap.put(sample.getWorkclass(), count + 1);

        hashMap = arrayList.get(1);
        count = hashMap.get(sample.getEducatino());
        hashMap.put(sample.getEducatino(), count + 1);

        hashMap = arrayList.get(2);
        count = hashMap.get(sample.getMaritalStatus());
        hashMap.put(sample.getMaritalStatus(), count + 1);

        hashMap = arrayList.get(3);
        count = hashMap.get(sample.getOccupation());
        hashMap.put(sample.getOccupation(), count + 1);

        hashMap = arrayList.get(4);
        count = hashMap.get(sample.getRelationship());
        hashMap.put(sample.getRelationship(), count + 1);

        hashMap = arrayList.get(5);
        count = hashMap.get(sample.getRace());
        hashMap.put(sample.getRace(), count + 1);

        hashMap = arrayList.get(6);
        count = hashMap.get(sample.getSex());
        hashMap.put(sample.getSex(), count + 1);

        hashMap = arrayList.get(7);
        count = hashMap.get(sample.getNativeCountry());
        hashMap.put(sample.getNativeCountry(), count + 1);


    }

    public static void readFile() throws IOException {
        initializeDataStructure();
        BufferedReader br = new BufferedReader(new FileReader("train_data.txt"));
        String line = null;
        while((line = br.readLine()) != null){
            String[] features = line.split(",");
            Sample sample = new Sample(features[0], features[1], features[2],
                    features[3], features[4], features[5], features[6], features[7], features[8]);
            trainingSet.add(sample);
            addSampleToDataStructure(sample);
        }
        fillMaximumLikehood();
    }

    public static void fillMaximumLikehood(){
        for(String str : classLabelList){
            maximumLikehood.put(str, .0);
        }
//        int sum = 0;
        for(Sample sample : trainingSet){
            String classLabel = sample.getClassLabel();
            maximumLikehood.put(classLabel, maximumLikehood.get(classLabel) + 1);
//            sum++;
        }
//        for(String str : classLabelList){
//            maximumLikehood.put(str, maximumLikehood.get(str)/sum);
//        }
    }

    public static Double getP(String classLabel){
        double sum = 0;
        for(String str: maximumLikehood.keySet()){
            sum += maximumLikehood.get(str);
        }
        return maximumLikehood.get(classLabel)/sum;
    }

    public static Double getProbability(int feature, String featureValue, String classLabel){
        Integer n1 = dataStructure.get(classLabel).get(feature).get(featureValue);
        Double n2 = maximumLikehood.get(classLabel);
        Integer n3 = dataStructure.get(classLabel).get(feature).size();
//        System.out.println(n1);
//        System.out.println(n2);
//        System.out.println(n3);
//        System.out.println((n1+1)/(n2+n3));
        return (n1+1)/(n2+n3);
    }

    public static void readTestFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("test_data.txt"));
        String line = null;
        int sum = 0;
        int acc = 0;
        while((line = br.readLine()) != null){
            String[] features = line.split(",");
            Sample sample = new Sample(features[0], features[1], features[2],
                    features[3], features[4], features[5], features[6], features[7], features[8]);
            testingSet.add(sample);
            double prod1 = getP("<=50K");
            for (int i = 0; i < 7; i++) {
                prod1 *= getProbability(i, features[i], "<=50K");
            }
            double prod2 = getP(">50K");
            for (int i = 0; i < 7; i++) {
                prod2 *= getProbability(i, features[i], ">50K");
            }
            String str;
            if(prod1 < prod2)
                str = ">50K";
            else
                str = "<=50K";
            if(str.equals(features[8]))
                acc++;
            sum++;
        }
        System.out.println(((double)acc)/sum);
    }

    public static void main(String[] args) throws IOException {
        readFile();

        System.out.println(getP("<=50K"));
        System.out.println(getProbability(1, "Bachelors", ">50K"));

        readTestFile();
    }

}
