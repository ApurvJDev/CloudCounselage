package com.example.cloudcounselageconnect;

public class BotResponse {
    public static String getBotReply(String query) {

        query = query.toLowerCase().trim();

        if(query.contains("hello")) {
            return "Hello! How can I assist you?";
        }  else if (query.contains("benefits") || query.contains("part of iac")) {
            return "1. Industry Exposure Workshops\n" +
                    "2. Career Vision\n" +
                    "3. Career Guidance\n" +
                    "4. Industry Training\n" +
                    "5. Internship Program\n" +
                    "6. Industry Visits\n" +
                    "7. CV & Interview Preparations\n" +
                    "8. Soft Skills Workshops\n" +
                    "9. Career Assessments\n" +
                    "10. Hackathons\n" +
                    "11. Job Placements\n" +
                    "12. Entrepreneurship Program";
        }else if (query.contains("iac") || query.contains("industry academia community") || query.contains("what is iac")) {
            return "Industry-Academia Community (IAC) is a part of 'Industry-Academia Connect' initiative of Cloud Counselage Pvt. Ltd." +
                    "in association with Gift-A-Career Foundation created for Industry & Academia PAN India. This community engages and " +
                    "supports higher education students and freshers by providing them with 360-degree professional development and career" +
                    " growth opportunities through Career Vision, Career Guidance, Industry & Corporate Exposure, and Hands-on experience/remote" +
                    " internships on live projects. All the benefits of the community can be availed from any corner of the world as it is an " +
                    "online community and at no cost to the members.";
        } else if (query.contains("fees") || query.contains("money")) {
            return "This community is created with the intent to eradicate the perpetual unemployability issue" +
                    " amongst the fresh graduates of India. Cloud Counselage is committed to this social cause and " +
                    "therefore the students can be part of this community at no cost. They do not pay any fees and " +
                    "there are no hidden charges for being part of this community and for availing the benefits of " +
                    "the programs offered through this community.";
        } else if (query.contains("do i need to participate in all the activities") || query.contains("part of activity") || query.contains("activities")) {
            return "You can participate in any activities of your choice. However, each activity has its own benefit. " +
                    "The more activities that you participate in the more career/ job-ready you become.";
        } else if (query.contains("hours of work") || query.contains("amount of work") || query.contains("woring hours")) {
            return "You are expected to spend 1-2 hours a week completing the professional development activities under IAC." +
                    " This will ensure continuous professional development.";
        } else if (query.contains("necessary to become a member") || query.contains("have to become a member")) {
            return "You agree to become a member of the community as soon as you sign up for the app or express interest to join the community. " +
                    "The benefits of the app and in turn, the community are only available to the community members. Moreover, there are no fees" +
                    " to become a member of the community.";
        } else if (query.contains("flexibility") || query.contains("will i get the flexibility to participate in my activities at my convenience")) {
            return "Yes, having worked with the student community for the last few years, we completely understand their academic commitments and " +
                    "therefore offer the flexibility to participate in the activities at their convenience. With the events that are conducted " +
                    "at a stipulated time, the recordings are provided. During the internships, the students can work at their convenience however," +
                    " will need to meet the deadlines for submission.";
        } else if (query.contains("benefit of internship") || query.contains("has this program helped students earlier")) {
            return "More than 90% of the community members have rated us 4 & 5 out of 5 for their overall experience of the activities." +
                    " The reviews of the participant are available on our iReviews page and social media pages. The students have experienced" +
                    " a boost in confidence, especially during the interview, were able to channelize the interview, and have been placed in" +
                    " companies like Oracle, Microsoft, Capgemini, TCS, Wipro, Deloitte, etc. In terms of higher education, they were able to" +
                    " secure places in reputed universities in U.S., Australia, Ireland, etc.";
        } else if (query.contains("how will the internship be conducted")) {
            return "This is a 6-12 weeks online internship, that will be conducted during the vacation (generally) and you can choose any of one" +
                    " domain from the ones that we offer. This is a guaranteed internship for all the students in the age group of 17-24 years " +
                    "who aspire to IT & Management careers. There will be no interviews or aptitude tests required for participating in this " +
                    "internship. To keep the internship focused and reduce the stress to the students we allow working on only one technology at " +
                    "a time. The interns who have successfully submitted the project, get an experience certificate. The interns who have " +
                    "performed exceptionally well and have delivered high-quality deliverables are provided with ‘letters of appreciation' as " +
                    "well and are facilitated in the ‘Industry Academia Excellence Awards’.";
        } else if (query.contains("how are you")) {
            return "I'm just a bot, but I'm here to help!";
        } else if (query.contains("thank")) {
            return "You're Welcome!";
        } else if (query.contains("duration") || query.contains("weeks") || query.contains("difference")) {
            return "The difference in the internship duration is to provide you flexibility to " +
                    "accommodate your academic requirements, as many colleges do not allow their " +
                    "students to do internship while the academic sessions are in progress. " +
                    "Such students can contribute more hours and finish their internship early.\n" +
                    "\n" +
                    "The problem statement for all the duration for the resp. domain will be same. " +
                    "The excellence awards will categorised based on the duration and projects " +
                    "submitted for 6 weeks will be accessed in relation to the other projects " +
                    "submitted for the same duration";
        } else if (query.contains("more") || query.contains("multiple") || query.contains("more than one domain")) {
            return "We encourage focusing on one field at a time as this will help yield better results.";
        } else if (query.contains("how to choose domain")) {
            return "A form will be made available to the interns to choose the domain and the duration of the internship";
        } else if (query.contains("domain")) {
            return "There are 16 IT & 7 Management domains for internship:\n" +
                    "Cloud Computing,  Digital Marketing, DevOps, Human Resources, Machine Learning," +
                    " Data Analytics, Artificial Intelligence, Business Research, Web Development, Java, Python,\n" +
                    "Business Development, React JS, UI/UX, Node.js, Operations Management, " +
                    "Android Development, Quality Assurance, Flutter, Cyber Security, Product Management," +
                    "  Project Management, Game Development, Blockchain, Full Stack Development, Marketing Management";
        } else if (query.contains("help") || query.contains("guidance") || query.contains("mentor") || query.contains("guided")) {
            return "Participants will get help related to understanding the problem statement and the project delivery process";
        } else if (query.contains("selection") || query.contains("criteria")) {
            return "This internship is designed for students in higher education courses and any such student" +
                    " aspiring for IT and Management career can participate in this internship.";
        } else if (query.contains("freshers") || query.contains("graduates") || query.contains("postgraduate") || query.contains("undergraduate")) {
            return "This internship program is designed for undergraduate students but if graduates and freshers" +
                    " feel it is beneficial for them then they can participate in this internship program.";
        } else if (query.contains("internship")) {
            return "Please paste the link in your browser to signup " +
                    "for the free online internship https://www.industryacademiacommunity.com/courses/Internships";
        } else if (query.contains("update") || query.contains("telegram")) {
            return "Please join our Telegram Channel for updates: https://t.me/+uXmD1vTLpttjN2Vl";
        }  else if (query.contains("offer letter") || query.contains("certificate")) {
            return "The participants will get following certificates and letters:\n" +
                    "> Appointment letter within 15 days after the interns choose the field of interest\n" +
                    "> Industry Training Certificate (further to completing assessment)\n" +
                    "> Experience Certificate from Cloud Counselage (on successful completion of internship)\n" +
                    "> Appreciation letter/certificate ( For top performers)\n";
        } else if (query.contains("appointment") || query.contains("letter")) {
            return "We will be providing appointment letters within 15 days after the interns choose the field of interest.";
        } else if (query.contains("successful") || query.contains("completion")) {
            return "The interns have to complete the tasks as per the instructions within the deadlines";
        }  else if (query.contains("change") || query.contains("change domain") || query.contains("switch")) {
            return "Participants will get a chance to choose/change the domain and duration for internship from 14th July to 16th July.";
        } else if (query.contains("paid") || query.contains("free") || query.contains("stipend")) {
            return "Internship and other IAC activities are absolutely FREE.\n" +
                    "There is no cost involved for participating in the community activities (including internships) as " +
                    "this is a noble initiative taken up by Cloud Counselage Pvt.Ltd. in association with Gift- " +
                    "A-Career Foundation, to help students, get job-ready, in time!";
        } else if (query.contains("timings") || query.contains("schedule")) {
            return "For schedule of internship please refer https://shorturl.at/eSX46 The interns are expected to contribute minimum 1 to 2 hours on a daily basis.";
        } else if (query.contains("laptop")) {
            return "Yes, Laptop Required.";
        }  else if (query.contains("data") || query.contains("data analytics")) {
            return "Yes, the data will be provided for data analytics.";
        } else {
            return "I'm not sure how to respond to that.";
        }
    }
}
