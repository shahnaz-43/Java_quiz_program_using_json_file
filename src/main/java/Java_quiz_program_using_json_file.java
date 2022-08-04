import java.io.FileReader;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Java_quiz_program_using_json_file {
    public static void quesFile() {
        Scanner input = new Scanner(System.in);
        JSONArray quizList = new JSONArray();

        while (true) {
            JSONObject quesDetails = new JSONObject();

            String question = "Question";
            String option1 = "Option-a:";
            String option2 = "Option-b:";
            String option3 = "Option-c:";
            String option4 = "Option-d:";
            String answer = "Answer: ";

            System.out.println("Please add a Question here:");
            String ques = input.nextLine();

            System.out.println("Please input option a:");
            String in1 = input.nextLine();
            System.out.println("Please input option b:");
            String in2 = input.nextLine();
            System.out.println("Please input option c:");
            String in3 = input.nextLine();
            System.out.println("Please input option d:");
            String in4 = input.nextLine();
            System.out.println("Please input answer:");
            String in5 = input.nextLine();

            quesDetails.put(question, ques);
            quesDetails.put(option1, in1);
            quesDetails.put(option2, in2);
            quesDetails.put(option3, in3);
            quesDetails.put(option4, in4);
            quesDetails.put(answer, in5);

            quizList.add(quesDetails);

            try {
                FileWriter file = new FileWriter("C:/Users/samawat/IdeaProjects/Quiz/Quiz_test/src/main/resources/addQuiz.json");
                file.write(quizList.toJSONString());
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Question saved. Do you want to add more questions? y/n");
            String choice = input.nextLine();
            if (choice.equals("y")) {
                continue;
            } else {
                break;
            }
        }
    }

    public static void readQuizList() throws IOException, ParseException {
        Scanner input = new Scanner(System.in);
        int count = 0;
        //int pos=0;
        String fileName = "C:/Users/samawat/IdeaProjects/Quiz/Quiz_test/src/main/resources/addQuiz.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray quizArray = (JSONArray) obj;

        for (int i = 1; i <= 5; i++) {
            int randNum = (int) (Math.random() * 20) + 1;

            JSONObject quizObject = (JSONObject) quizArray.get(randNum);
            String que = (String) quizObject.get("Question");
            String opt_a = (String) quizObject.get("Option-a:");
            String otp_b = (String) quizObject.get("Option-b:");
            String opt_c = (String) quizObject.get("Option-c:");
            String opt_d = (String) quizObject.get("Option-d:");
            String CorrectAnswer = (String) quizObject.get("Answer: ");
            System.out.println(i + "." + que);
            System.out.println("a." + opt_a);
            System.out.println("b." + otp_b);
            System.out.println("c." + opt_c);
            System.out.println("d." + opt_d);

            System.out.println("Enter your answer:");
            String answer = input.nextLine();

            if (answer.equals(CorrectAnswer)) {
                System.out.println("Well Done! Correct!");
                count++;
            } else {
                System.out.println("Sorry..Not correct");
            }
            System.out.println("You got " + count + " out of 5");
            System.out.println();
        }

    }


    public static void main(String[] args) throws IOException, ParseException {

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the quiz program");
        System.out.println("Choose 1. Add quiz");
        System.out.println("Choose 2. Start quiz");

        int choice = input.nextInt();
        if (choice == 1) {
            quesFile();
        } else if (choice == 2) {
            System.out.println("You will be asked 5 questions,each questions has 1 marks");
            readQuizList();
        } else {
            System.out.println("Enter your choice");
        }
    }
}
