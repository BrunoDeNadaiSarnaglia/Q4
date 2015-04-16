import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Bruno on 4/15/2015.
 */
public class Main {

    private static ArrayList<Sample> trainingSet = new ArrayList<Sample>();
    private static HashSet<String> workclassList = new HashSet<String>();
    private static HashSet<String> educationList = new HashSet<String>();
    private static HashSet<String> maritalStatusList = new HashSet<String>();
    private static HashSet<String> occupationList = new HashSet<String>();
    private static HashSet<String> relationshipList = new HashSet<String>();
    private static HashSet<String> raceList = new HashSet<String>();
    private static HashSet<String> sexList = new HashSet<String>();
    private static HashSet<String> nativeCountryList = new HashSet<String>();
    private static HashSet<String> classLabelList = new HashSet<String>();


    public static void readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("train_data.txt"));
        String line = null;
        while((line = br.readLine()) != null){
            String[] features = line.split(",");
            Sample sample = new Sample(features[0], features[1], features[2],
                    features[3], features[4], features[5], features[6], features[7], features[8]);
            trainingSet.add(sample);
            workclassList.add(features[0]);
            educationList.add(features[1]);
            maritalStatusList.add(features[2]);
            occupationList.add(features[3]);
            relationshipList.add(features[4]);
            raceList.add(features[5]);
            sexList.add(features[6]);
            nativeCountryList.add(features[7]);
            classLabelList.add(features[8]);
        }
        for(String str : workclassList){
            System.out.println(str);
        }
    }

    public static void main(String[] args) throws IOException {
        readFile();
    }

}
