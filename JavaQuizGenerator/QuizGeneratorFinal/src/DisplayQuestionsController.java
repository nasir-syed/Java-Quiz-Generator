import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;

public class DisplayQuestionsController {


    // for each category, we have an array of questions, a 2D array of options, and a char array for the answers.


    private String answer;

    private int score = 0;

    private Stage stage;

    private Parent root;

    private Scene scene;

    private int numQs;

    private String name;

    private String category;
    private int count;
    private ArrayList randNumQArrayList = new ArrayList();

    private ArrayList randOptionsArrayList = new ArrayList();

    private Timer timer = new Timer();

    private Questions [] historyQuestions ={
            new Questions("When is the National day of UAE?","December 1st", "December 2nd", "December 3rd", "December 4th", "December 2nd"),
            new Questions("When is India's independence day?","August 14th", "August 16th", "August 23rd", "August 15th", "August 15th"),
            new Questions("The period for which there are no written records is called _________","History", "Ancient Times", "Pre-History", "None of the above","Pre-History"),
            new Questions("In which year was Napoleon defeated?","1810", "1920", "1795", "1815","1815"),
            new Questions( "Who was the ruler of France during the revolution?","Louis XVI", "Rosseau", "Louis XV", "George Washington","Louis XVI"),
            new Questions("When did the American Civil War end?","1832", "1865", "1810", "1790","1865"),
            new Questions( "Who were the axis powers in World War II?","Poland, Japan, Germany", "Italy, Japan, Britain", "Germany, Poland, Italy,", "Germany, Italy, Japan","Germany, Italy, Japan"),
            new Questions("Genghis Khan died in the year ____","1227", "1298", "1276", "1235","1227"),
            new Questions("Who was the second President of the USA?","Abraham Lincoln", "Thomas Jefferson", "John Adams", "Thomas Edison","John Adams"),
            new Questions("When was Louis XVI executed?","1782", "1793", "1805", "1815","1793")

    };
    private Questions [] scienceQuestions = {
            new Questions("What element does the periodic symbol 'He' represent?","Helios","Hydrogen","Helium","Hermes", "Helium"),
            new Questions("What is the pH value of water?","8","7","6","5","7"),
            new Questions("Which of the following is used in pencils?","Graphite","Iron","Sodium","Silicon","Graphite"),
            new Questions("Which of the following metals forms an amalgam with other metals?","Mercury","Tin","Lead","Zinc","Mercury"),
            new Questions("Which of the following is a non-metal that is liquid at room temperature?","Hydrogen","Bromine","Helium ","Chlorine","Bromine"),
            new Questions("What is the gas usually found in an electric bulb?","Oxygen","Carbon Dioxide","Helium","Nitrogen","Nitrogen"),
            new Questions("For which compound is 'Washing Soda' a nickname for?","Hydrogen Peroxide ","Sodium Carbonate","Sodium Chloride","Calcium Phosphate","Sodium Carbonate"),
            new Questions("What are the two elements that are frequently used for making transistors?","Iron and Graphite","Copper and Silicon","Silicon and Germanium","Gold and Copper","Silicon and Germanium"),
            new Questions("Which is the lightest metal, from the following:","Lithium","Bromine","Mercury","Copper","Lithium"),
            new Questions("Which metal from among the following needs to be stored in kerosene?","Beryllium","Copper","Iron","Sodium","Sodium")
    };

    private Questions [] musicQuestions = {

            new Questions("How many members are in the group BTS?","5","6","7","8","7"),
            new Questions("Who was the lead singer for the band 'Queen'?","John lennon","Freddie Mercury","Steve Perry","Alex Turner","Freddie Mercury"),
            new Questions("Michael Jackson is deemed as the king of ____","Rap","Metal","R&B","Pop","Pop"),
            new Questions("Who made the hit song 'Blinding Lights'?","Selena Gomez","The Weeknd","Drake","Taylor Swift","The Weeknd"),
            new Questions("Which band, which recently disbanded, contained only 2 members","Daft Punk","Green Day","Linkin Park","System of a Down","Daft Punk"),
            new Questions("How many members are in 'Tame Impala'?","3","2","1","4","1"),
            new Questions("Which album granted Kendrick Lamar a 'Grammy' award?","Forest Hills Drive","Good Kid, M.A.A.D City","Section .80","Untitled Unmastered","Good Kid, M.A.A.D City"),
            new Questions("Who made the hit song 'God's Plan'?","Drake","Kanye West","J Cole","Justin Bieber","Drake"),
            new Questions("Which artist was featured on the hit record 'Baby' by Justin Bieber?","Will.I.Am","Lil Wayne","Ludacris","Bruno Mars","Ludacris"),
            new Questions("'CALL ME IF YOU GET LOST' is an album made by _____","Clairo","Jay Z","Frank Ocean","Tyler, The Creator","Tyler, The Creator")


    };

    private Questions [] sportsQuestions = {

            new Questions("What is the record for most red cards given in a single match of football?","10","17","24","36","36"),
            new Questions("What type of race is 'Tour de France'?","NASCAR","Bicycle","Formula 1","Marathon","Bicycle"),
            new Questions("Which team won the very first NBA game, held in 1946?","New York Knicks","Boston Celtics","Chicago Bulls","Atlanta Hawks","New York Knicks"),
            new Questions("Who is the youngest ever world heavyweight boxing champion?","Muhammad Ali", "Mike Tyson", "Evander Holyfield", "Cus D'Amato","Mike Tyson"),
            new Questions("The youngest player in the NBA to score 10,000 points was _________","Kobe Bryant","Shaquille O'Neal","LeBron James","Steph Curry","LeBron James"),
            new Questions("When was the first ever 'FIFA World Cup' held?","1930","1934","1940","1942","1930"),
            new Questions("Who won the 'FIFA World Cup' that took place in 2018?","Argentina","Germany","Spain","France","France"),
            new Questions("The FIFA World Cup is held every ____ years.","2","3","4","5","4"),
            new Questions( "What is the national sport of England","Cricket","Rugby","Football","Hockey","Cricket"),
            new Questions("______ has appeared on the most sports illustrated covers.","Cristiano Ronaldo","Lionel Messi","Michael Jordan","Lebron James","Michael Jordan")


    };

    private Questions [] mathsQuestions = {

            new Questions("The line that touches a circle at only one point is called _______","Chord","Tangent","Sector","Arc","Tangent"),
            new Questions("An angle whose value lies between _______ is called an 'acute' angle","0-90","90-180","180-270","270-360","0-90"),
            new Questions("What letter represents an imaginary number","l","a","i","e","i"),
            new Questions("What is the formula to calculate simple interest?","P/T","(T/P)*R","P*R","(P*R*T)/100","(P*R*T)/100"),
            new Questions("What is the only number to not have a Roman numeral","0","1000","10,000","100,000","0"),
            new Questions("What is name of this sequence of numbers: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 . . .","Verstappen","Fibonacci","Marshall","De Morgan","Fibonacci"),
            new Questions("What is the value of 'e'","2.817","2.187","2.718","2.178","2.718"),
            new Questions("Which one of these is NOT a branch of mathematics","Topology","Calculus","Trigonometry","None of the above","None of the above"),
            new Questions("What is a line joining two endpoints called?","Chord","Line Segment","Intersecting Lines","Perpendicular Lines","Line Segment"),
            new Questions("What is the shape of the bottom of a pyramid?","Triangle","Parallelogram","Rectangle","Any Polygon","Any Polygon")


    };




    @FXML
    private Button optionButtonA, optionButtonB, optionButtonC, optionButtonD;

    @FXML
    private AnchorPane questionsPane;


    @FXML
    private Label noteLabel, questionLabel, optionLabelA, optionLabelB, optionLabelC, optionLabelD, questionNoLabel,timerLabel;

    public void setVariables(String category, String name, int numQs){

        // variables are set
        this.category=category;
        this.name=name;
        this.numQs=numQs;
    }
    public void randomiseQuestions() {

        // the questions are shuffled, displayed in a different order than declared above.

        for (int i = 0; i < 10; i++) {

            randNumQArrayList.add(i);

        }

        Collections.shuffle(randNumQArrayList);




    }

    public void randomiseOptions(int count,Questions [] categoryQuestions ){

        // options are randomised and displayed for each question

        randOptionsArrayList.add(categoryQuestions[(int) randNumQArrayList.get(count)].optionA);
        randOptionsArrayList.add(categoryQuestions[(int) randNumQArrayList.get(count)].optionB);
        randOptionsArrayList.add(categoryQuestions[(int) randNumQArrayList.get(count)].optionC);
        randOptionsArrayList.add(categoryQuestions[(int) randNumQArrayList.get(count)].optionD);

        //shuffles the options
        Collections.shuffle(randOptionsArrayList);

    }



    public void assigningCategory(Questions [] categoryQuestions){

        // a note label to tell the user how to choose an option, disappears after first question
        if (count == 0){
            noteLabel.setText("[click on 'A','B','C' or 'D' to choose an option]");
        }
        else{
            noteLabel.setText("");
        }

        // we call the method and randomise the options of each question from the category chosen
        randomiseOptions(count,categoryQuestions);

        // we set the labels with the question and its options
        questionLabel.setText(categoryQuestions[(int) randNumQArrayList.get(count)].question);
        optionLabelA.setText((String)randOptionsArrayList.get(0));
        optionLabelB.setText((String)randOptionsArrayList.get(1));
        optionLabelC.setText((String)randOptionsArrayList.get(2));
        optionLabelD.setText((String)randOptionsArrayList.get(3));

        // the buttons will not fire when spacebar or enter is pressed
        optionButtonA.setFocusTraversable(false);
        optionButtonB.setFocusTraversable(false);
        optionButtonC.setFocusTraversable(false);
        optionButtonD.setFocusTraversable(false);

        // removes the elements of the arraylist to prevent the options of other questions
        // being displayed rather than the options of the current question
        randOptionsArrayList.removeAll(randOptionsArrayList);

    }


    public void questions(){
        // since count starts with 0, we add 1 to correctly display the number of questions
        // "Question 1" not "Question 0"
        questionNoLabel.setText("Question " + (count + 1)+" of "+numQs);



        //if the count variable is less than the number of questions chosen by the user, display the questions
        if (count<numQs) {

            // according to the category chosen, the questions will be displayed
            switch(category) {
                case "history":
                    assigningCategory(historyQuestions);
                    break;

                case "science":
                    assigningCategory(scienceQuestions);
                    break;

                case "music":
                    assigningCategory(musicQuestions);
                    break;

                case "maths":
                    assigningCategory(mathsQuestions);
                    break;

                case "sports":
                    assigningCategory(sportsQuestions);
                    break;
            }

        }
    }

    public void answerSelected (ActionEvent e) throws IOException{


        // according to the button pressed, the answer variable is assigned a value
        // Eg. if buttonA is pressed then the answer variable is assigned 'A'

        if (e.getSource()==optionButtonA){
            answer = optionLabelA.getText();
        }

        if (e.getSource()==optionButtonB){
            answer = optionLabelB.getText();
        }

        if (e.getSource()==optionButtonC){
            answer = optionLabelC.getText();
        }

        if (e.getSource()==optionButtonD){
            answer = optionLabelD.getText();
        }

        // the check method checks if the answer is correct and increments the score if so
        checkAnswer();
        //the count variable is incremented to show that this question is over, and to display the next
        count++;
        // if the question was the last question, then the ShowResults method will be executed
        showResults(e);
        // after incrementing the count variable the "questions" method is called again to display the next question
        questions();


    }

    public void checkAnswer() {

        // we check if the user's answer matches with the correct answer
        // if it does then score is incremented by 1
        switch (category) {
            case "history":
                if (answer.equalsIgnoreCase(historyQuestions[(int) randNumQArrayList.get(count)].correctAns)) {
                    score++;
                }
                break;

            case "science":
                if (answer.equalsIgnoreCase(scienceQuestions[(int) randNumQArrayList.get(count)].correctAns)) {
                    score++;
                }
                break;

            case "music":
                if (answer.equalsIgnoreCase(musicQuestions[(int) randNumQArrayList.get(count)].correctAns)) {
                    score++;
                }
                break;

            case "maths":
                if (answer.equalsIgnoreCase(mathsQuestions[(int) randNumQArrayList.get(count)].correctAns)) {
                    score++;
                }
                break;

            case "sports":
                if (answer.equalsIgnoreCase(sportsQuestions[(int) randNumQArrayList.get(count)].correctAns)) {
                    score++;
                }
                break;
        }
    }



    public void storeScore() throws IOException{

        // the final score is then stored in the file "Scores.txt" along with the date (in the format yyyy-MM-dd), name and category chosen

        //the date is stored in the format "yyyy-MM-dd"
        Date date = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        //we write the score to the file
        FileWriter fw = new FileWriter("src\\Scores.txt", true );
        PrintWriter outputFile = new PrintWriter(fw);
        outputFile.println("("+simpleDateFormat.format(date)+"):\t"+"NAME:\t"+name+"\tCATEGORY:\t"+category+"\tSCORE:\t"+score+"/"+numQs);
        outputFile.close();
        fw.close();


    }

    public void showResults (ActionEvent event) throws IOException {


        // after the last question is displayed, store the score and show the results by loading the "Results" FXML file.
        if (count == numQs) {

            // the timer is canceled and purged, in order to prevent the storeScore method to be called twice
            // to prevent storing the same user's info two times
            timer.cancel();
            timer.purge();

            //storeScore method is called to store the score
            storeScore();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Results.fxml"));
            root = loader.load();

            ResultsController Results = loader.getController();
            Results.getResult(score,numQs);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }


    }


    public void timer(){

        // a timertask is created for the timer to perform
        TimerTask timerTask = new TimerTask() {
            int counter = numQs*10;
            // number of questions into five will give us the total time limit for the quiz


            @Override
            public void run() {
                // Run the specified Runnable on the JavaFX Application Thread at some unspecified time in the future.
                // allows us to update GUI components
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (counter == 0){
                            try {
                                // cancels and purges the timer object.
                                timer.cancel();
                                timer.purge();

                                //score is stored
                                storeScore();

                                //the "Results.fxml" file is loaded
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Results.fxml"));
                                root = loader.load();

                                ResultsController Results = loader.getController();
                                Results.getResult(score,numQs);


                                stage = (Stage) questionsPane.getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }
                        // countdown
                        counter--;
                        timerLabel.setText(String.valueOf(counter));
                    }
                });
            }

        };

        // after each second the task is executed
        timer.schedule(timerTask, 0, 1000);

    }
}