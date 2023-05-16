package com.allsolved.allsolved.common.slack;

import com.slack.api.Slack;
import org.springframework.beans.factory.annotation.Value;

public class ControllerAdvice {

    private final Slack slackClient = Slack.getInstance();
    @Value("${slack.webhook.url")
    private String webhookUrl;
}
