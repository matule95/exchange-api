package mz.co.checkmob.api.authorization.domain;

import mz.co.checkmob.api.core.utils.ApiService;

import java.util.Map;

public enum AuthorizationType {
    NO_AUTH, API_KEY , BEARER_TOKEN , BASIC_AUTH,OAUTH2
}