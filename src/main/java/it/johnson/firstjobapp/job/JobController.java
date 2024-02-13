package it.johnson.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping()
    public ResponseEntity<List<Job>> findAll(){
        return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job created Successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if(job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted)
            return ResponseEntity.ok("Job deleted successfully");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //@PutMapping("/{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJobBy(id, updatedJob);
        if(updated)
            return ResponseEntity.ok("Job updated successfully");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
