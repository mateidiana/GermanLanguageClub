# German Language Club
## Overview
This project is a Language Learning App designed to enable accessible learning of the German language.
It allows students to practice their reading, grammar and vocabulary skills with various types of exercises,
while allowing teachers to create this exercises, create exams and view the students' activity.

## Main Features
### For Students
- Create an account
- Enroll in one or more courses (Reading, Grammar, Vocabulary)
- Practice exercises
- Practice Past Mistakes
- Take exams for their respective courses
- View past exam scores

### For Teachers
- Create an account
- Create/update/delete courses
- Create/update/delete exams
- View the enrolled students
- View the results on exams, sorted/filtered by diverse criteria

## Complex methods
### Practice Sessions for Students
- Students can access a course's exercises only if they are enrolled in that course
- Each answer they give is handled and returns a feedback
- Wrong answers are included into their past mistakes management system (in form of (studentId,questionId))

### Exams For Students
- A student can take an exam only if they are enrolled in any reading/grammar/vocabulary course
- The students get a score after answering each question of the exam
- The result is stored into the exam results management system
- Students can view their past exam scores

### Filter And Sort Functions
- A student can view the mandatory books of a reading course sorted in alphabetical order
- A teacher can view the students who passed their reading exam/who passed their vocabulary exam with the best grade
- A teacher can view the students sorted by their grammar exam grade

## Usage
- Run the app
- Choose how the data will be stored (in memory/in file/in a database)
- Choose your role (Student or Teacher)
- Login or sign up
- Explore the functionalities! As a student, you can enroll in any course, practice and take exams.
As a teacher, you can create exams or courses and view your student's activity.

## Technologies Used 
- Java for implementation
- Storage of data in .dat files
- MySQL database for data storage
- Object Oriented Programming

## Future Developments/Features
- Integration of a user interface
- Adding a social component: users can add friends and view each other's progress
- Increased difficulty of exercises for users who have been active for longer


