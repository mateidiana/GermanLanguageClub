package org.example.service;

import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.example.model.*;
import org.example.repo.WritingRepository;
import org.example.repo.WritingRepository;
import org.example.repo.StudentRepository;
public class WritingService {
    private WritingRepository writingRepo;

    private StudentRepository studentRepo;

    public WritingService(WritingRepository writingRepo, StudentRepository studentRepo) {
        this.writingRepo = writingRepo;
        this.studentRepo = studentRepo;
    }

    public static void main(String[] args) {
        System.out.println(chatGPT("hello, how are you?"));
        // Prints out a response to the question.
    }

    public static String chatGPT(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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"; // API key goes here
        String model = "gpt-3.5-turbo"; // current model of chatgpt api

        try {
            // Create the HTTP POST request
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            // Build the request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            // Get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // returns the extracted contents of the response.
            return extractContentFromResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // This method extracts the response expected from chatgpt and returns it.
    public static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content")+11; // Marker for where the content starts.
        int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
        return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
    }


    public void enroll(Integer studentId, Integer writingCourseId) {
        Student student = studentRepo.getById(studentId);
        Writing course = writingRepo.getById(writingCourseId);
        studentRepo.delete(student);
        writingRepo.delete(course);
        if (course.getAvailableSlots() > course.getEnrolledStudents().size()) {
            course.getEnrolledStudents().add(student);
            student.getCourses().add(course);
            writingRepo.save(course);
            studentRepo.save(student);
        }
    }
    public void practiceWriting(Integer studentId, Integer courseId) {
        Student student = studentRepo.getById(studentId);
        Writing course = writingRepo.getById(courseId);

        Scanner scanner = new Scanner(System.in);

        //aici e cu string matching merge matricea vietii si fac vf la atribute

        int foundCourse=0;
        if (foundCourse==0)
            System.out.println("\n\n\nYou are not enrolled in this course!");
        else{

        }


    }
    public List<Student> getAllStudents() {
        return studentRepo.getObjects();
    }
    public List<Writing> getAvailableCourses() {
        return writingRepo.getObjects();
    }

    public List<Student> getEnrolledStudents(Integer courseId) {
        Writing course = writingRepo.getById(courseId);
        return course.getEnrolledStudents();
    }

    public void removeCourse(Integer courseId) {
        //this doesnt do jackshit yet
    }
}
