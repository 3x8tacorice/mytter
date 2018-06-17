package com.example.mytter.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GithubRepo {
    private String name;
    @JsonProperty("html_url")
    private String htmlUrl;
}
