package com.example.cloud.watch.service;

import com.amazonaws.services.logs.AWSLogs;
import com.amazonaws.services.logs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class CloudWatchService {
    @Autowired
    private AWSLogs cloudWatchlog;
    private static String logGroupName = "your-curd-app";
    private static String logStreamName = "your-curd-stream";

    public void logMessageToCloudWatch(String message) {

        List<InputLogEvent> logEvents = new ArrayList<InputLogEvent>();
        InputLogEvent log = new InputLogEvent();
        Calendar calendar = Calendar.getInstance();

        log.setTimestamp(calendar.getTimeInMillis());
        log.setMessage(message);
        logEvents.add(log);

        String token = null;
        DescribeLogStreamsRequest logStreamsRequest = new DescribeLogStreamsRequest(logGroupName);

        logStreamsRequest.withLimit(5);
        List<LogStream> logStreamList = new ArrayList<LogStream>();
        logStreamList = cloudWatchlog.describeLogStreams(logStreamsRequest).getLogStreams();

        for (LogStream logStream : logStreamList) {
            if (logStream.getLogStreamName().equals(logStreamName))
                token = logStream.getUploadSequenceToken();
        }

        PutLogEventsRequest putLogEventsRequest = new PutLogEventsRequest();
        PutLogEventsResult putLogEventsResult = new PutLogEventsResult();
        if (token != null) {

            appendLogsToCloudWatchWithToken(putLogEventsRequest, putLogEventsResult, token, logEvents);
        } else {
            firstHitToCloudWatch(putLogEventsRequest, logEvents, putLogEventsResult);
        }

    }

    /**
     * This method takes care to generate token for next iteration and also push
     * logs to CloudWatch
     *
     * @param putLogEventsRequest
     * @param putLogEventsResult
     * @param token
     * @param logEvents
     */
    private void appendLogsToCloudWatchWithToken(PutLogEventsRequest putLogEventsRequest,
                                                 PutLogEventsResult putLogEventsResult, String token, List<InputLogEvent> logEvents) {
        putLogEventsRequest.setLogGroupName(logGroupName);
        putLogEventsRequest.setLogStreamName(logStreamName);
        putLogEventsRequest.setLogEvents(logEvents);

        putLogEventsRequest.setSequenceToken(token);

        putLogEventsResult = cloudWatchlog.putLogEvents(putLogEventsRequest);

    }

    /**
     * Method that is used for the first time to store logs to CloudWatch without
     * token
     *
     * @param putLogEventsRequest
     * @param logEvents
     * @param putLogEventsResult
     */
    private void firstHitToCloudWatch(PutLogEventsRequest putLogEventsRequest, List<InputLogEvent> logEvents,
                                      PutLogEventsResult putLogEventsResult) {
        putLogEventsRequest.setLogGroupName(logGroupName);
        putLogEventsRequest.setLogStreamName(logStreamName);
        putLogEventsRequest.setLogEvents(logEvents);

        putLogEventsResult = cloudWatchlog.putLogEvents(putLogEventsRequest);

    }
}
