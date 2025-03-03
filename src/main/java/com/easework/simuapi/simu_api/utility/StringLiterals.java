package com.easework.simuapi.simu_api.utility;

import java.util.List;

public  class StringLiterals {
    
    public static String DB_URL = "dbUrl";
    public static String API_DETAILS = "apiDetails";
    public static String API_TYPE = "apiType";
    public static String API_URL = "apiUrl";
    public static String HTTP_STATUS_CODE = "statusCode";
    public static String RESPONSE_BODY = "responseBody";
    public static String UNDERSCORE = "_";


    public static String getDotPath(String ...args){
        String s = "";
        if(args.length > 0){
            for(int i=0; i<args.length; i++){
                if(i == 0)
                    s+=args[i];
                else
                    s+="."+args[i];
            }
        }
        return s;
    }


    // public static final Map<Integer, HttpStatus> HTTP_STATUS_CODE_MAP = Arrays.stream(HttpStatus.values())
    //         .collect(Collectors.toMap(HttpStatus::value, status -> status));

}
