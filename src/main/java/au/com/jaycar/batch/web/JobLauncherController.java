package au.com.jaycar.batch.web;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JobLauncherController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    JobRepository jobRepository;
    
    @Autowired
    JobOperator jobOperator;
    
    @Autowired
    Job job;

    @RequestMapping("/jobLauncher.html")
    public void handle() throws Exception {
	JobParametersBuilder builder = new JobParametersBuilder();
	builder.addDate("date", new Date());
	jobLauncher.run(job, builder.toJobParameters());
    }
}